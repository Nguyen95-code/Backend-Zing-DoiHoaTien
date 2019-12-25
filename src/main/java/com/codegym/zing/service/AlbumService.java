package com.codegym.zing.service;

import com.codegym.zing.model.Album;
import com.codegym.zing.model.User;

import java.util.List;

public interface AlbumService extends GeneralService<Album> {
    void delete(Long id);
    void addSong(Long albumId, Long songId);
    void deleteSong(Long albumId, Long songId);
    List<Album> findAllBySinger(User user);
}
