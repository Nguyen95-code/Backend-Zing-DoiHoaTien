package com.codegym.zing.service.impl;

import com.codegym.zing.model.Comment;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import com.codegym.zing.repository.CommentRepository;
import com.codegym.zing.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void delete(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()){
            commentRepository.delete(comment.get());
        }
    }

    @Override
    public Iterable<Comment> findOrderByComment() {
        return commentRepository.findOrderByComment();
    }

    @Override
    public List<Comment> findAllBySong(Song song) {
        return commentRepository.findAllBySong(song);
    }

    @Override
    public List<Comment> findAllByPlaylist(Playlist playlist) {
        return commentRepository.findAllByPlaylist(playlist);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            return comment.get();
        }
        return null;
    }
}
