package com.codegym.zing.controller;

import com.codegym.zing.model.Like;
import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;
import com.codegym.zing.service.LikeService;
import com.codegym.zing.service.SongService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/songs/{song_id}")
@RestController
public class LikeSongRestController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private SongService songService;
    @Autowired
    private UserService userService;
    @ModelAttribute("userCurent")
    public User getUserCurent() {
        return userService.getCurrentUser();
    }

    @GetMapping("likes")
    public ResponseEntity<List<Like>> likeSong(@PathVariable Long song_id){
        Song song =songService.findById(song_id);
        if (song == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Like> likes = likeService.findAllBySong(song);
        return new ResponseEntity<>(likes,HttpStatus.OK);
    }

    @GetMapping("/likes/users/{user_id}")
    public ResponseEntity<Boolean> checkUserLikeSong(@PathVariable Long song_id, @PathVariable Long user_id){
        Song song =songService.findById(song_id);
        if (song == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Like> likes = likeService.findAllBySong(song);
        for (Like like: likes) {
            if (like.getUser().getId() == user_id) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
    @PostMapping("likes")
    public ResponseEntity<Like> createLikeSong(@RequestBody Like like,@PathVariable Long song_id){
        like.setUser(getUserCurent());
        Song song = songService.findById(song_id);
        like.setSong(song);
        likeService.save(like);
        return new ResponseEntity<Like>(like,HttpStatus.OK);
    }
    @DeleteMapping("/likes")
    public ResponseEntity<Void> deleteLikeSong(@PathVariable Long song_id){
        Song song =songService.findById(song_id);
        if (song == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Like> likes = likeService.findAllBySong(song);
        for (Like like: likes) {
            if (like.getUser().getId() == getUserCurent().getId()) {
                likeService.delete(like);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
