package com.codegym.zing.service;
import com.codegym.zing.model.Comment;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;

import java.util.List;

public interface CommentService extends GeneralService<Comment> {
    void delete(Long id);
    Iterable<Comment> findOrderByComment();
    List<Comment> findAllBySong(Song song);
    List<Comment> findAllByPlaylist(Playlist playlist);
    List<Comment> findAllByUser(User user);
}
