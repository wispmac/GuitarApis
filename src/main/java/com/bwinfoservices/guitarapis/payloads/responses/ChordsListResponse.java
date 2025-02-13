package com.bwinfoservices.guitarapis.payloads.responses;

import com.bwinfoservices.guitarapis.dtos.ChordsDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChordsListResponse {
    private String status;

    private List<ChordsDto> chordsList;
}
