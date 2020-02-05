package com.codegym.zing.controller;

import com.codegym.zing.model.*;
import com.codegym.zing.service.SongService;
import com.codegym.zing.service.TabService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/songs/{song_id}")
@RestController
public class TabSongRestController {
    @Autowired
    private TabService tabService;
    @Autowired
    private SongService songService;
    @Autowired
    private UserService userService;
    @ModelAttribute("userCurent")
    public User getUserCurent() {
        return userService.getCurrentUser();
    }
    @GetMapping("/tabs")
    public ResponseEntity<List<Tab>> listTabSong(@PathVariable Long song_id){
        Song song = songService.findById(song_id);
        if (song == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Tab> tabs = tabService.findAllBySong(song);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/tabs")
    public ResponseEntity<Tab> createTab(@RequestBody Tab tab, @PathVariable Long song_id){
        tab.setUser(getUserCurent());
        Song song = songService.findById(song_id);
        tab.setSong(song);
        tabService.save(tab);
        return new ResponseEntity<Tab>(tab, HttpStatus.OK);
    }
    @PutMapping("/tabs")
    public ResponseEntity<Tab> updateTab(@RequestBody Tab tab, @PathVariable Long song_id) {
        Song song = songService.findById(song_id);
        tab.setSong(song);
        tabService.save(tab);
        return new ResponseEntity<>(tab, HttpStatus.OK);
    }
    @DeleteMapping("/tabs/{id}")
    public ResponseEntity<Void> deleteTab(@PathVariable Long id, @PathVariable Long song_id){
        Song song = songService.findById(song_id);
        Tab tab = tabService.findById(id);
        if (tab == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } tabService.delete(tab);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
