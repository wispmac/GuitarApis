package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Chords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChordsRepository extends JpaRepository<Chords, Integer> {
    Optional<Chords> findByChordName(String chordName);

    @Query("SELECT c.chordName FROM Chords c ORDER BY 1")
    List<String> listAllChords();

    @Query("SELECT c.chordName FROM Chords c, ChordsUsed cu WHERE c.id = cu.chordId AND cu.songId = :songId")
    List<String> listBySongId(@Param("songId") Integer songId);
}
