package com.codegym.zing.repository;

import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("select s from Song s order by s.createDate desc")
    Iterable<Song> findOrOrderByCreateDate();
    List<Song> findAllBySinger(Singer singer);
}
