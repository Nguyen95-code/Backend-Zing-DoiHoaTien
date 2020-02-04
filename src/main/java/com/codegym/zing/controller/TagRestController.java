package com.codegym.zing.controller;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.Tag;
import com.codegym.zing.model.User;
import com.codegym.zing.service.SongService;
import com.codegym.zing.service.TagService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("songs/{song_id}")
public class TagRestController {
    @Autowired
    private SongService songService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;
    @ModelAttribute("userCurent")
    public User getUserCurrent() {
        return userService.getCurrentUser();
    }
    @GetMapping("tags")
    public ResponseEntity<List<Tag>> findAllBySong(@PathVariable Long song_id){
        Song song = songService.findById(song_id);
        if (song == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Tag> tags = tagService.findAllBySong(song);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping("tags")
    public ResponseEntity<Tag> createTag(@PathVariable Long song_id, @RequestBody Tag tag){
        Song song = songService.findById(song_id);
        if (song == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (song.getUser().getId() != getUserCurrent().getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tag.setSong(song);
        tagService.save(tag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
