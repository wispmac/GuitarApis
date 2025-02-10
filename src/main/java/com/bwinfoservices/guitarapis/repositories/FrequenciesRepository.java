package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Frequencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FrequenciesRepository extends JpaRepository<Frequencies, Integer> {
    @Query("SELECT f.frequency FROM Frequencies f WHERE f.stringNum = :sn AND f.fretNum = :fn AND f.noteId = :ni")
    Integer findFrequency(@Param("sn") Integer stringNum, @Param("fn") Integer fretNum, @Param("ni") Integer noteId);

    @Query("SELECT f.fretNum FROM Frequencies f WHERE f.noteId = :note AND f.stringNum = :stringNum")
    List<Integer> findFrets(@Param("note") Integer noteId, @Param("stringNum") Integer stringNum);
}
