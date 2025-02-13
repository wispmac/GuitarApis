package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.entities.Artists;
import com.bwinfoservices.guitarapis.payloads.responses.ArtistDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ArtistsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;

public interface ArtistsService {
    ArtistsListResponse listAll();

    ArtistDetailsResponse find(String artistName);

    ArtistDetailsResponse find(Integer artistId);

    SaveMasterResponse save(Artists artist);

    ResponseMessage delete(Integer artistId);

    ResponseMessage delete(String artistName);
}
