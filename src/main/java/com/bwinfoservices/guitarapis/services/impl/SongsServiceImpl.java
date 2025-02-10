package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.config.FilepathConfig;
import com.bwinfoservices.guitarapis.entities.MusicFiles;
import com.bwinfoservices.guitarapis.entities.SongsList;
import com.bwinfoservices.guitarapis.payloads.requests.SongNumRange;
import com.bwinfoservices.guitarapis.repositories.*;
import com.bwinfoservices.guitarapis.services.SongsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
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
    public List<SongsList> listAll() {
        return songsListRepository.findAll();
    }

    @Override
    public SongsList findBySongNum(String songNum) {
        return songsListRepository.findById_SongNum(songNum);
    }

    @Override
    public void save(SongsList songsList) {

    }

    @Override
    public byte[] getAudioFile(Integer songId) throws IOException {
        MusicFiles audioFile = musicFilesRepository.findAudioFile(songId).orElse(null);

        if (audioFile != null) {
            String filename = filepathConfig.getFileLocation() + filepathConfig.getAudioPath() + audioFile.getFileName();

            File file = new File(filename);

            if (file.exists() && !file.isDirectory()) {
                return Files.readAllBytes(file.toPath());
            }
        }

        return new byte[0];
    }

    @Override
    public byte[] getPdfFile(Integer songId) throws IOException {
        MusicFiles pdfFile = musicFilesRepository.findPdfFile(songId).orElse(null);

        if (pdfFile != null) {
            String filename = filepathConfig.getFileLocation() + filepathConfig.getPdfPath() + pdfFile.getFileName();

            File file = new File(filename);

            if (file.exists() && !file.isDirectory()) {
                return Files.readAllBytes(file.toPath());
            }
        }

        return new byte[0];
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
    public byte[] getSongsIndex(SongNumRange songNumRange) throws JRException, SQLException, IOException {
        Connection conn = Objects.requireNonNull(dataSource).getConnection();

        Integer fromId = songsRepository.findIdBySongNum(songNumRange.getSongNumFrom());
        Integer toId = songsRepository.findIdBySongNum(songNumRange.getSongNumTo());

        Map<String, Object> params = new HashMap<>();
        params.put("startId", fromId);
        params.put("endId", toId);

        String path = ResourceUtils.getFile("classpath:report/GuitarIndex.jasper").getAbsolutePath();

        JasperReport jReport = (JasperReport) JRLoader.loadObjectFromFile(path);
        JasperPrint jPrint = JasperFillManager.fillReport(jReport, params, conn);

        return JasperExportManager.exportReportToPdf(jPrint);
    }
}
