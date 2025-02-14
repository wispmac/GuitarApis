package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.config.FilepathConfig;
import com.bwinfoservices.guitarapis.commons.Constants;
import com.bwinfoservices.guitarapis.entities.*;
import com.bwinfoservices.guitarapis.payloads.requests.SaveSongRequest;
import com.bwinfoservices.guitarapis.payloads.requests.UploadFileRequest;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.repositories.*;
import com.bwinfoservices.guitarapis.services.SongsService;
import com.bwinfoservices.guitarapis.commons.MediaType;
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
            List<SongsList> lstSongs = songsListRepository.findAll();
            return new SongsListResponse(Constants.SUCCESS, lstSongs.size(), lstSongs);
        } catch (Exception e) {
            return new SongsListResponse(e.getMessage(), null, null);
        }
    }

    @Override
    public SongsResponse findBySongId(Integer songId) {
        SongsList song = songsListRepository.findById_Id(songId).orElse(null);

        if (song == null) {
            return new SongsResponse(Constants.NOT_FOUND, new SongsList());
        } else {
            return new SongsResponse(Constants.SUCCESS, song);
        }
    }

    @Override
    public SongsResponse findBySongNum(String songNum) {
        SongsList song = songsListRepository.findById_SongNum(songNum).orElse(null);

        if (song == null) {
            return new SongsResponse(Constants.NOT_FOUND, new SongsList());
        } else {
            return new SongsResponse(Constants.SUCCESS, song);
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

            saveSongResponse.setStatus(Constants.SUCCESS);
            saveSongResponse.setSongId(song.getId());
            saveSongResponse.setAlbumId(album.getId());
            saveSongResponse.setComposerId(composer.getId());
            saveSongResponse.setLyricistId(lyricist.getId());
            saveSongResponse.setArtistIds(artistIds);
            saveSongResponse.setChordIds(chordIds);
        } catch (Exception ex) {
            saveSongResponse.setStatus(Constants.ERROR + ex.getMessage());
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
    public FileResponse getFile(Integer songId, MediaType mediaType) {
        FileResponse fileResponse = new FileResponse(Constants.NOT_FOUND, null, null, null);

        try {
            MusicFiles musicFile = musicFilesRepository.findAudioFile(songId).orElse(null);

            if (musicFile != null) {
                String fileName = filepathConfig.getFileLocation() +
                        (mediaType == MediaType.Audio ? filepathConfig.getAudioPath() : filepathConfig.getPdfPath()) +
                        musicFile.getFileName();

                File file = new File(fileName);

                if (file.exists() && !file.isDirectory()) {
                    fileResponse.setFileName(musicFile.getFileName());
                    fileResponse.setFileSize(musicFile.getFileSize());
                    fileResponse.setData(Files.readAllBytes(file.toPath()));
                    fileResponse.setStatus(Constants.SUCCESS);
                }
            }
        } catch (Exception e) {
            fileResponse.setStatus(Constants.ERROR + e.getMessage());
        }

        return fileResponse;
    }

    @Override
    public UploadFileResponse uploadFile(UploadFileRequest uploadFileRequest, MediaType mediaType) {
        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        MusicFiles mediaFile = null;

        try {
            if (uploadFileRequest.getId() != null && uploadFileRequest.getId() > 0) {
                mediaFile = musicFilesRepository.findById(uploadFileRequest.getId()).orElse(null);
            }

            if (mediaFile == null) {
                mediaFile = new MusicFiles();
            }

            mediaFile.setSongId(uploadFileRequest.getSongId());
            mediaFile.setFileName(uploadFileRequest.getFileName());
            mediaFile.setFileType(uploadFileRequest.getFileType());
            mediaFile.setFileSize((long) uploadFileRequest.getFileData().length);

            String filename = filepathConfig.getFileLocation() +
                    (mediaType == MediaType.Audio ?  filepathConfig.getAudioPath() : filepathConfig.getPdfPath()) +
                    uploadFileRequest.getFileName();
            FileUtils.writeByteArrayToFile(new File(filename), uploadFileRequest.getFileData());

            mediaFile = musicFilesRepository.saveAndFlush(mediaFile);

            uploadFileResponse.setStatus(Constants.SUCCESS);
            uploadFileResponse.setId(mediaFile.getId());
        } catch (Exception e) {
            uploadFileResponse.setStatus(Constants.ERROR + e.getMessage());
            uploadFileResponse.setId(null);
        }

        return uploadFileResponse;
    }

    @Override
    public FileResponse getSongsIndex(String songNumFrom, String songNumTo) {
        FileResponse fileResponse = new FileResponse();

        try {
            Connection conn = Objects.requireNonNull(dataSource).getConnection();

            Integer fromId = songsRepository.findIdBySongNum(songNumFrom);
            Integer toId = songsRepository.findIdBySongNum(songNumTo);

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
            fileResponse.setStatus(Constants.SUCCESS);
        } catch (JRException | SQLException | IOException e) {
            fileResponse.setStatus(Constants.ERROR + e.getMessage());
            fileResponse.setFileName(null);
            fileResponse.setFileSize(null);
            fileResponse.setData(null);
        }

        return fileResponse;
    }

    @Override
    public StringListResponse listAllSongNums() {
        try {
            List<String> lstSongNums = songsRepository.listAllSongNums();
            return new StringListResponse(Constants.SUCCESS, lstSongNums.size(), lstSongNums);
        } catch (Exception e) {
            return new StringListResponse(Constants.ERROR + e.getMessage(), null, null);
        }
    }

    @Override
    public LastSongNumResponse getLastSongNum() {
        try {
            return new LastSongNumResponse(Constants.SUCCESS, songsRepository.getLastSongNum());
        } catch (Exception e) {
            return new LastSongNumResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public StringListResponse listToSongNums(String fromSongNum) {
        try {
            List<String> lstSongNums = songsRepository.listToSongNums(fromSongNum);
            return new StringListResponse(Constants.SUCCESS, lstSongNums.size(), lstSongNums);
        } catch (Exception e) {
            return new StringListResponse(Constants.ERROR + e.getMessage(), null, null);
        }
    }

    @Override
    public ResponseMessage delete(String songNum) {
        Optional<Songs> song = songsRepository.findBySongNum(songNum);

        return song.map(songs -> new ResponseMessage(deleteSong(songs))).orElseGet(() -> new ResponseMessage(Constants.NOT_FOUND));
    }

    @Override
    public ResponseMessage delete(Integer songId) {
        Optional<Songs> song = songsRepository.findById(songId);

        return song.map(songs -> new ResponseMessage(deleteSong(songs))).orElseGet(() -> new ResponseMessage(Constants.NOT_FOUND));
    }

    private String deleteSong(Songs song) {
        try {
            List<MusicFiles> lstFiles = musicFilesRepository.findBySongId(song.getId());

            for (MusicFiles musicFile : lstFiles) {
                try {
                    String filename = filepathConfig.getFileLocation() +
                            (musicFile.getFileType().equals("application/pdf") ? filepathConfig.getPdfPath() : filepathConfig.getAudioPath()) +
                            musicFile.getFileName();
                    FileUtils.delete(new File(filename));
                } catch (Exception ignored) {
                    //
                }
            }

            musicFilesRepository.deleteAll(lstFiles);
            songsRepository.delete(song);

            return Constants.SUCCESS;
        } catch (Exception e) {
            return Constants.ERROR + e.getMessage();
        }
    }
}
