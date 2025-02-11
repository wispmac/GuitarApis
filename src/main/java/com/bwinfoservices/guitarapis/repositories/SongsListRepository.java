package com.bwinfoservices.guitarapis.repositories;

import com.bwinfoservices.guitarapis.entities.SongsList;
import com.bwinfoservices.guitarapis.entities.SongsListId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongsListRepository extends JpaRepository<SongsList, SongsListId> {
    Optional<SongsList> findById_SongNum(String songNum);
}