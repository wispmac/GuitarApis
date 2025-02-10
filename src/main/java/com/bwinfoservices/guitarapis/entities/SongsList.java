package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Getter
@Setter
@ToString
@Entity
@Immutable
@Table(name = "songsList")
public class SongsList {
    @EmbeddedId
    private SongsListId id;

    @Size(max = 255)
    @NotNull
    @Column(name = "songTitle", nullable = false)
    private String songTitle;

    @Size(max = 255)
    @Column(name = "dtLearnt")
    private String dtLearnt;

    @Size(max = 255)
    @Column(name = "artistName")
    private String artistName;

    @Size(max = 255)
    @NotNull
    @Column(name = "albumName", nullable = false)
    private String albumName;

    @Size(max = 11)
    @Column(name = "releaseYear", length = 11)
    private String releaseYear;

    @Size(max = 255)
    @Column(name = "composerName")
    private String composerName;

    @Size(max = 255)
    @NotNull
    @Column(name = "lyricistName", nullable = false)
    private String lyricistName;

    @Size(max = 11)
    @NotNull
    @Column(name = "capoInFret", nullable = false, length = 11)
    private String capoInFret;

    @Size(max = 255)
    @Column(name = "chordsUsed")
    private String chordsUsed;

    @Column(name = "audioId")
    private Integer audioId;

    @Column(name = "pdfId")
    private Integer pdfId;
}