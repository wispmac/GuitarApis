package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Immutable
@Table(name = "songsList")
public class SongsList {
    @EmbeddedId
    private SongsListId id;

    @Column(name = "songTitle", nullable = false)
    private String songTitle;

    @Column(name = "dtLearnt")
    private String dtLearnt;

    @Column(name = "artistName")
    private String artistName;

    @Column(name = "albumName", nullable = false)
    private String albumName;

    @Column(name = "releaseYear", length = 11)
    private String releaseYear;

    @Column(name = "composerName")
    private String composerName;

    @Column(name = "lyricistName", nullable = false)
    private String lyricistName;

    @Column(name = "capoInFret", nullable = false, length = 11)
    private String capoInFret;

    @Column(name = "chordsUsed")
    private String chordsUsed;

    @Column(name = "audioId")
    private Integer audioId;

    @Column(name = "pdfId")
    private Integer pdfId;
}