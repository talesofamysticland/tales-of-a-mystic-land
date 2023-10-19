package main.model;

import java.time.LocalDateTime;

public class ChangePassword {
    private Integer id;
    private Integer idPlayer;
    private boolean validated;
    private LocalDateTime verificationDate;
    private String verificationToken;

    public ChangePassword (Integer id, Integer idPlayer, boolean validated, LocalDateTime verificationDate, String verificationToken){
        this.id = id;
        this.idPlayer = idPlayer;
        this.validated = validated;
        this.verificationDate = verificationDate;
        this.verificationToken = verificationToken;
    }

    public ChangePassword (Integer id, Integer idPlayer, boolean validated, String verificationToken){
        this(id, idPlayer, validated, LocalDateTime.now(), verificationToken);
    }

    public ChangePassword (Integer idPlayer, boolean validated, LocalDateTime verificationDate, String verificationToken){
        this.idPlayer = idPlayer;
        this.validated = validated;
        this.verificationDate = verificationDate;
        this.verificationToken = verificationToken;
    }

    public ChangePassword (Integer idPlayer, boolean validated, String verificationToken){
        this(idPlayer, validated, LocalDateTime.now(), verificationToken);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public LocalDateTime getVerificationDate() {
        return verificationDate;
    }
    
    public void setVerificationDate(LocalDateTime verificationDate) {
        this.verificationDate = verificationDate;
    }

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

}
