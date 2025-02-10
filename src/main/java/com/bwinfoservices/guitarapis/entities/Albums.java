package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "albums")
public class Albums {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "albumName")
    private String albumName;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Albums albums = (Albums) o;
        return Objects.equals(albumName, albums.albumName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumName);
    }

    @Override
    public String toString() {
        return "Albums{" +
                "id=" + id +
                ", albumName='" + albumName + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
