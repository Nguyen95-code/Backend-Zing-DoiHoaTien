package com.codegym.zing.controller;

import com.codegym.zing.model.Comment;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.User;
import com.codegym.zing.service.CommentService;
import com.codegym.zing.service.PlaylistService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/playlists/{playlist_id}")
@RestController
public class CommentPlaylistRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PlaylistService playlistService;

    @ModelAttribute("userCurent")
    public User getUserCurent() {
        return userService.getCurrentUser();
    }
    @GetMapping("comments")
    public ResponseEntity<List<Comment>> listCommentSong(@PathVariable Long song_id){
        Playlist playlist = playlistService.findById(song_id);
        if (playlist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comment> comments = commentService.findAllByPlaylist(playlist);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Long playlist_id){
        comment.setUser(getUserCurent());
        Playlist playlist = playlistService.findById(playlist_id);
        comment.setPlaylist(playlist);
        commentService.save(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PutMapping("/comments")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable Long playlist_id) {
        Playlist playlist = playlistService.findById(playlist_id);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @PathVariable Long playlist_id){
        Playlist playlist = playlistService.findById(playlist_id);
        Comment comment = commentService.findById(id);
        if (comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
