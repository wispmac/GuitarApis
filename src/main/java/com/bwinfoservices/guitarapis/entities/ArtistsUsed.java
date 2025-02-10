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
@Table(name = "artistsUsed")
public class ArtistsUsed {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "songId")
    private Integer songId;

    @Column(name = "artistId")
    private Integer artistId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistsUsed that = (ArtistsUsed) o;
        return Objects.equals(songId, that.songId) && Objects.equals(artistId, that.artistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, artistId);
    }

    @Override
    public String toString() {
        return "ArtistsUsed{" +
                "id=" + id +
                ", songId=" + songId +
                ", artistId=" + artistId +
                '}';
    }
}
