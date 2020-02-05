package com.codegym.zing.service.impl;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.Tab;
import com.codegym.zing.repository.TabRepository;
import com.codegym.zing.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabServiceImpl implements TabService {
    @Autowired
    private TabRepository tabRepository;

    @Override
    public List<Tab> findAllBySong(Song song) {
        return tabRepository.findAllBySong(song);
    }

    @Override
    public void delete(Tab tab) {
        tabRepository.delete(tab);
    }

    @Override
    public List<Tab> findAll() {
        return tabRepository.findAll();
    }

    @Override
    public void save(Tab tab) {
        tabRepository.save(tab);
    }

    @Override
    public Tab findById(Long id) {
        Optional<Tab> tab = tabRepository.findById(id);
        if (tab.isPresent()){
            return tab.get();
        }
        return null;
    }
}
