package com.codegym.zing.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String confirmPassword;

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

    @OneToMany(targetEntity = Album.class)
    private Set<Comment> comments = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;
    public User() {
    }

    public User(String name, String image, String username, String password, String confirmPassword, String address, String identityCard, String identityCardImage, String company, Set<Song> myList, Set<Playlist> playlist, Set<Album> albumList, Role roles) {
        this.name = name;
        this.image = image;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address = address;
        this.identityCard = identityCard;
        this.identityCardImage = identityCardImage;
        this.company = company;
        this.myList = myList;
        this.playlist = playlist;
        this.albumList = albumList;
        this.roles = roles;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
