package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LastSongNumResponse {
    private String status;
    private String lastSongNum;
}
