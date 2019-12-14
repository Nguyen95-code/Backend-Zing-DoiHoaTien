package com.codegym.zing.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    @OneToMany(targetEntity = Song.class)
    private List<Song> songList;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;

    public Album() {
    }

    public Album(String name, String image, List<Song> songList, Singer singer) {
        this.name = name;
        this.image = image;
        this.songList = songList;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
