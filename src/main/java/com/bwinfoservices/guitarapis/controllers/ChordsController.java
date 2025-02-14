package com.bwinfoservices.guitarapis.controllers;

import com.bwinfoservices.guitarapis.commons.GetResponse;
import com.bwinfoservices.guitarapis.dtos.ChordsDto;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.services.ChordsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChordsController {
    private final ChordsService chordsService;

    public ChordsController(ChordsService chordsService) {
        this.chordsService = chordsService;
    }

    @GetMapping("/chords")
    public ResponseEntity<?> listChords() {
        ChordsListResponse response = chordsService.listAll();

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/chords/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "chordId") Integer chordId) {
        ChordDetailsResponse response = chordsService.getChordDetails(chordId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("chords/findByName")
    public ResponseEntity<?> findByName(@RequestParam(name = "chordName") String chordName) {
        ChordDetailsResponse response = chordsService.getChordDetails(chordName);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/chords/update")
    public ResponseEntity<?> updateChord(@RequestBody ChordsDto chordDetails) {
        ResponseMessage response = chordsService.save(chordDetails);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/chords/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam(name = "chordId") Integer chordId) {
        ResponseMessage response = chordsService.delete(chordId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/chords/deleteByName")
    public ResponseEntity<?> deleteByName(@RequestParam(name = "chordName") String chordName) {
        ResponseMessage response = chordsService.delete(chordName);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/chords/frets")
    public ResponseEntity<?> listFrets(@RequestParam(name = "noteName") String noteName,
                                       @RequestParam(name = "stringNum") Integer stringNum) {
        StringListResponse response = chordsService.findFrets(noteName, stringNum);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/chords/frequency")
    public ResponseEntity<?> getFrequency(@RequestParam(name = "stringNum") Integer stringNum,
                                          @RequestParam(name = "fretNum") Integer fretNum,
                                          @RequestParam(name = "noteName") String noteName) {
        FrequencyResponse response = chordsService.findFrequency(stringNum, fretNum, noteName);

        return GetResponse.generate(response.getStatus(), response);
    }
}
