package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.entities.SongsList;
import com.bwinfoservices.guitarapis.payloads.requests.SongNumRange;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unused")
public interface SongsService {
    List<SongsList> listAll();

    SongsList findBySongNum(String songNum);

    void save(SongsList songsList);

    byte[] getAudioFile(Integer songId) throws IOException;

    byte[] getPdfFile(Integer songId) throws IOException;

    List<String> listAllSongNums();

    List<String> listAllAlbums();

    List<String> listAllArtists();

    List<Integer> listAllReleaseYears();

    List<String> listAllComposers();

    List<String> listAllLyricists();

    List<String> listAllChords();

    String getLastSongNum();

    Integer getReleaseYear(String albumName);

    List<String> listToSongNums(String fromSongNum);

    byte[] getSongsIndex(SongNumRange songNumRange) throws JRException, SQLException, IOException;
}
