package com.codegym.zing.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "singer")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    @OneToMany(targetEntity = Song.class)
    private Set<Song> myList;
    @OneToMany(targetEntity = Song.class)
    private Set<Song> playlist;

    public Singer() {
    }

    public Singer(String name, String image) {
        this.name = name;
        this.image = image;
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

    public Set<Song> getMyList() {
        return myList;
    }

    public void setMyList(Set<Song> myList) {
        this.myList = myList;
    }

    public Set<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<Song> playlist) {
        this.playlist = playlist;
    }
}
