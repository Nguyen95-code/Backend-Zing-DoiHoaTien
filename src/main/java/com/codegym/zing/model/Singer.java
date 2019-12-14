package com.codegym.zing.model;

import javax.persistence.*;
import java.util.List;

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
    private List<Song> myList;

    @OneToMany(targetEntity = Playlist.class)
    private List<Playlist> playlist;

    public Singer() {
    }

    public Singer(String name, String image, String address, String identityCard, String identityCardImage, String company, List<Song> myList, List<Playlist> playlist) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.identityCard = identityCard;
        this.identityCardImage = identityCardImage;
        this.company = company;
        this.myList = myList;
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

    public List<Song> getMyList() {
        return myList;
    }

    public void setMyList(List<Song> myList) {
        this.myList = myList;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
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
}
