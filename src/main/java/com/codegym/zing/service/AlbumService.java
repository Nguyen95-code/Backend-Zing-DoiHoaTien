package com.codegym.zing.service;

import com.codegym.zing.model.Album;
import com.codegym.zing.model.Song;

public interface AlbumService extends GeneralService<Album> {
    void delete(Long id);
    void addSong(Long albumId, Long songId);
    void deleteSong(Long albumId, Long songId);
}
