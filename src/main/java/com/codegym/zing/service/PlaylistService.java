package com.codegym.zing.service;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;

public interface PlaylistService extends GeneralService<Playlist>{
    void delete(Long id);
    void addSong(Long playlistId, Long songId);
    void deleteSong(Long playlistId, Long songId);
}
