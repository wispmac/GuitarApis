package com.bwinfoservices.guitarapis.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetResponse {
    public static ResponseEntity<?> generate(String status, Object response) {
        return switch (status) {
            case Constants.SUCCESS -> ResponseEntity.ok(response);
            case Constants.NOT_FOUND -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
            case Constants.INVALID_NOTE -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        };
    }
}
