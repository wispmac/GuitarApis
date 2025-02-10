package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.ChordsList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChordsListRepository extends JpaRepository<ChordsList, Integer> {
    List<ChordsList> findByChordName(String chordName);
}
