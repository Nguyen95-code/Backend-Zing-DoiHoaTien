package com.codegym.zing.repository;

import com.codegym.zing.model.Like;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllBySong(Song song);
    List<Like> findAllByPlaylist(Playlist playlist);
}
