package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.entities.Lyricists;
import com.bwinfoservices.guitarapis.payloads.responses.LyricistDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.LyricistsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.repositories.LyricistsRepository;
import com.bwinfoservices.guitarapis.services.LyricistsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LyricistsServiceImpl implements LyricistsService {
    private final LyricistsRepository lyricistsRepository;

    public LyricistsServiceImpl(LyricistsRepository lyricistsRepository) {
        this.lyricistsRepository = lyricistsRepository;
    }

    @Override
    public LyricistsListResponse listAll() {
        try {
            return new LyricistsListResponse("Success", lyricistsRepository.findAll());
        } catch (Exception e) {
            return new LyricistsListResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public LyricistDetailsResponse find(String lyricistName) {
        try {
            Optional<Lyricists> lyricist = lyricistsRepository.findByLyricistName(lyricistName);

            return lyricist.map(l -> new LyricistDetailsResponse("Success", l)).orElseGet(() -> new LyricistDetailsResponse("Not Found", null));
        } catch (Exception e) {
            return new LyricistDetailsResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public LyricistDetailsResponse find(Integer lyricistId) {
        try {
            Optional<Lyricists> lyricist = lyricistsRepository.findById(lyricistId);

            return lyricist.map(l -> new LyricistDetailsResponse("Success", l)).orElseGet(() -> new LyricistDetailsResponse("Not Found", null));
        } catch (Exception e) {
            return new LyricistDetailsResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public SaveMasterResponse save(Lyricists lyricist) {
        try {
            Lyricists newLyricist = lyricistsRepository.findById(lyricist.getId()).orElse(new Lyricists());
            newLyricist.setLyricistName(lyricist.getLyricistName());

            newLyricist = lyricistsRepository.saveAndFlush(newLyricist);

            return new SaveMasterResponse("Success", newLyricist.getId());
        } catch (Exception e) {
            return new SaveMasterResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage delete(Integer lyricistId) {
        try {
            Optional<Lyricists> lyricist = lyricistsRepository.findById(lyricistId);

            if (lyricist.isPresent()) {
                lyricistsRepository.delete(lyricist.get());
                return new ResponseMessage("Success");
            }

            return new ResponseMessage("Not Found");
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseMessage delete(String lyricistName) {
        try {
            Optional<Lyricists> lyricist = lyricistsRepository.findByLyricistName(lyricistName);

            if (lyricist.isPresent()) {
                lyricistsRepository.delete(lyricist.get());
                return new ResponseMessage("Success");
            }

            return new ResponseMessage("Not Found");
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }
}
