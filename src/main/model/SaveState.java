package main.model;

import java.time.LocalDateTime;

public class SaveState {

    private Integer id;
    private Integer saveId;
    private Integer characterStateId;
    private Integer savePointId;
    private LocalDateTime date;

    public SaveState (Integer id, Integer saveId, Integer characterStateId, Integer savePointId, LocalDateTime date) {
        this.id = id;
        this.saveId = saveId;
        this.characterStateId = characterStateId;
        this.savePointId = savePointId;
        this.date = date;
    }

    public SaveState (Integer saveId, Integer characterStateId, Integer savePointId, LocalDateTime date) {
        this(null, saveId, characterStateId, savePointId, date);
    }

    public SaveState (Integer id, Integer saveId, Integer characterStateId, Integer savePointId) {
        this(id, saveId, characterStateId, savePointId, LocalDateTime.now());
    }

    public SaveState (Integer saveId, Integer characterStateId, Integer savePointId) {
        this(saveId, characterStateId, savePointId, LocalDateTime.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSaveId() {
        return saveId;
    }

    public void setSaveId(Integer saveId) {
        this.saveId = saveId;
    }

    public Integer getCharacterStateId() {
        return characterStateId;
    }

    public void setCharacterStateId(Integer characterStateId) {
        this.characterStateId = characterStateId;
    }

    public Integer getSavePointId() {
        return savePointId;
    }

    public void setSavePointId(Integer savePointId) {
        this.savePointId = savePointId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SaveState [id=" + id + ", saveId=" + saveId + ", characterStateId=" + characterStateId
                + ", savePointId=" + savePointId + ", date=" + date + "]\n";
    }
}
