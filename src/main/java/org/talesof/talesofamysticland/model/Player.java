package org.talesof.talesofamysticland.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Player {
    
    private Integer id;
    private String username;
    private String email;
    private String password;
    private boolean verified;
    private String verificationToken;
    private LocalDateTime registerDate;

    public Player() {
        this.verificationToken = UUID.randomUUID().toString();
        this.registerDate = LocalDateTime.now();
        this.verified = false;
    }

    public Player(Integer id, String username, String email, String password, boolean verified,
            String verificationToken, LocalDateTime registerDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.verificationToken = verificationToken;
        this.registerDate = registerDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", verified=" + verified + ", verificationToken=" + verificationToken + ", registerDate="
                + registerDate + "]\n";
    }
}
