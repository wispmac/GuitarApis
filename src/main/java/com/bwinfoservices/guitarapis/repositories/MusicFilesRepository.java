package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.MusicFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MusicFilesRepository extends JpaRepository<MusicFiles, Integer> {
    List<MusicFiles> findBySongId(Integer songId);

    @Query("SELECT m FROM MusicFiles m WHERE m.songId = :songId AND m.fileType <> 'application/pdf'")
    Optional<MusicFiles> findAudioFile(@Param("songId") Integer songId);

    @Query("SELECT m FROM MusicFiles m WHERE m.songId = :songId AND m.fileType = 'application/pdf'")
    Optional<MusicFiles> findPdfFile(@Param("songId") Integer songId);
}
