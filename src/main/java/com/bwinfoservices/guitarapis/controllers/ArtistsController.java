package com.bwinfoservices.guitarapis.controllers;

import com.bwinfoservices.guitarapis.commons.GetResponse;
import com.bwinfoservices.guitarapis.entities.Artists;
import com.bwinfoservices.guitarapis.payloads.responses.ArtistDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ArtistsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.services.ArtistsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtistsController {
    private final ArtistsService artistsService;

    public ArtistsController(ArtistsService artistsService) {
        this.artistsService = artistsService;
    }

    @GetMapping("/artists")
    public ResponseEntity<?> listArtists() {
        ArtistsListResponse response = artistsService.listAll();

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/artists/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "artistId") Integer artistId) {
        ArtistDetailsResponse response = artistsService.find(artistId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("artists/findByName")
    public ResponseEntity<?> findByName(@RequestParam(name = "artistName") String artistName) {
        ArtistDetailsResponse response = artistsService.find(artistName);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/artists/update")
    public ResponseEntity<?> updateArtist(@RequestBody Artists artist) {
        SaveMasterResponse response = artistsService.save(artist);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/artists/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam(name = "artistId") Integer artistId) {
        ResponseMessage response = artistsService.delete(artistId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/artists/deleteByName")
    public ResponseEntity<?> deleteByName(@RequestParam(name = "artistName") String artistName) {
        ResponseMessage response = artistsService.delete(artistName);

        return GetResponse.generate(response.getStatus(), response);
    }
}
