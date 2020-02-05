package com.codegym.zing.service;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.Tab;

import java.util.List;

public interface TabService extends GeneralService<Tab> {
    List<Tab> findAllBySong(Song song);
    void delete(Tab tab);
}
