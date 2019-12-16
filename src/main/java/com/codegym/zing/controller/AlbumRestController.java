package com.codegym.zing.controller;

import com.codegym.zing.model.Album;
import com.codegym.zing.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
