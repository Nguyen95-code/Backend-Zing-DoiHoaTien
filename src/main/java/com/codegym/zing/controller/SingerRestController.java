package com.codegym.zing.controller;

import com.codegym.zing.model.Singer;
import com.codegym.zing.model.Song;
import com.codegym.zing.service.SingerService;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SingerRestController {
    @Autowired
    private SingerService singerService;

    @GetMapping("/singers")
    public ResponseEntity<List<Singer>> listSong() {
        List<Singer> singers = singerService.findAll();
        return new ResponseEntity<>(singers, HttpStatus.OK);
    }

    @PostMapping("/singers")
    public ResponseEntity<Void> createSong(@RequestBody Singer singer) {
        singerService.save(singer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/singers/{id}")
    public ResponseEntity<Singer> findById(@PathVariable Long id) {
        Singer singer = singerService.findById(id);
        if (singer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(singer, HttpStatus.OK);
    }
}
