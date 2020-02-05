package com.codegym.zing.repository;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllBySong(Song song);
}
