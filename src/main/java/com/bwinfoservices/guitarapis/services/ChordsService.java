package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.dtos.ChordsDto;
import com.bwinfoservices.guitarapis.payloads.responses.ChordDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ChordsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.StringListResponse;

@SuppressWarnings("unused")
public interface ChordsService {
    ChordsListResponse listAll();

    ChordDetailsResponse getChordDetails(String chordName);

    void save(ChordsDto chordDetails);

    ResponseMessage delete(String chordName);

    StringListResponse findFrets(String noteName, Integer stringNum);

    ResponseMessage findFrequency(Integer stringNum, Integer fretNum, String noteName);
}
