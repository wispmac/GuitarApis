package com.bwinfoservices.guitarapis.payloads.responses;

import com.bwinfoservices.guitarapis.entities.Artists;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArtistsListResponse {
    private String status;

    private Integer count;

    private List<Artists> artistsList;
}
