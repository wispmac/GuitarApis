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
@Table(name = "artists")
public class Artists {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "artistName")
    private String artistName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;
        return Objects.equals(artistName, artists.artistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistName);
    }

    @Override
    public String toString() {
        return "Artists{" +
                "id=" + id +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
