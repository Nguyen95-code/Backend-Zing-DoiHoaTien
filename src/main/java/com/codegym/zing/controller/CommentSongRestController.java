package com.codegym.zing.controller;

import com.codegym.zing.model.Comment;
import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;
import com.codegym.zing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/songs/{song_id}")
@RestController
public class CommentSongRestController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private SongService songService;
    @Autowired
    private UserService userService;

    @ModelAttribute("userCurent")
    public User getUserCurent() {
        return userService.getCurrentUser();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> listCommentSong(@PathVariable Long song_id){
        Song song = songService.findById(song_id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comment> comments = commentService.findAllBySong(song);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Long song_id){
        comment.setUser(getUserCurent());
        Song song = songService.findById(song_id);
        comment.setSong(song);
        commentService.save(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
    @PutMapping("/comments")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable Long song_id) {
        Song song = songService.findById(song_id);
        comment.setSong(song);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @PathVariable Long song_id){
        Song song = songService.findById(song_id);
        Comment comment = commentService.findById(id);
        if (comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
