package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.ArtistsUsed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SuppressWarnings("unused")
public interface ArtistsUsedRepository extends JpaRepository<ArtistsUsed, Integer> {
    List<ArtistsUsed> findBySongId(Integer songId);

    List<ArtistsUsed> findByArtistId(Integer artistId);
}
