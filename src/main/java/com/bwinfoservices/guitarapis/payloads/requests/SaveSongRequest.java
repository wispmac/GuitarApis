package com.bwinfoservices.guitarapis.payloads.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveSongRequest {
    private Integer id;

    private String songNum;

    private String songTitle;

    private String dtLearnt;

    private String artistName;

    private String albumName;

    private Integer releaseYear;

    private String composerName;

    private String lyricistName;

    private Integer capoInFret;

    private String chordsUsed;
}
