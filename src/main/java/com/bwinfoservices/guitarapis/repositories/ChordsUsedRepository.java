package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.ChordsUsed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SuppressWarnings("unused")
public interface ChordsUsedRepository extends JpaRepository<ChordsUsed, Integer> {
    List<ChordsUsed> findBySongId(Integer songId);

    List<ChordsUsed> findByChordId(Integer chordId);
}
