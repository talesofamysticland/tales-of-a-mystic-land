package main.entity;

import java.time.LocalDateTime;

public class Player {
    
    private Integer id;
    private Integer idSettings;
    private String username;
    private String email;
    private String password;
    private boolean verified;
    private String verificationToken;
    private LocalDateTime registerDate;

    public Player(Integer id, Integer idSettings, String username, String email, String password, boolean verified,
            String verificationToken, LocalDateTime registerDate) {
        this.id = id;
        this.idSettings = idSettings;
        this.username = username;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.verificationToken = verificationToken;
        this.registerDate = registerDate;
    }

    public Player(Integer idSettings, String username, String email, String password, boolean verified,
            String verificationToken, LocalDateTime registerDate) {
        this.idSettings = idSettings;
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

    public Integer getIdSettings() {
        return idSettings;
    }

    public void setIdSettings(Integer idSettings) {
        this.idSettings = idSettings;
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
}
