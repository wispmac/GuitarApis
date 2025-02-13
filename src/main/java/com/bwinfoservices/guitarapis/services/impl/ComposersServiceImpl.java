package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.defs.Constants;
import com.bwinfoservices.guitarapis.entities.Composers;
import com.bwinfoservices.guitarapis.payloads.responses.ComposerDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ComposerListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.repositories.ComposersRepository;
import com.bwinfoservices.guitarapis.services.ComposersService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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
            List<Composers> composers = composersRepository.findAll();
            return new ComposerListResponse(Constants.SUCCESS, composers.size(), composers);
        } catch (Exception e) {
            return new ComposerListResponse(Constants.ERROR + e.getMessage(), null, null);
        }
    }

    @Override
    public ComposerDetailsResponse find(String composerName) {
        try {
            Optional<Composers> composer = composersRepository.findByComposerName(composerName);

            return composer.map(c -> new ComposerDetailsResponse(Constants.SUCCESS, c)).orElseGet(() -> new ComposerDetailsResponse(Constants.NOT_FOUND, null));
        } catch (Exception e) {
            return new ComposerDetailsResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public ComposerDetailsResponse find(Integer composerId) {
        try {
            Optional<Composers> composer = composersRepository.findById(composerId);

            return composer.map(c -> new ComposerDetailsResponse(Constants.SUCCESS, c)).orElseGet(() -> new ComposerDetailsResponse(Constants.NOT_FOUND, null));
        } catch (Exception e) {
            return new ComposerDetailsResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public SaveMasterResponse save(Composers composer) {
        try {
            Composers newComposer = composersRepository.findById(composer.getId()).orElse(new Composers());
            newComposer.setComposerName(composer.getComposerName());

            newComposer = composersRepository.saveAndFlush(newComposer);

            return new SaveMasterResponse(Constants.SUCCESS, newComposer.getId());
        } catch (Exception e) {
            return new SaveMasterResponse(Constants.ERROR + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage delete(Integer composerId) {
        try {
            Optional<Composers> composer = composersRepository.findById(composerId);

            if (composer.isPresent()) {
                composersRepository.delete(composer.get());
                return new ResponseMessage(Constants.SUCCESS);
            }

            return new ResponseMessage(Constants.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseMessage(Constants.ERROR + e.getMessage());
        }
    }

    @Override
    public ResponseMessage delete(String composerName) {
        try {
            Optional<Composers> composer = composersRepository.findByComposerName(composerName);

            if (composer.isPresent()) {
                composersRepository.delete(composer.get());
                return new ResponseMessage(Constants.SUCCESS);
            }

            return new ResponseMessage(Constants.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseMessage(Constants.ERROR + e.getMessage());
        }
    }
}
