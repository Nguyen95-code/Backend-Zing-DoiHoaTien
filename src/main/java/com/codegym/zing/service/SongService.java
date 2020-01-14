package com.codegym.zing.service;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;

import java.util.List;

public interface SongService extends GeneralService<Song>{
    void delete(Long id);
    Iterable<Song> findOrOrderByCreateDate();
    List<Song> findAllBySinger(User user);
    Iterable<Song> findOrOrderByViews();
    List<Song> findAllByNameContaining(String name);
}
