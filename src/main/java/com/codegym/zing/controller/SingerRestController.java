package com.codegym.zing.controller;

import com.codegym.zing.model.*;
import com.codegym.zing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SingerRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SongService songService;

    @GetMapping("/singers")
    public ResponseEntity<List<User>> findAllSingers(){
        List<User> singers = userService.findAllByRoleSinger();
        return new ResponseEntity<>(singers, HttpStatus.OK);
    }

    @GetMapping("/singers/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!user.getRoles().getName().equals("ROLE_SINGER")){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/singers/{id}/albums")
    public ResponseEntity<List<Album>> findAllAlbum(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Album> albums = albumService.findAllBySinger(user);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/singers/{id}/playlists")
    public ResponseEntity<List<Playlist>> findAllPlaylist(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Playlist> playlists = playlistService.findAllByUser(user);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/singers/{id}/songs")
    public ResponseEntity<List<Song>> findAllSong(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Song> songs = songService.findAllBySinger(user);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
