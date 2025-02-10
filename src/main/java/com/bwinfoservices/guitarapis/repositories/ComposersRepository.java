package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Composers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComposersRepository extends JpaRepository<Composers, Integer> {
    Optional<Composers> findByComposerName(String composerName);

    @Query("SELECT c from Composers c WHERE c.composerName <> '' ORDER BY c.composerName")
    List<Composers> findAllNonEmpty();

    @Query("SELECT c.composerName FROM Composers c ORDER BY 1")
    List<String> listAllComposers();
}
