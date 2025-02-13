package com.bwinfoservices.guitarapis.payloads.responses;

import com.bwinfoservices.guitarapis.dtos.ChordsDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChordDetailsResponse {
    private String status;

    private ChordsDto chordDetails;
}
