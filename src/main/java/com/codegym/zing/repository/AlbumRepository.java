package com.codegym.zing.repository;

import com.codegym.zing.model.Album;
import com.codegym.zing.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllBySinger(Singer singer);
}
