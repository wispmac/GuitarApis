package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
    Optional<Notes> findByNoteName(String noteName);

    @Query("SELECT n.noteName FROM Notes n ORDER BY 1")
    List<String> listAllNotes();
}
