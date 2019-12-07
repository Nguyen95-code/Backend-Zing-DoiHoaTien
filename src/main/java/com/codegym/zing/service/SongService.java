package com.codegym.zing.service;

import com.codegym.zing.model.Song;

public interface SongService extends GeneralService<Song>{
    void delete(Long id);
    Iterable<Song> findOrOrderByCreateDate();

}
