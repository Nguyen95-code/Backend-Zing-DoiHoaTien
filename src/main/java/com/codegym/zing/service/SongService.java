package com.codegym.zing.service;

import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;

import java.util.List;

public interface SongService extends GeneralService<Song>{
    void delete(Long id);
    Iterable<Song> findOrOrderByCreateDate();
    List<Song> findAllBySinger(Singer singer);
}
