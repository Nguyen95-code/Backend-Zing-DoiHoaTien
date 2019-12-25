package com.codegym.zing.service;

import com.codegym.zing.model.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    void save(VerificationToken token);
}
