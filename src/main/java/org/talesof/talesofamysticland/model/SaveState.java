package org.talesof.talesofamysticland.model;

import java.time.LocalDateTime;

public class SaveState {

    private Integer id;
    private Integer saveId;
    private Integer characterStateId;
    private Integer savePointId;
    private LocalDateTime lastSaved;

    public SaveState (Integer id, Integer saveId, Integer characterStateId, Integer savePointId, LocalDateTime lastSaved) {
        this.id = id;
        this.saveId = saveId;
        this.characterStateId = characterStateId;
        this.savePointId = savePointId;
        this.lastSaved = lastSaved;
    }

    public SaveState(Integer saveId, Integer characterStateId, Integer savePointId, LocalDateTime lastSaved) {
        this(null, saveId, characterStateId, savePointId, lastSaved);
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

    public LocalDateTime getLastSaved() {
        return lastSaved;
    }

    public void setLastSaved(LocalDateTime lastSaved) {
        this.lastSaved = lastSaved;
    }

    @Override
    public String toString() {
        return "SaveState [id=" + id + ", saveId=" + saveId + ", characterStateId=" + characterStateId
                + ", savePointId=" + savePointId + ", date=" + lastSaved + "]\n";
    }
}
