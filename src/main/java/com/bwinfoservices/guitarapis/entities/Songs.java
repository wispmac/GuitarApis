package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Songs {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "songNum")
    private String songNum;

    @Column(name = "songTitle")
    private String songTitle;

    @Column(name = "dtLearnt")
    private String dtLearnt;

    @Column(name = "albumId")
    private Integer albumId;

    @Column(name = "composerId")
    private Integer composerId;

    @Column(name = "lyricistId")
    private Integer lyricistId;

    @Column(name = "capoInFret")
    private Integer capoInFret;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Songs songs = (Songs) o;
        return Objects.equals(songNum, songs.songNum) && Objects.equals(songTitle, songs.songTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songNum, songTitle);
    }

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", songNum='" + songNum + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", dtLearnt='" + dtLearnt + '\'' +
                ", albumId=" + albumId +
                ", composerId=" + composerId +
                ", lyricistId=" + lyricistId +
                ", capoInFret=" + capoInFret +
                '}';
    }
}
