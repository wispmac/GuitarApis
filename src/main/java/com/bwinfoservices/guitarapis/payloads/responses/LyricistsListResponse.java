package com.bwinfoservices.guitarapis.payloads.responses;

import com.bwinfoservices.guitarapis.entities.Lyricists;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LyricistsListResponse {
    private String status;

    private Integer count;

    private List<Lyricists> lyricistList;
}
