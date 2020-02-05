package com.codegym.zing.controller;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;
import com.codegym.zing.service.PlaylistService;
import com.codegym.zing.service.SongService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
public class PlaylistRestController {
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SongService songService;
    @Autowired
    private UserService userService;

    @ModelAttribute("userCurent")
    public User getUserCurrent(){
        return userService.getCurrentUser();
    }

    @PostMapping("/playlists")
    public ResponseEntity<Void> savePlaylist(@RequestBody Playlist playlist){
        playlist.setUser(getUserCurrent());
        playlist.setViews(0);
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

    @PostMapping("/playlists/{playlistId}")
    public ResponseEntity<Void> addSong(@PathVariable Long playlistId, @RequestBody Song song){
        Playlist playlist = playlistService.findById(playlistId);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.addSong(playlistId, song.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/playlists/{playlistId}/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") Long songId){
        Playlist playlist = playlistService.findById(playlistId);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.deleteSong(playlistId, songId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/playlists")
    public ResponseEntity<List<Playlist>> findAllPlaylist(@RequestParam("username") Optional<String> username){
        List<Playlist> playlists;
        if (username.isPresent()) {
            User user = userService.findByUsername(username.get());
            playlists = playlistService.findAllByUser(user);
        } else playlists = playlistService.findAll();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/playlists/{playlistId}")
    public ResponseEntity<Playlist> findById(@PathVariable Long playlistId){
        Playlist playlist = playlistService.findById(playlistId);
        if (playlist == null || playlist.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @GetMapping("/playlists/{playlistId}/songs")
    public ResponseEntity<Set<Song>> findAllSongs(@PathVariable Long playlistId){
        Playlist playlist = playlistService.findById(playlistId);
        if (playlist == null || playlist.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlist.getSongList(), HttpStatus.OK);
    }
    @GetMapping("/playlists/views")
    public ResponseEntity<List<Playlist>> playlistTopView(){
        List<Playlist> playlists = (List<Playlist>) playlistService.findOrOrderByViews();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

}
