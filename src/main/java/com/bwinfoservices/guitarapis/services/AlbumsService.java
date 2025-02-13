package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.entities.Albums;
import com.bwinfoservices.guitarapis.payloads.responses.AlbumDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.AlbumsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;

public interface AlbumsService {
    AlbumsListResponse listAll();

    AlbumDetailsResponse find(String albumName);

    AlbumDetailsResponse find(Integer albumId);

    SaveMasterResponse save(Albums album);

    ResponseMessage delete(Integer albumId);

    ResponseMessage delete(String albumName);
}
