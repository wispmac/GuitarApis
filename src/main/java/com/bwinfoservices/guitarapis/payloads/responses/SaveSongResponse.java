package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveSongResponse {
    private String status;

    private Integer songId;

    private Integer albumId;

    private Integer composerId;

    private Integer lyricistId;

    private List<Integer> artistIds;

    private List<Integer> chordIds;
}
