package com.codegym.zing.controller;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.User;
import com.codegym.zing.service.SongService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class SongRestController {
    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @ModelAttribute("userCurent")
    public User getUserCurent() {
        return userService.getCurrentUser();
    }

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> listSong() {
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping("/songs")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        song.setCreateDate(LocalDate.now());
        song.setUser(getUserCurent());
        song.setViews(0);
        songService.save(song);
        return new ResponseEntity<Song>(song, HttpStatus.OK);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> searchSongById(@PathVariable Long id) {
        Song song = songService.findById(id);
        if (song == null) {
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Song>(song, HttpStatus.OK);
    }

    @PutMapping("/songs")
    public ResponseEntity<Song> updateSong(@RequestBody Song song) {
        songService.save(song);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        Song song = songService.findById(id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/songs/new")
    public ResponseEntity<List<Song>> songNew() {
        List<Song> songs = (List<Song>) songService.findOrOrderByCreateDate();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/songs/views")
    public ResponseEntity<List<Song>> songTopViews() {
        List<Song> songs = (List<Song>) songService.findOrOrderByViews();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}
