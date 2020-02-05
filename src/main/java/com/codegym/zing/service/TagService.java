package com.codegym.zing.service;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllBySong(Song song);
    Tag findById(Long id);
    void save(Tag tag);
}
