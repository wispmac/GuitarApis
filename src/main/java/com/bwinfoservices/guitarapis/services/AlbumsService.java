package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.entities.Albums;
import com.bwinfoservices.guitarapis.payloads.responses.*;

public interface AlbumsService {
    AlbumsListResponse listAll();

    IntegerListResponse listReleaseYears();

    ReleaseYearResponse getReleaseYear(String albumName);

    AlbumDetailsResponse find(String albumName);

    AlbumDetailsResponse find(Integer albumId);

    SaveMasterResponse save(Albums album);

    ResponseMessage delete(Integer albumId);

    ResponseMessage delete(String albumName);
}
