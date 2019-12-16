package com.codegym.zing.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "singer")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    private String address;
    private String identityCard;
    private String identityCardImage;
    private String company;

    @OneToMany(targetEntity = Song.class)
    private Set<Song> myList = new HashSet<>();

    @OneToMany(targetEntity = Playlist.class)
    private Set<Playlist> playlist = new HashSet<>();

    @OneToMany(targetEntity = Album.class)
    private Set<Album> albumList = new HashSet<>();

    public Singer() {
    }

    public Singer(String name, String image, String address, String identityCard, String identityCardImage, String company, Set<Song> myList, Set<Playlist> playlist, Set<Album> albumList) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.identityCard = identityCard;
        this.identityCardImage = identityCardImage;
        this.company = company;
        this.myList = myList;
        this.playlist = playlist;
        this.albumList = albumList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getIdentityCardImage() {
        return identityCardImage;
    }

    public void setIdentityCardImage(String identityCardImage) {
        this.identityCardImage = identityCardImage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Set<Song> getMyList() {
        return myList;
    }

    public void setMyList(Set<Song> myList) {
        this.myList = myList;
    }

    public Set<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<Playlist> playlist) {
        this.playlist = playlist;
    }

    public Set<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(Set<Album> albumList) {
        this.albumList = albumList;
    }
}
