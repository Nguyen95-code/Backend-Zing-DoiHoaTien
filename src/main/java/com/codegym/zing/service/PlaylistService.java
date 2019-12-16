package com.codegym.zing.service;

import com.codegym.zing.model.Playlist;

public interface PlaylistService extends GeneralService<Playlist>{
    void delete(Long id);
}
