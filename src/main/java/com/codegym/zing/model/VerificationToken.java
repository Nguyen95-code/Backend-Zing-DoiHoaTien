package com.codegym.zing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class VerificationToken {
    private static final long serialVersionUID = 5926468583005150707L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private Date expiryDate;

    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken() {
    }

    public VerificationToken(User user) {
        this.user = user;
        createdDate = new Date();
        token = UUID.randomUUID().toString();
    }

    public void setExpiryDate(int minutes) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }
}
