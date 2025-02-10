package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Albums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlbumsRepository extends JpaRepository<Albums, Integer> {
    Optional<Albums> findByAlbumName(String albumName);

    @Query("SELECT a FROM Albums a WHERE a.albumName <> '' ORDER by a.albumName")
    List<Albums> findAllNonEmpty();

    @Query("SELECT a.albumName FROM Albums a ORDER BY 1")
    List<String> listAllAlbums();

    @Query("SELECT DISTINCT a.releaseYear FROM Albums a ORDER BY 1")
    List<Integer> listAllReleaseYears();

    @Query("SELECT a.releaseYear FROM Albums a WHERE a.albumName = :albumName")
    Integer getReleaseYear(@Param("albumName") String albumName);
}
