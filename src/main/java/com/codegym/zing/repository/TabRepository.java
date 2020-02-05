package com.codegym.zing.repository;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.Tab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabRepository extends JpaRepository<Tab, Long> {
    List<Tab> findAllBySong(Song song);
}
