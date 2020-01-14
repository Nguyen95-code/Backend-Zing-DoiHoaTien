package com.codegym.zing.repository;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("select s from Song s order by s.createDate desc")
    Iterable<Song> findOrderByCreateDate();
    List<Song> findAllByUser(User user);
    @Query("select s from Song s order by s.views desc")
    Iterable<Song> findOrderByViews();
    List<Song> findAllByNameContaining(String name);
}
