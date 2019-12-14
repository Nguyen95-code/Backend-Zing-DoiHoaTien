package com.codegym.zing.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    @OneToMany(targetEntity = Song.class)
    private List<Song> playlist;

    public User(String name, String image, List<Song> playlist) {
        this.name = name;
        this.image = image;
        this.playlist = playlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }
}
