package com.codegym.zing.service;

import com.codegym.zing.model.Song;

public interface SongService {
    Iterable<Song> findAll();
    void save(Song song);
    void delete(Long id);
    Song findById(Long id);
    Iterable<Song> findOrOrderByCreateDate();

}
