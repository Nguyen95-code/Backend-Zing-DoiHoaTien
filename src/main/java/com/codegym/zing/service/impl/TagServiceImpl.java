package com.codegym.zing.service.impl;

import com.codegym.zing.model.Song;
import com.codegym.zing.model.Tag;
import com.codegym.zing.repository.TagRepository;
import com.codegym.zing.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Override
    public List<Tag> findAllBySong(Song song) {
        return tagRepository.findAllBySong(song);
    }

    @Override
    public Tag findById(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()){
            return tag.get();
        }
        return null;
    }

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
