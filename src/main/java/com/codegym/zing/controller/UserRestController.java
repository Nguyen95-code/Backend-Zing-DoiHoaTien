package com.codegym.zing.controller;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.User;
import com.codegym.zing.service.PlaylistService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/users")
    public ResponseEntity<Void> save(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/playlists")
    public ResponseEntity<List<Playlist>> findAllPlaylist(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Playlist> playlists = playlistService.findAllByUser(user);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }
}
