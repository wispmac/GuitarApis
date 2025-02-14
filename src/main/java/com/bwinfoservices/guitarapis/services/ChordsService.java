package com.bwinfoservices.guitarapis.services;

import com.bwinfoservices.guitarapis.dtos.ChordsDto;
import com.bwinfoservices.guitarapis.payloads.responses.*;

@SuppressWarnings("unused")
public interface ChordsService {
    ChordsListResponse listAll();

    ChordDetailsResponse getChordDetails(Integer chordId);

    ChordDetailsResponse getChordDetails(String chordName);

    ResponseMessage save(ChordsDto chordDetails);

    ResponseMessage delete(Integer chordId);

    ResponseMessage delete(String chordName);

    StringListResponse findFrets(String noteName, Integer stringNum);

    FrequencyResponse findFrequency(Integer stringNum, Integer fretNum, String noteName);
}
