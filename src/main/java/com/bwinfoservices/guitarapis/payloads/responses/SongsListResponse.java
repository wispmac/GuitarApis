package com.bwinfoservices.guitarapis.payloads.responses;

import com.bwinfoservices.guitarapis.entities.SongsList;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SongsListResponse {
    private String status;

    private Integer count;

    private List<SongsList> lstSongs;
}
