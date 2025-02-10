package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Lyricists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LyricistsRepository extends JpaRepository<Lyricists, Integer> {
    Optional<Lyricists> findByLyricistName(String lyricistName);

    @Query("SELECT l FROM Lyricists l WHERE l.lyricistName <> '' ORDER BY l.lyricistName")
    List<Lyricists> findAllNonEmpty();

    @Query("SELECT l.lyricistName FROM Lyricists l ORDER BY 1")
    List<String> listAllLyricists();
}
