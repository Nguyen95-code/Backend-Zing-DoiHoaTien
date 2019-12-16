package com.codegym.zing.controller;

import com.codegym.zing.model.Album;
import com.codegym.zing.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
