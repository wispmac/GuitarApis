package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.config.FilepathConfig;
import com.bwinfoservices.guitarapis.entities.*;
import com.bwinfoservices.guitarapis.payloads.requests.SaveSongRequest;
import com.bwinfoservices.guitarapis.payloads.requests.SongNumRange;
import com.bwinfoservices.guitarapis.payloads.requests.UploadFileRequest;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.repositories.*;
import com.bwinfoservices.guitarapis.services.SongsService;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class SongsServiceImpl implements SongsService {
    private final FilepathConfig filepathConfig;
    private final AlbumsRepository albumsRepository;
    private final ArtistsRepository artistsRepository;
    private final ArtistsUsedRepository artistsUsedRepository;
    private final ChordsRepository chordsRepository;
    private final ChordsUsedRepository chordsUsedRepository;
    private final ComposersRepository composersRepository;
    private final LyricistsRepository lyricistsRepository;
    private final MusicFilesRepository musicFilesRepository;
    private final SongsRepository songsRepository;
    private final SongsListRepository songsListRepository;
    private final DataSource dataSource;

    public SongsServiceImpl(
            FilepathConfig filepathConfig,
            AlbumsRepository albumsRepository,
            ArtistsRepository artistsRepository,
            ArtistsUsedRepository artistsUsedRepository,
            ChordsRepository chordsRepository,
            ChordsUsedRepository chordsUsedRepository,
            ComposersRepository composersRepository,
            LyricistsRepository lyricistsRepository,
            MusicFilesRepository musicFilesRepository,
            SongsRepository songsRepository,
            SongsListRepository songsListRepository,
            DataSource dataSource) {
        this.filepathConfig = filepathConfig;
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
        this.artistsUsedRepository = artistsUsedRepository;
        this.chordsRepository = chordsRepository;
        this.chordsUsedRepository = chordsUsedRepository;
        this.composersRepository = composersRepository;
        this.lyricistsRepository = lyricistsRepository;
        this.musicFilesRepository = musicFilesRepository;
        this.songsRepository = songsRepository;
        this.songsListRepository = songsListRepository;
        this.dataSource = dataSource;
    }

    @Override
    public SongsListResponse listAll() {
        try {
            return new SongsListResponse("Success", songsListRepository.findAll());
        } catch (Exception e) {
            return new SongsListResponse(e.getMessage(), null);
        }
    }

    @Override
    public SongsResponse findBySongNum(String songNum) {
        SongsList song = songsListRepository.findById_SongNum(songNum).orElse(null);

        if (song == null) {
            return new SongsResponse("Not Found", new SongsList());
        } else {
            return new SongsResponse("Success", song);
        }
    }

    @Override
    public SaveSongResponse save(SaveSongRequest saveSongRequest) {
        SaveSongResponse saveSongResponse = new SaveSongResponse();

        try {
            Songs song = songsRepository.findById(saveSongRequest.getId()).orElse(new Songs());

            Albums album = albumsRepository.findByAlbumName(saveSongRequest.getAlbumName()).orElse(null);
            if (album == null) {
                album = new Albums();
                album.setAlbumName(saveSongRequest.getAlbumName());
                album.setReleaseYear(saveSongRequest.getReleaseYear());
                album = albumsRepository.saveAndFlush(album);
            }

            Composers composer = composersRepository.findByComposerName(saveSongRequest.getComposerName()).orElse(null);
            if (composer == null) {
                composer = new Composers();
                composer.setComposerName(saveSongRequest.getComposerName());
                composer = composersRepository.saveAndFlush(composer);
            }

            Lyricists lyricist = lyricistsRepository.findByLyricistName(saveSongRequest.getLyricistName()).orElse(null);
            if (lyricist == null) {
                lyricist = new Lyricists();
                lyricist.setLyricistName(saveSongRequest.getLyricistName());
                lyricist = lyricistsRepository.saveAndFlush(lyricist);
            }

            song.setSongNum(saveSongRequest.getSongNum());
            song.setSongTitle(saveSongRequest.getSongTitle());
            song.setDtLearnt(saveSongRequest.getDtLearnt());
            song.setAlbumId(album.getId());
            song.setComposerId(composer.getId());
            song.setLyricistId(lyricist.getId());
            song.setCapoInFret(saveSongRequest.getCapoInFret());

            song = songsRepository.saveAndFlush(song);

            List<ArtistsUsed> lstArtists = artistsUsedRepository.findBySongId(song.getId());
            artistsUsedRepository.deleteAll(lstArtists);
            lstArtists = new ArrayList<>();
            String[] artists = saveSongRequest.getArtistName().split("/");
            List<Integer> artistIds = new ArrayList<>();
            for (String artistName : artists) {
                Artists artist = artistsRepository.findByArtistName(artistName).orElse(null);
                if (artist == null) {
                    artist = new Artists();
                    artist.setArtistName(artistName);
                    artist = artistsRepository.saveAndFlush(artist);
                }
                ArtistsUsed artistsUsed = new ArtistsUsed();
                artistsUsed.setArtistId(artist.getId());
                artistsUsed.setSongId(song.getId());
                lstArtists.add(artistsUsed);
                artistIds.add(artist.getId());
            }
            if (!lstArtists.isEmpty()) {
                artistsUsedRepository.saveAll(lstArtists);
            }

            List<ChordsUsed> lstChords = chordsUsedRepository.findBySongId(song.getId());
            chordsUsedRepository.deleteAll(lstChords);
            lstChords = new ArrayList<>();
            String[] chords = saveSongRequest.getChordsUsed().split("/");
            List<Integer> chordIds = new ArrayList<>();
            for (String chordName : chords) {
                Chords chord = chordsRepository.findByChordName(chordName).orElse(null);
                if (chord == null) {
                    chord = new Chords();
                    chord.setChordName(chordName);
                    chord = chordsRepository.saveAndFlush(chord);
                }
                ChordsUsed chordsUsed = new ChordsUsed();
                chordsUsed.setChordId(chord.getId());
                chordsUsed.setSongId(song.getId());
                lstChords.add(chordsUsed);
                chordIds.add(chord.getId());
            }
            if (!lstChords.isEmpty()) {
                chordsUsedRepository.saveAll(lstChords);
            }

            saveSongResponse.setStatus("Success");
            saveSongResponse.setSongId(song.getId());
            saveSongResponse.setAlbumId(album.getId());
            saveSongResponse.setComposerId(composer.getId());
            saveSongResponse.setLyricistId(lyricist.getId());
            saveSongResponse.setArtistIds(artistIds);
            saveSongResponse.setChordIds(chordIds);
        } catch (Exception ex) {
            saveSongResponse.setStatus("Error: " + ex.getMessage());
            saveSongResponse.setSongId(null);
            saveSongResponse.setAlbumId(null);
            saveSongResponse.setComposerId(null);
            saveSongResponse.setLyricistId(null);
            saveSongResponse.setArtistIds(null);
            saveSongResponse.setChordIds(null);
        }
        return saveSongResponse;
    }

    @Override
    public FileResponse getAudioFile(Integer songId) throws IOException {
        MusicFiles audioFile = musicFilesRepository.findAudioFile(songId).orElse(null);
        FileResponse fileResponse = new FileResponse("Not Found", null, null, null);

        if (audioFile != null) {
            String filename = filepathConfig.getFileLocation() + filepathConfig.getAudioPath() + audioFile.getFileName();


            setFileResponse(fileResponse, filename, audioFile);
        }

        return fileResponse;
    }

    @Override
    public FileResponse getPdfFile(Integer songId) throws IOException {
        MusicFiles pdfFile = musicFilesRepository.findPdfFile(songId).orElse(null);
        FileResponse fileResponse = new FileResponse("Not Found", null, null, null);

        if (pdfFile != null) {
            String filename = filepathConfig.getFileLocation() + filepathConfig.getPdfPath() + pdfFile.getFileName();

            setFileResponse(fileResponse, filename, pdfFile);
        }

        return fileResponse;
    }

    private void setFileResponse(FileResponse fileResponse, String fileName, MusicFiles musicFile) throws IOException {
        File file = new File(fileName);

        if (file.exists() && !file.isDirectory()) {
            fileResponse.setFileName(musicFile.getFileName());
            fileResponse.setFileSize(musicFile.getFileSize());
            fileResponse.setData(Files.readAllBytes(file.toPath()));
            fileResponse.setStatus("Success");
        }
    }

    @Override
    public List<String> listAllSongNums() {
        return songsRepository.listAllSongNums();
    }

    @Override
    public List<String> listAllAlbums() {
        return albumsRepository.listAllAlbums();
    }

    @Override
    public List<String> listAllArtists() {
        return artistsRepository.listAllArtists();
    }

    @Override
    public List<Integer> listAllReleaseYears() {
        return albumsRepository.listAllReleaseYears();
    }

    @Override
    public List<String> listAllComposers() {
        return composersRepository.listAllComposers();
    }

    @Override
    public List<String> listAllLyricists() {
        return lyricistsRepository.listAllLyricists();
    }

    @Override
    public List<String> listAllChords() {
        return chordsRepository.listAllChords();
    }

    @Override
    public String getLastSongNum() {
        return songsRepository.getLastSongNum();
    }

    @Override
    public Integer getReleaseYear(String albumName) {
        return albumsRepository.getReleaseYear(albumName);
    }

    @Override
    public List<String> listToSongNums(String fromSongNum) {
        return songsRepository.listToSongNums(fromSongNum);
    }

    @Override
    public FileResponse getSongsIndex(SongNumRange songNumRange) {
        FileResponse fileResponse = new FileResponse();

        try {
            Connection conn = Objects.requireNonNull(dataSource).getConnection();

            Integer fromId = songsRepository.findIdBySongNum(songNumRange.getSongNumFrom());
            Integer toId = songsRepository.findIdBySongNum(songNumRange.getSongNumTo());

            Map<String, Object> params = new HashMap<>();
            params.put("startId", fromId);
            params.put("endId", toId);

            String path = ResourceUtils.getFile("classpath:report/GuitarIndex.jasper").getAbsolutePath();

            JasperReport jReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, params, conn);

            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jPrint);
            fileResponse.setData(pdfBytes);
            fileResponse.setFileName("GuitarIndex.pdf");
            fileResponse.setFileSize((long) pdfBytes.length);
            fileResponse.setStatus("Success");
        } catch (JRException | SQLException | IOException e) {
            fileResponse.setStatus("Error: " + e.getMessage());
            fileResponse.setFileName(null);
            fileResponse.setFileSize(null);
            fileResponse.setData(null);
        }

        return fileResponse;
    }

    @Override
    public UploadFileResponse uploadAudioFile(UploadFileRequest uploadFileRequest) {
        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        MusicFiles audioFile = null;

        try {
            if (uploadFileRequest.getId() != null && uploadFileRequest.getId() > 0) {
                audioFile = musicFilesRepository.findById(uploadFileRequest.getId()).orElse(null);
            }

            if (audioFile == null) {
                audioFile = new MusicFiles();
            }

            audioFile.setSongId(uploadFileRequest.getSongId());
            audioFile.setFileName(uploadFileRequest.getFileName());
            audioFile.setFileType(uploadFileRequest.getFileType());
            audioFile.setFileSize((long) uploadFileRequest.getFileData().length);

            String filename = filepathConfig.getFileLocation() + filepathConfig.getAudioPath() + uploadFileRequest.getFileName();
            FileUtils.writeByteArrayToFile(new File(filename), uploadFileRequest.getFileData());

            audioFile = musicFilesRepository.saveAndFlush(audioFile);

            uploadFileResponse.setStatus("Success");
            uploadFileResponse.setId(audioFile.getId());
        } catch (Exception e) {
            uploadFileResponse.setStatus("Error: " + e.getMessage());
            uploadFileResponse.setId(null);
        }

        return uploadFileResponse;
    }

    @Override
    public UploadFileRequest uploadPdfFile(UploadFileRequest uploadFileRequest) {
        return null;
    }
}
