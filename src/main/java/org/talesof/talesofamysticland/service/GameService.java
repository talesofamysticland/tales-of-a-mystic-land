package org.talesof.talesofamysticland.service;

import org.talesof.talesofamysticland.model.Save;

public class GameService {

    private Save currentSave;
    private int selectedSlot;
    private String characterClass;
    private String characterName;

    public Save getCurrentSave() {
        return currentSave;
    }

    public void setCurrentSave(Save currentSave) {
        this.currentSave = currentSave;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(int selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
