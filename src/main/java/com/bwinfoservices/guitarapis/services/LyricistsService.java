package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.entities.Lyricists;
import com.bwinfoservices.guitarapis.payloads.responses.LyricistDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.LyricistsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;

@SuppressWarnings("unused")
public interface LyricistsService {
    LyricistsListResponse listAll();

    LyricistDetailsResponse find(String lyricistName);

    LyricistDetailsResponse find(Integer lyricistId);

    SaveMasterResponse save(Lyricists lyricist);

    ResponseMessage delete(Integer lyricistId);

    ResponseMessage delete(String lyricistName);
}
