package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongsRepository extends JpaRepository<Songs, Integer> {
    Optional<Songs> findBySongNum(String songNum);

    List<Songs> findByAlbumId(Integer albumId);

    List<Songs> findByComposerId(Integer composerId);

    List<Songs> findByLyricistId(Integer lyricistId);

    @Query("SELECT s.id FROM Songs s WHERE s.songNum = :songNum")
    Integer findIdBySongNum(@Param("songNum") String songNum);

    @Query("SELECT s.songNum FROM Songs s ORDER BY 1")
    List<String> listAllSongNums();
    
    @Query("SELECT s.songNum FROM Songs s WHERE s.songNum > :fromSongNum")
    List<String> listToSongNums(@Param("fromSongNum") String fromSongNum);

    @Query("SELECT MAX(s.songNum) FROM Songs s")
    String getLastSongNum();
}
