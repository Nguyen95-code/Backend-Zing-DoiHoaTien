package com.codegym.zing.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "playlist_song",
            joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")})
    private Set<Song> songList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "person_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;

    public Playlist() {
    }

    public Playlist(Long id, String name, String image, Set<Song> songList, User user, Singer singer) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.songList = songList;
        this.user = user;
        this.singer = singer;
    }

    public Playlist(String name, String image, Set<Song> songList, User user, Singer singer) {
        this.name = name;
        this.image = image;
        this.songList = songList;
        this.user = user;
        this.singer = singer;
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

    public Set<Song> getSongList() {
        return songList;
    }

    public void setSongList(Set<Song> songList) {
        this.songList = songList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
