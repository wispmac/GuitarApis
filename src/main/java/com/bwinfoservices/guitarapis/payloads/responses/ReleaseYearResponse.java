package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReleaseYearResponse {
    private String status;
    private Integer releaseYear;
}
