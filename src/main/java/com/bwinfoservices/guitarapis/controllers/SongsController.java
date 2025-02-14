package com.bwinfoservices.guitarapis.controllers;

import com.bwinfoservices.guitarapis.commons.GetResponse;
import com.bwinfoservices.guitarapis.commons.MediaType;
import com.bwinfoservices.guitarapis.payloads.requests.SaveSongRequest;
import com.bwinfoservices.guitarapis.payloads.requests.UploadFileRequest;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.services.SongsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongsController {
    private final SongsService songsService;

    public SongsController(SongsService songsService) {
        this.songsService = songsService;
    }

    @GetMapping("/songs")
    public ResponseEntity<?> listSongs() {
        SongsListResponse response = songsService.listAll();

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/songs/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "songId") Integer songId) {
        SongsResponse response = songsService.findBySongId(songId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/songs/findBySongNum")
    public ResponseEntity<?> findBySongNum(@RequestParam(name = "songNum") String songNum) {
        SongsResponse response = songsService.findBySongNum(songNum);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/songs/update")
    public ResponseEntity<?> updateSong(@RequestBody SaveSongRequest saveSongRequest) {
        SaveSongResponse response = songsService.save(saveSongRequest);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/songs/audioFile")
    public ResponseEntity<?> getAudioFile(@RequestParam(name = "songId")Integer songId) {
        FileResponse response = songsService.getFile(songId, MediaType.Audio);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/songs/pdfFile")
    public ResponseEntity<?> getPdfFile(@RequestParam(name = "songId")Integer songId) {
        FileResponse response = songsService.getFile(songId, MediaType.Pdf);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/songs/audioFile")
    public ResponseEntity<?> updateAudioFile(@RequestBody UploadFileRequest uploadFileRequest) {
        UploadFileResponse response = songsService.uploadFile(uploadFileRequest, MediaType.Audio);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/songs/pdfFile")
    public ResponseEntity<?> updatePdfFile(@RequestBody UploadFileRequest uploadFileRequest) {
        UploadFileResponse response = songsService.uploadFile(uploadFileRequest, MediaType.Pdf);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/songs/songsIndex")
    public ResponseEntity<?> generateIndex(@RequestParam(name = "songNumFrom") String songNumFrom,
                                           @RequestParam(name = "songNumTo") String songNumTo) {
        FileResponse response = songsService.getSongsIndex(songNumFrom, songNumTo);

        return GetResponse.generate(response.getStatus(), response);
    }


}
