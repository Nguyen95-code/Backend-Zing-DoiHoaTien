package com.codegym.zing.controller;

import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;
import com.codegym.zing.service.SingerService;
import com.codegym.zing.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class SongRestController {
    @Autowired
    private SongService songService;

    @Autowired
    private static SingerService singerService;

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> listSong(){
        List<Song> songs = (List<Song>) songService.findAll();
        return new ResponseEntity<List<Song>>(songs, HttpStatus.OK);
    }

    @PostMapping("/songs")
    public ResponseEntity<Void> createSong(@RequestBody Song song){
        songService.save(song);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> searchSongById(@PathVariable Long id){
        Song song = songService.findById(id);
        if (song.equals(null)){
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
        Singer singer = singerService.findById(song.getSingerId());
        Set<Song> myList = singer.getMyList();
        myList.add(song);
        singer.setMyList(myList);
        return new ResponseEntity<Song>(song, HttpStatus.OK);
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id){
        Song song = songService.findById(id);
        if (song.equals(null)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/songs/new")
    public ResponseEntity<List<Song>> songNew(){
        List<Song> songs = (List<Song>) songService.findOrOrderByCreateDate();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
