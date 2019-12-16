package com.codegym.zing.service;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;

import java.util.List;

public interface PlaylistService extends GeneralService<Playlist>{
    void delete(Long id);
    void addSong(Long playlistId, Long songId);
    void deleteSong(Long playlistId, Long songId);
    List<Playlist> findAllBySinger(Singer singer);
    List<Playlist> findAllByUser(User user);
}
