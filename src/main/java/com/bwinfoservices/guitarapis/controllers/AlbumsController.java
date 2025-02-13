package com.bwinfoservices.guitarapis.controllers;

import com.bwinfoservices.guitarapis.defs.Constants;
import com.bwinfoservices.guitarapis.entities.Albums;
import com.bwinfoservices.guitarapis.payloads.responses.AlbumDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.AlbumsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.services.AlbumsService;
import org.springframework.http.HttpStatus;
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

        if (response.getStatus().equals(Constants.SUCCESS)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @GetMapping("/albums/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "albumId") Integer albumId) {
        AlbumDetailsResponse response = albumsService.find(albumId);

        return switch (response.getStatus()) {
            case Constants.SUCCESS -> ResponseEntity.ok(response);
            case Constants.NOT_FOUND -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        };
    }

    @GetMapping("/albums/findByName")
    public ResponseEntity<?> findByName(@RequestParam(name = "albumName") String albumName) {
        AlbumDetailsResponse response = albumsService.find(albumName);

        return switch (response.getStatus()) {
            case Constants.SUCCESS -> ResponseEntity.ok(response);
            case Constants.NOT_FOUND -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        };
    }

    @PostMapping("/albums/update")
    public ResponseEntity<?> updateAlbum(@RequestBody Albums album) {
        SaveMasterResponse response = albumsService.save(album);

        if (response.getStatus().equals(Constants.SUCCESS)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/albums/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam(name = "albumId") Integer albumId) {
        ResponseMessage response = albumsService.delete(albumId);

        return switch (response.getStatus()) {
            case Constants.SUCCESS -> ResponseEntity.ok(response);
            case Constants.NOT_FOUND -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        };
    }

    @DeleteMapping("/albums/deleteByName")
    public ResponseEntity<?> deleteByName(@RequestParam(name = "albumName") String albumName) {
        ResponseMessage response = albumsService.delete(albumName);

        return switch (response.getStatus()) {
            case Constants.SUCCESS -> ResponseEntity.ok(response);
            case Constants.NOT_FOUND -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        };
    }
}
