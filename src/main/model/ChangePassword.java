package main.model;

import java.time.LocalDateTime;

public class ChangePassword {

    private Integer id;
    private Integer playerId;
    private boolean validated;
    private LocalDateTime verificationDate;
    private String verificationToken;

    public ChangePassword(Integer id, Integer playerId, boolean validated, LocalDateTime verificationDate, String verificationToken) {
        this.id = id;
        this.playerId = playerId;
        this.validated = validated;
        this.verificationDate = verificationDate;
        this.verificationToken = verificationToken;
    }

    public ChangePassword(Integer playerId, boolean validated, LocalDateTime verificationDate, String verificationToken) {
        this(null, playerId, validated, verificationDate, verificationToken);
    }

    public ChangePassword(Integer id, Integer playerId, boolean validated, String verificationToken) {
        this(id, playerId, validated, LocalDateTime.now(), verificationToken);
    }

    public ChangePassword(Integer playerId, boolean validated, String verificationToken) {
        this(playerId, validated, LocalDateTime.now(), verificationToken);
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

    @Override
    public String toString() {
        return "ChangePassword [id=" + id + ", playerId=" + playerId + ", validated=" + validated
                + ", verificationDate=" + verificationDate + ", verificationToken=" + verificationToken + "]";
    }
}
