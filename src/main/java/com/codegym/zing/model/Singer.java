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
    private String username;
    private String password;
    private String image;

    @OneToMany(targetEntity = Song.class)
    private Set<Song> myList;
    @OneToMany(targetEntity = Song.class)
    private Set<Song> playlist;

    public Singer(String name, String username, String password, String image) {
        this.name = name;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
