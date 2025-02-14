package com.bwinfoservices.guitarapis.controllers;

import com.bwinfoservices.guitarapis.commons.GetResponse;
import com.bwinfoservices.guitarapis.entities.Composers;
import com.bwinfoservices.guitarapis.payloads.responses.ComposerDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ComposersListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.SaveMasterResponse;
import com.bwinfoservices.guitarapis.services.ComposersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComposersController {
    private final ComposersService composersService;

    public ComposersController(ComposersService composersService) {
        this.composersService = composersService;
    }

    @GetMapping("/composers")
    public ResponseEntity<?> listComposers() {
        ComposersListResponse response = composersService.listAll();

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("/composers/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "composerId") Integer composerId) {
        ComposerDetailsResponse response = composersService.find(composerId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @GetMapping("composers/findByName")
    public ResponseEntity<?> findByName(@RequestParam(name = "composerName") String composerName) {
        ComposerDetailsResponse response = composersService.find(composerName);

        return GetResponse.generate(response.getStatus(), response);
    }

    @PostMapping("/composers/update")
    public ResponseEntity<?> updateComposer(@RequestBody Composers composer) {
        SaveMasterResponse response = composersService.save(composer);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/composers/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam(name = "composerId") Integer composerId) {
        ResponseMessage response = composersService.delete(composerId);

        return GetResponse.generate(response.getStatus(), response);
    }

    @DeleteMapping("/composers/deleteByName")
    public ResponseEntity<?> deleteByName(@RequestParam(name = "composerName") String composerName) {
        ResponseMessage response = composersService.delete(composerName);

        return GetResponse.generate(response.getStatus(), response);
    }
}
