package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.payloads.requests.SaveSongRequest;
import com.bwinfoservices.guitarapis.payloads.requests.UploadFileRequest;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.commons.MediaType;

public interface SongsService {
    SongsListResponse listAll();

    SongsResponse findBySongId(Integer songId);

    SongsResponse findBySongNum(String songNum);

    SaveSongResponse save(SaveSongRequest saveSongRequest);

    FileResponse getFile(Integer songId, MediaType mediaType);

    UploadFileResponse uploadFile(UploadFileRequest uploadFileRequest, MediaType mediaType);

    FileResponse getSongsIndex(String songNumFrom, String songNumTo);

    StringListResponse listAllSongNums();

    LastSongNumResponse getLastSongNum();

    StringListResponse listToSongNums(String fromSongNum);

    ResponseMessage delete(String songNum);

    ResponseMessage delete(Integer songId);
}
