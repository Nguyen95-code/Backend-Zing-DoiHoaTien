package com.codegym.zing.controller;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlaylistRestController {
    @Autowired
    private PlaylistService playlistService;
    @GetMapping("/playlists")
    public ResponseEntity<List<Playlist>> findAll(){
        List<Playlist> playlists = playlistService.findAll();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping("/playlists")
    public ResponseEntity<Void> savePlaylist(@RequestBody Playlist playlist){
        playlistService.save(playlist);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/playlists")
    public ResponseEntity<Void> editPlaylist(@RequestBody Playlist playlist){
        playlistService.save(playlist);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id){
        Playlist playlist = playlistService.findById(id);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/playlists/{id}")
    public ResponseEntity<Playlist> findById(@PathVariable Long id){
        Playlist playlist = playlistService.findById(id);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }
}
