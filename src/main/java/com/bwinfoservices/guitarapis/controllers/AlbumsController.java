package com.bwinfoservices.guitarapis.controllers;

import com.bwinfoservices.guitarapis.commons.GetResponse;
import com.bwinfoservices.guitarapis.entities.Albums;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.services.AlbumsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumsController {
    private final AlbumsService albumsService;

    public AlbumsController(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }

    @GetMapping("/albums")
    public ResponseEntity<?> listAlbums() {
        AlbumsListResponse response = albumsService.listAll();

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/albums/allReleaseYears")
    public ResponseEntity<?> listAllReleaseYears() {
        IntegerListResponse response = albumsService.listReleaseYears();

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/albums/releaseYear")
    public ResponseEntity<?> getReleaseYear(@RequestParam(name = "albumName") String albumName) {
        ReleaseYearResponse response = albumsService.getReleaseYear(albumName);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/albums/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "albumId") Integer albumId) {
        AlbumDetailsResponse response = albumsService.find(albumId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/albums/findByName")
    public ResponseEntity<?> findByName(@RequestParam(name = "albumName") String albumName) {
        AlbumDetailsResponse response = albumsService.find(albumName);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/albums/update")
    public ResponseEntity<?> updateAlbum(@RequestBody Albums album) {
        SaveMasterResponse response = albumsService.save(album);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/albums/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam(name = "albumId") Integer albumId) {
        ResponseMessage response = albumsService.delete(albumId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/albums/deleteByName")
    public ResponseEntity<?> deleteByName(@RequestParam(name = "albumName") String albumName) {
        ResponseMessage response = albumsService.delete(albumName);

        return GetResponse.generate(response.getStatus(), response);
    }
}
