package com.codegym.zing.service.impl;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;
import com.codegym.zing.repository.PlaylistRepository;
import com.codegym.zing.repository.SongRepository;
import com.codegym.zing.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private SongRepository songRepository;

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

    @Override
    public void addSong(Long playlistId, Long songId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            Set<Song> songs = playlist.get().getSongList();
            Optional<Song> song = songRepository.findById(songId);
            if (song.isPresent()) {
                songs.add(song.get());
                playlist.get().setSongList(songs);
                playlistRepository.save(playlist.get());
            }
        }
    }

    @Override
    public void deleteSong(Long playlistId, Long songId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            Set<Song> songs = playlist.get().getSongList();
            Optional<Song> song = songRepository.findById(songId);
            if (song.isPresent()) {
                songs.remove(song.get());
                playlist.get().setSongList(songs);
                playlistRepository.save(playlist.get());
            }
        }
    }

    @Override
    public List<Playlist> findAllBySinger(Singer singer) {
        return playlistRepository.findAllBySinger(singer);
    }

    @Override
    public List<Playlist> findAllByUser(User user) {
        return playlistRepository.findAllByUser(user);
    }


}
