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
@Table(name = "musicFiles")
public class MusicFiles {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "songId")
    private Integer songId;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileType")
    private String fileType;

    @Column(name = "fileSize")
    private Long fileSize;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicFiles that = (MusicFiles) o;
        return Objects.equals(songId, that.songId) && Objects.equals(fileName, that.fileName) && Objects.equals(fileType, that.fileType) && Objects.equals(fileSize, that.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, fileName, fileType, fileSize);
    }

    @Override
    public String toString() {
        return "MusicFiles{" +
                "id=" + id +
                ", songId=" + songId +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
