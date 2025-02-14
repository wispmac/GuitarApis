package com.bwinfoservices.guitarapis.controllers;

import com.bwinfoservices.guitarapis.commons.GetResponse;
import com.bwinfoservices.guitarapis.entities.Lyricists;
import com.bwinfoservices.guitarapis.payloads.responses.LyricistDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.LyricistsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.services.LyricistsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LyricistsController {
    private final LyricistsService lyricistsService;

    public LyricistsController(LyricistsService lyricistsService) {
        this.lyricistsService = lyricistsService;
    }

    @GetMapping("/lyricists")
    public ResponseEntity<?> listLyricists() {
        LyricistsListResponse response = lyricistsService.listAll();

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/lyricists/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "lyricistId") Integer lyricistId) {
        LyricistDetailsResponse response = lyricistsService.find(lyricistId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("lyricists/findByName")
    public ResponseEntity<?> findByName(@RequestParam(name = "lyricistName") String lyricistName) {
        LyricistDetailsResponse response = lyricistsService.find(lyricistName);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/lyricists/update")
    public ResponseEntity<?> updateLyricist(@RequestBody Lyricists lyricist) {
        SaveMasterResponse response = lyricistsService.save(lyricist);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/lyricists/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam(name = "lyricistId") Integer lyricistId) {
        ResponseMessage response = lyricistsService.delete(lyricistId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/lyricists/deleteByName")
    public ResponseEntity<?> deleteByName(@RequestParam(name = "lyricistName") String lyricistName) {
        ResponseMessage response = lyricistsService.delete(lyricistName);

        return GetResponse.generate(response.getStatus(), response);
    }
}
