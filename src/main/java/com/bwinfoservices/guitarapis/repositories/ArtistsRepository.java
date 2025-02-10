package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Artists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistsRepository extends JpaRepository<Artists, Integer> {
    Optional<Artists> findByArtistName(String artistName);

    @Query("SELECT a FROM Artists a WHERE a.artistName <> '' ORDER BY a.artistName")
    List<Artists> findAllNonEmpty();

    @Query("SELECT a.artistName from Artists a ORDER BY 1")
    List<String> listAllArtists();

    @Query("SELECT a.artistName FROM Artists a, ArtistsUsed au WHERE a.id = au.artistId AND au.songId = :songId")
    List<String> listBySongId(@Param("songId") Integer songId);
}
