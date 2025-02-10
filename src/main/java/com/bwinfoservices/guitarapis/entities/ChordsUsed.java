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
@Table(name = "chordsUsed")
public class ChordsUsed {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "songId")
    private Integer songId;

    @Column(name = "chordId")
    private Integer chordId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChordsUsed that = (ChordsUsed) o;
        return Objects.equals(songId, that.songId) && Objects.equals(chordId, that.chordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, chordId);
    }

    @Override
    public String toString() {
        return "ChordsUsed{" +
                "id=" + id +
                ", songId=" + songId +
                ", chordId=" + chordId +
                '}';
    }
}
