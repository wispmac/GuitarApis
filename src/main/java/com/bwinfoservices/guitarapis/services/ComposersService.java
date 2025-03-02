package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.entities.Composers;
import com.bwinfoservices.guitarapis.payloads.responses.ComposerDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ComposersListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;

@SuppressWarnings("unused")
public interface ComposersService {
    ComposersListResponse listAll();

    ComposerDetailsResponse find(String composerName);

    ComposerDetailsResponse find(Integer composerId);

    SaveMasterResponse save(Composers composer);

    ResponseMessage delete(Integer composerId);

    ResponseMessage delete(String composerName);
}
