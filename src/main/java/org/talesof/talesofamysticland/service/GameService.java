package org.talesof.talesofamysticland.service;

import org.talesof.talesofamysticland.model.Save;

public class GameService {

    private Save currentSave;
    private int selectedSlot;

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
}
