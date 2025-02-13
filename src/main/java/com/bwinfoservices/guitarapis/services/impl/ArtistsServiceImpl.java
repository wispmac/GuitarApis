package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.entities.Artists;
import com.bwinfoservices.guitarapis.payloads.responses.ArtistDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ArtistsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.repositories.ArtistsRepository;
import com.bwinfoservices.guitarapis.services.ArtistsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ArtistsServiceImpl implements ArtistsService {
    private final ArtistsRepository artistsRepository;

    public ArtistsServiceImpl(ArtistsRepository artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    @Override
    public ArtistsListResponse listAll() {
        try {
            return new ArtistsListResponse("Success", artistsRepository.findAll());
        } catch (Exception e) {
            return new ArtistsListResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ArtistDetailsResponse find(String artistName) {
        try {
            Optional<Artists> artist = artistsRepository.findByArtistName(artistName);

            return artist.map(a -> new ArtistDetailsResponse("Success", a)).orElseGet(() -> new ArtistDetailsResponse("Not Found", null));
        } catch (Exception e) {
            return new ArtistDetailsResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ArtistDetailsResponse find(Integer artistId) {
        try {
            Optional<Artists> artist = artistsRepository.findById(artistId);

            return artist.map(a -> new ArtistDetailsResponse("Success", a)).orElseGet(() -> new ArtistDetailsResponse("Not Found", null));
        } catch (Exception e) {
            return new ArtistDetailsResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public SaveMasterResponse save(Artists artist) {
        try {
            Artists newArtist = artistsRepository.findById(artist.getId()).orElse(new Artists());
            newArtist.setArtistName(artist.getArtistName());

            newArtist = artistsRepository.saveAndFlush(newArtist);

            return new SaveMasterResponse("Success", newArtist.getId());
        } catch (Exception e) {
            return new SaveMasterResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage delete(Integer artistId) {
        try {
            Optional<Artists> artist = artistsRepository.findById(artistId);

            if (artist.isPresent()) {
                artistsRepository.delete(artist.get());
                return new ResponseMessage("Success");
            }

            return new ResponseMessage("Not Found");
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseMessage delete(String artistName) {
        try {
            Optional<Artists> artist = artistsRepository.findByArtistName(artistName);

            if (artist.isPresent()) {
                artistsRepository.delete(artist.get());
                return new ResponseMessage("Success");
            }

            return new ResponseMessage("Not Found");
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }
}
