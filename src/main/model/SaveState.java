package main.model;

import java.time.LocalDateTime;

public class SaveState {
    private Integer id;
    private Integer idSave;
    private Integer idCharacterState;
    private Integer idSavePoint;
    private LocalDateTime date;

    public SaveState (Integer id, Integer idSave, Integer idCharacterState, Integer idSavePoint, LocalDateTime date){
        this.id = id;
        this.idSave = idSave;
        this.idCharacterState = idCharacterState;
        this.idSavePoint = idSavePoint;
        this.date = date;
    }

    public SaveState (Integer id, Integer idSave, Integer idCharacterState, Integer idSavePoint){
        this(id, idSave, idCharacterState, idSavePoint, LocalDateTime.now());
    }

    public SaveState (Integer idSave, Integer idCharacterState, Integer idSavePoint, LocalDateTime date){
        this.idSave = idSave;
        this.idCharacterState = idCharacterState;
        this.idSavePoint = idSavePoint;
        this.date = date;
    }

    public SaveState (Integer idSave, Integer idCharacterState, Integer idSavePoint){
        this(idSave, idCharacterState, idSavePoint, LocalDateTime.now());
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCharacterState() {
        return idCharacterState;
    }

    public void setIdCharacterState(Integer idCharacterState) {
        this.idCharacterState = idCharacterState;
    }

    public Integer getIdSave() {
        return idSave;
    }

    public void setIdSave(Integer idSave) {
        this.idSave = idSave;
    }

    public Integer getIdSavePoint() {
        return idSavePoint;
    }

    public void setIdSavePoint(Integer idSavePoint) {
        this.idSavePoint = idSavePoint;
    }
}
