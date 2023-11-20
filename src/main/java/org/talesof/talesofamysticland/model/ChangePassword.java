package org.talesof.talesofamysticland.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChangePassword {

    private Integer id;
    private Integer playerId;
    private boolean validated;
    private LocalDateTime verificationDate;
    private String verificationToken;

    private LocalDateTime expirationDate;

    public ChangePassword(Integer playerId) {
        this.playerId = playerId;
        this.validated = false;
        generateVerificationToken();
    }

    public ChangePassword(Integer playerId, boolean validated, LocalDateTime verificationDate, String verificationToken) {
        this.playerId = playerId;
        this.validated = validated;
        this.verificationDate = verificationDate;
        this.verificationToken = verificationToken;
    }

    public ChangePassword(Integer id, Integer playerId, boolean validated, LocalDateTime verificationDate, String verificationToken) {
        this.id = id;
        this.playerId = playerId;
        this.validated = validated;
        this.verificationDate = verificationDate;
        this.verificationToken = verificationToken;
    }

    public void generateVerificationToken() {
        this.verificationToken = UUID.randomUUID().toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public LocalDateTime getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDateTime verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "ChangePassword [id=" + id + ", playerId=" + playerId + ", validated=" + validated
                + ", verificationDate=" + verificationDate + ", verificationToken=" + verificationToken + "]";
    }
}
