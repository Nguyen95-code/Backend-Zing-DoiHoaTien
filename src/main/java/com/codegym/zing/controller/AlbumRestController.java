package com.codegym.zing.controller;

import com.codegym.zing.model.Album;
import com.codegym.zing.model.Song;
import com.codegym.zing.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AlbumRestController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> findAll(){
        List<Album> albums = albumService.findAll();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping("/albums")
    public ResponseEntity<Void> save(@RequestBody Album album){
        albumService.save(album);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/albums")
    public ResponseEntity<Void> edit(@RequestBody Album album){
        albumService.save(album);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Album album = albumService.findById(id);
        if (album == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        albumService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/albums/{albumId}")
    public ResponseEntity<Void> addSong(@PathVariable Long albumId, @RequestBody Song song){
        Album album = albumService.findById(albumId);
        if (album == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        albumService.addSong(albumId, song.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/albums/{albumId}/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long albumId, @PathVariable Long songId){
        Album album = albumService.findById(albumId);
        if (album == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        albumService.deleteSong(albumId, songId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/albums/{albumId}")
    public ResponseEntity<Album> findById(@PathVariable Long albumId){
        Album album = albumService.findById(albumId);
        if (album == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @GetMapping("/albums/{albumId}/songs")
    public ResponseEntity<Set<Song>> getAllSong(@PathVariable Long albumId){
        Album album = albumService.findById(albumId);
        if (album == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Set<Song> songs = album.getSongList();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
