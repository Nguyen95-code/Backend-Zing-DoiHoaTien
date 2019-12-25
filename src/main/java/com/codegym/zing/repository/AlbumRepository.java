package com.codegym.zing.repository;

import com.codegym.zing.model.Album;
import com.codegym.zing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByUser(User user);
}
