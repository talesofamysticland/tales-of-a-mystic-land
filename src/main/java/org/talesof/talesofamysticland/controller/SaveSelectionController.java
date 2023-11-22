package org.talesof.talesofamysticland.controller;

import java.util.List;

import org.talesof.talesofamysticland.dao.CharacterStateDAO;
import org.talesof.talesofamysticland.dao.SaveDAO;
import org.talesof.talesofamysticland.dao.SaveStateDAO;
import org.talesof.talesofamysticland.model.CharacterState;
import org.talesof.talesofamysticland.model.Player;
import org.talesof.talesofamysticland.model.Save;
import org.talesof.talesofamysticland.model.SaveState;
import org.talesof.talesofamysticland.service.GameService;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SaveSelectionController {

    private UserService userService;
    private NavigationService navigationService;
    private GameService gameService;

    private SaveDAO saveDAO;
    private SaveStateDAO saveStateDAO;
    private CharacterStateDAO characterStateDAO;

    // Save slot 1

    @FXML
    private HBox boxLastSaveState1;

    @FXML
    private ImageView imgCharacter1;

    @FXML
    private Label lblCharacterName1, lblPlayTime1, lblLastSaved1;

    @FXML
    private Label lblNewSave1;

    // Save slot 2

    @FXML
    private HBox boxLastSaveState2;

    @FXML
    private ImageView imgCharacter2;

    @FXML
    private Label lblCharacterName2, lblPlayTime2, lblLastSaved2;

    @FXML
    private Label lblNewSave2;

    // Save slot 3

    @FXML
    private HBox boxLastSaveState3;

    @FXML
    private ImageView imgCharacter3;

    @FXML
    private Label lblCharacterName3, lblPlayTime3, lblLastSaved3;

    @FXML
    private Label lblNewSave3;

    private Save save1, save2, save3;

    
    public SaveSelectionController(
        UserService userService, 
        NavigationService navigationService,
        GameService gameService,
        SaveDAO saveDAO,
        SaveStateDAO saveStateDAO,
        CharacterStateDAO characterStateDAO) {

        this.userService = userService;
        this.navigationService = navigationService;
        this.gameService = gameService;
        this.saveDAO = saveDAO;
        this.saveStateDAO = saveStateDAO;
        this.characterStateDAO = characterStateDAO;
    }

    @FXML
    public void initialize() {
        Player player = userService.getCurrentPlayer();
        List<Save> saves = saveDAO.findByPlayer(player);

        for(Save save : saves) {
            if (save != null) {
                switch(save.getSlot()) {
                    case 1 -> {
                        save1 = save; 
                        updateSaveBox(save, lblNewSave1, boxLastSaveState1, imgCharacter1, lblCharacterName1, lblPlayTime1, lblLastSaved1);
                    }
                    case 2 -> {
                        save2 = save;
                        updateSaveBox(save, lblNewSave2, boxLastSaveState2, imgCharacter2, lblCharacterName2, lblPlayTime2, lblLastSaved2);
                    } 
                    case 3 -> {
                        save3 = save;
                        updateSaveBox(save, lblNewSave3, boxLastSaveState3, imgCharacter3, lblCharacterName3, lblPlayTime3, lblLastSaved3);
                    } 
                }
            }
        }
    }

    private void updateSaveBox(Save save, Label newSaveLabel, HBox saveBox, ImageView characterImage,
    Label characterNameLabel, Label playTimeLabel, Label lastSavedLabel) {

        gameService.setCharacterClass(save.getCharacterClass());
        gameService.setCharacterName(save.getCharacterName());

        SaveState mostRecentSaveState = saveStateDAO.findBySave(save);

        if(mostRecentSaveState != null) {
            CharacterState characterState = characterStateDAO.findById(mostRecentSaveState.getCharacterStateId());

            hideNewSaveLabel(newSaveLabel);
            showSaveBox(saveBox);
            setClassIcon(characterImage, save.getCharacterClass());
            characterNameLabel.setText(save.getCharacterName());
            playTimeLabel.setText(formatPlayTime(characterState.getPlayTime()));
            lastSavedLabel.setText(formatLastSaved(mostRecentSaveState.getLastSaved()));
        }
    }

    private String formatPlayTime(Long playTime) {
        Duration playTimeInSeconds = Duration.ofSeconds(playTime);
        LocalTime playTimeFormatted = LocalTime.MIDNIGHT.plus(playTimeInSeconds);
        return playTimeFormatted.toString();
    }

    private String formatLastSaved(LocalDateTime lastSaved) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        return lastSaved.format(formatter).toString();
    }

    private void setClassIcon(ImageView img, String characterClass) {
        switch(characterClass) {
            case "Warrior" -> img.getStyleClass().add("warrior-icon");
            case "Wizard" -> img.getStyleClass().add("wizard-icon");
            case "Archer" -> img.getStyleClass().add("archer-icon");
        }
    }

    private void showSaveBox(HBox box) {
        box.setVisible(true);
        box.setManaged(true);
    }

    private void hideNewSaveLabel(Label label) {
        label.setVisible(false);
        label.setManaged(false);
    } 

    @FXML 
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onClickBoxSelectSave1() {
        selectSave(save1, 1);
    }

    @FXML
    public void onClickBoxSelectSave2() {
        selectSave(save2, 2);
    }

    @FXML
    public void onClickBoxSelectSave3() {
        selectSave(save3, 3);
    }

    private void selectSave(Save save, int slot) {
        if(save == null) {
            navigationService.navigateTo("character-creation.fxml");
            gameService.setSelectedSlot(slot);
            return;
        }

        gameService.setCurrentSave(save);
        navigationService.startGame(gameService);
    }

    @FXML
    public void onClickImgDeleteSave1() {
        deleteSave(save1);
    }

    @FXML
    public void onClickImgDeleteSave2() {
        deleteSave(save2);
    }

    @FXML
    public void onClickImgDeleteSave3() {
        deleteSave(save3);
    }

    private void deleteSave(Save save) {
        SaveDAO saveDAO = new SaveDAO();
        saveDAO.delete(save);
        save = null;
        navigationService.navigateTo("save-selection.fxml");
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
