package com.codegym.zing.service.impl;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import com.codegym.zing.repository.PlaylistRepository;
import com.codegym.zing.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public void save(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public Playlist findById(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (playlist.isPresent()){
            return playlist.get();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (playlist.isPresent()) {
            playlistRepository.delete(playlist.get());
        }
    }
}
