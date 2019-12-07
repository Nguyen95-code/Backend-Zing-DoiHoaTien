package com.codegym.zing.service.impl;

import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;
import com.codegym.zing.repository.SingerRepository;
import com.codegym.zing.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Singer findById(Long id) {
        Optional<Singer> singer = singerRepository.findById(id);
        if (singer.isPresent()) {
            return singer.get();
        }
        return null;
    }

    @Override
    public void save(Singer singer) {
        singerRepository.save(singer);
    }

}
