package com.codegym.zing.service;


import com.codegym.zing.model.Like;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;

import java.util.List;

public interface LikeService extends GeneralService<Like> {
    List<Like> findAllBySong(Song song);
    List<Like> findAllByPlaylist(Playlist playlist);
    void delete(Like like);
}
