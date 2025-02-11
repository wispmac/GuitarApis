package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.payloads.requests.SaveSongRequest;
import com.bwinfoservices.guitarapis.payloads.requests.SongNumRange;
import com.bwinfoservices.guitarapis.payloads.requests.UploadFileRequest;
import com.bwinfoservices.guitarapis.payloads.responses.*;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public interface SongsService {
    SongsListResponse listAll();

    SongsResponse findBySongNum(String songNum);

    SaveSongResponse save(SaveSongRequest saveSongRequest);

    FileResponse getAudioFile(Integer songId) throws IOException;

    FileResponse getPdfFile(Integer songId) throws IOException;

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

    FileResponse getSongsIndex(SongNumRange songNumRange);

    UploadFileResponse uploadAudioFile(UploadFileRequest uploadFileRequest);

    UploadFileRequest uploadPdfFile(UploadFileRequest uploadFileRequest);
}
