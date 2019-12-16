package com.codegym.zing.service.impl;

import com.codegym.zing.model.Album;
import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;
import com.codegym.zing.repository.AlbumRepository;
import com.codegym.zing.repository.SongRepository;
import com.codegym.zing.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SongRepository songRepository;
    @Override
    public void delete(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()){
            albumRepository.delete(album.get());
        }
    }

    @Override
    public void addSong(Long albumId, Long songId) {
        Optional<Album> album = albumRepository.findById(albumId);
        if (album.isPresent()){
            Set<Song> songs = album.get().getSongList();
            Optional<Song> song = songRepository.findById(songId);
            if (song.isPresent()) {

                songs.add(song.get());

                album.get().setSongList(songs);
                albumRepository.save(album.get());
            }
        }
    }

    @Override
    public void deleteSong(Long albumId, Long songId) {
        Optional<Album> album = albumRepository.findById(albumId);
        if (album.isPresent()){
            Set<Song> songs = album.get().getSongList();
            Optional<Song> song = songRepository.findById(songId);
            if (song.isPresent()) {

                songs.remove(song.get());

                album.get().setSongList(songs);
                albumRepository.save(album.get());
            }
        }
    }

    @Override
    public List<Album> findAllBySinger(Singer singer) {
        return albumRepository.findAllBySinger(singer);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public Album findById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()){
            return album.get();
        }
        return null;
    }


}
