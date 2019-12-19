package com.codegym.zing.controller;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Song;
import com.codegym.zing.service.PlaylistService;
import com.codegym.zing.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PlaylistRestController {
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SongService songService;
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

    @GetMapping("/playlists/{playlistId}/songs")
    public ResponseEntity<Set<Song>> findAllSongs(@PathVariable Long playlistId){
        Playlist playlist = playlistService.findById(playlistId);
        if (playlist == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(playlist.getSongList(), HttpStatus.OK);
    }
}
