package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.entities.Composers;
import com.bwinfoservices.guitarapis.payloads.responses.ComposerDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ComposerListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.repositories.ComposersRepository;
import com.bwinfoservices.guitarapis.services.ComposersService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ComposersServiceImpl implements ComposersService {
    private final ComposersRepository composersRepository;

    public ComposersServiceImpl(ComposersRepository composersRepository) {
        this.composersRepository = composersRepository;
    }

    @Override
    public ComposerListResponse listAll() {
        try {
            return new ComposerListResponse("Success", composersRepository.findAll());
        } catch (Exception e) {
            return new ComposerListResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ComposerDetailsResponse find(String composerName) {
        try {
            Optional<Composers> composer = composersRepository.findByComposerName(composerName);

            return composer.map(c -> new ComposerDetailsResponse("Success", c)).orElseGet(() -> new ComposerDetailsResponse("Not Found", null));
        } catch (Exception e) {
            return new ComposerDetailsResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ComposerDetailsResponse find(Integer composerId) {
        try {
            Optional<Composers> composer = composersRepository.findById(composerId);

            return composer.map(c -> new ComposerDetailsResponse("Success", c)).orElseGet(() -> new ComposerDetailsResponse("Not Found", null));
        } catch (Exception e) {
            return new ComposerDetailsResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public SaveMasterResponse save(Composers composer) {
        try {
            Composers newComposer = composersRepository.findById(composer.getId()).orElse(new Composers());
            newComposer.setComposerName(composer.getComposerName());

            newComposer = composersRepository.saveAndFlush(newComposer);

            return new SaveMasterResponse("Success", newComposer.getId());
        } catch (Exception e) {
            return new SaveMasterResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage delete(Integer composerId) {
        try {
            Optional<Composers> composer = composersRepository.findById(composerId);

            if (composer.isPresent()) {
                composersRepository.delete(composer.get());
                return new ResponseMessage("Success");
            }

            return new ResponseMessage("Not Found");
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseMessage delete(String composerName) {
        try {
            Optional<Composers> composer = composersRepository.findByComposerName(composerName);

            if (composer.isPresent()) {
                composersRepository.delete(composer.get());
                return new ResponseMessage("Success");
            }

            return new ResponseMessage("Not Found");
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }
}
