package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.commons.Constants;
import com.bwinfoservices.guitarapis.entities.Albums;
import com.bwinfoservices.guitarapis.payloads.responses.*;
import com.bwinfoservices.guitarapis.repositories.AlbumsRepository;
import com.bwinfoservices.guitarapis.services.AlbumsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlbumsServiceImpl implements AlbumsService {
    private final AlbumsRepository albumsRepository;

    public AlbumsServiceImpl(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @Override
    public AlbumsListResponse listAll() {
        try {
            List<Albums> albums = albumsRepository.findAll();
            return new AlbumsListResponse(Constants.SUCCESS, albums.size(), albums);
        } catch (Exception e) {
            return new AlbumsListResponse(Constants.ERROR + e.getMessage(), null, null);
        }
    }

    @Override
    public IntegerListResponse listReleaseYears() {
        try {
            List<Integer> lstReleaseYears = albumsRepository.listAllReleaseYears();
            return new IntegerListResponse(Constants.SUCCESS, lstReleaseYears.size(), lstReleaseYears);
        } catch (Exception e) {
            return new IntegerListResponse(Constants.ERROR + e.getMessage(), null, null);
        }
    }

    @Override
    public ReleaseYearResponse getReleaseYear(String albumName) {
        try {
            return new ReleaseYearResponse(Constants.SUCCESS, albumsRepository.getReleaseYear(albumName));
        } catch (Exception e) {
            return new ReleaseYearResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public AlbumDetailsResponse find(String albumName) {
        try {
            Optional<Albums> album = albumsRepository.findByAlbumName(albumName);

            return album.map(a -> new AlbumDetailsResponse(Constants.SUCCESS, a)).orElseGet(() -> new AlbumDetailsResponse(Constants.NOT_FOUND, null));

        } catch (Exception e) {
            return new AlbumDetailsResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public AlbumDetailsResponse find(Integer albumId) {
        try {
            Optional<Albums> album = albumsRepository.findById(albumId);

            return album.map(a -> new AlbumDetailsResponse(Constants.SUCCESS, a)).orElseGet(() -> new AlbumDetailsResponse(Constants.NOT_FOUND, null));

        } catch (Exception e) {
            return new AlbumDetailsResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public SaveMasterResponse save(Albums album) {
        try {
            Albums newAlbum = albumsRepository.findById(album.getId()).orElse(new Albums());
            newAlbum.setAlbumName(album.getAlbumName());
            newAlbum.setReleaseYear(album.getReleaseYear());

            newAlbum = albumsRepository.saveAndFlush(newAlbum);

            return new SaveMasterResponse(Constants.SUCCESS, newAlbum.getId());
        } catch (Exception e) {
            return new SaveMasterResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage delete(Integer albumId) {
        try {
            Optional<Albums> album = albumsRepository.findById(albumId);

            if (album.isPresent()) {
                albumsRepository.delete(album.get());
                return new ResponseMessage(Constants.SUCCESS);
            }

            return new ResponseMessage(Constants.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseMessage(Constants.ERROR + e.getMessage());
        }
    }

    @Override
    public ResponseMessage delete(String albumName) {
        try {
            Optional<Albums> album = albumsRepository.findByAlbumName(albumName);

            if (album.isPresent()) {
                albumsRepository.delete(album.get());
                return new ResponseMessage(Constants.SUCCESS);
            }

            return new ResponseMessage(Constants.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseMessage(Constants.ERROR + e.getMessage());
        }
    }
}
