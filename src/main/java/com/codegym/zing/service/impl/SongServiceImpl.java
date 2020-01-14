package com.codegym.zing.service.impl;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;
import com.codegym.zing.repository.SongRepository;
import com.codegym.zing.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public void delete(Long id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            songRepository.delete(song.get());
        }
    }

    @Override
    public Song findById(Long id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            return song.get();
        }
        return null;
    }

    @Override
    public Iterable<Song> findOrOrderByViews(){
        return songRepository.findOrderByViews();
    }

    @Override
    public List<Song> findAllByNameContaining(String name) {
        return songRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Song> findOrOrderByCreateDate() {
        return songRepository.findOrderByCreateDate();
    }

    @Override
    public List<Song> findAllBySinger(User user) {
        return songRepository.findAllByUser(user);
    }
}
