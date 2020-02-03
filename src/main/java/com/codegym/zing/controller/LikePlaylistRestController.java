package com.codegym.zing.controller;

import com.codegym.zing.model.Like;
import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.User;
import com.codegym.zing.service.LikeService;
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
public class LikePlaylistRestController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private UserService userService;
    @ModelAttribute("userCurent")
    public User getUserCurent() {
        return userService.getCurrentUser();
    }

    @GetMapping("like")
    public ResponseEntity<List<Like>> lisePlaylist(@PathVariable Long playlist_id){
        Playlist playlist = playlistService.findById(playlist_id);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Like> likes = likeService.findAllByPlaylist(playlist);
        return new ResponseEntity<>(likes,HttpStatus.OK);
    }

    @GetMapping("/likes/users/{user_id}")
    public ResponseEntity<Boolean> checkUserLikePlaylist(@PathVariable Long playlist_id, @PathVariable Long user_id){
        Playlist playlist = playlistService.findById(playlist_id);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Like> likes = likeService.findAllByPlaylist(playlist);
        for (Like like: likes) {
            if (like.getUser().getId() == user_id) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
    @PostMapping("likes")
    public ResponseEntity<Like> createLikePlaylist(@RequestBody Like like,@PathVariable Long playlist_id){
        like.setUser(getUserCurent());
        Playlist playlist = playlistService.findById(playlist_id);
        like.setPlaylist(playlist);
        likeService.save(like);
        return new ResponseEntity<Like>(like,HttpStatus.OK);
    }
    @DeleteMapping("/likes")
    public ResponseEntity<Void> deleteLikePlaylist(@PathVariable Long playlist_id){
        Playlist playlist = playlistService.findById(playlist_id);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Like> likes = likeService.findAllByPlaylist(playlist);
        for (Like like: likes) {
            if (like.getUser().getId() == getUserCurent().getId()) {
                likeService.delete(like);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
