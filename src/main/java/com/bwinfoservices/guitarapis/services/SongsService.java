package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.payloads.requests.SaveSongRequest;
import com.bwinfoservices.guitarapis.payloads.requests.SongNumRange;
import com.bwinfoservices.guitarapis.payloads.requests.UploadFileRequest;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.types.MediaType;

@SuppressWarnings("unused")
public interface SongsService {
    SongsListResponse listAll();

    SongsResponse findBySongNum(String songNum);

    SaveSongResponse save(SaveSongRequest saveSongRequest);

    FileResponse getFile(Integer songId, MediaType mediaType);

    StringListResponse listAllSongNums();

    StringListResponse listAllAlbums();

    StringListResponse listAllArtists();

    IntegerListResponse listAllReleaseYears();

    StringListResponse listAllComposers();

    StringListResponse listAllLyricists();

    StringListResponse listAllChords();

    LastSongNumResponse getLastSongNum();

    ReleaseYearResponse getReleaseYear(String albumName);

    StringListResponse listToSongNums(String fromSongNum);

    FileResponse getSongsIndex(SongNumRange songNumRange);

    UploadFileResponse uploadFile(UploadFileRequest uploadFileRequest, MediaType mediaType);

    ResponseMessage delete(String songNum);

    ResponseMessage delete(Integer songId);
}
