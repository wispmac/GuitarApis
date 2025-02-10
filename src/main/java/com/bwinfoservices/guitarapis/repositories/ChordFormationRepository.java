package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.ChordFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChordFormationRepository extends JpaRepository<ChordFormation, Integer> {
    List<ChordFormation> findByChordId(Integer chordId);
}
