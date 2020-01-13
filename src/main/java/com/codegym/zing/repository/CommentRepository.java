package com.codegym.zing.repository;

import com.codegym.zing.model.Comment;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select s from Comment s order by s.description desc")
    Iterable<Comment> findOrderByComment();

    List<Comment> findAllBySong(Song song);
    List<Comment> findAllByPlaylist(Playlist playlist);
}
