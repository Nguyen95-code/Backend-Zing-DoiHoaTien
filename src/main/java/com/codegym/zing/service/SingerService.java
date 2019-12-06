package com.codegym.zing.service;

import com.codegym.zing.model.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(Long id);
    void save(Singer singer);
}
