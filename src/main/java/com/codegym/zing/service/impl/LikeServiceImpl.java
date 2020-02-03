package com.codegym.zing.service.impl;

import com.codegym.zing.model.Like;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import com.codegym.zing.repository.LikeRepository;
import com.codegym.zing.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Override
    public List<Like> findAllBySong(Song song) {
        return likeRepository.findAllBySong(song);
    }

    @Override
    public List<Like> findAllByPlaylist(Playlist playlist) {
        return likeRepository.findAllByPlaylist(playlist);
    }

    @Override
    public void delete(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public void save(Like like) {
        likeRepository.save(like);
    }

    @Override
    public Like findById(Long id) {
        Optional<Like> like = likeRepository.findById(id);
        if(like.isPresent()){
            return like.get();
        }
        return null;
    }
}
