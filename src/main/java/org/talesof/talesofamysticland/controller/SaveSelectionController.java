package org.talesof.talesofamysticland.controller;

import java.util.List;

import org.talesof.talesofamysticland.dao.PlayerDAO;
import org.talesof.talesofamysticland.dao.SaveDAO;
import org.talesof.talesofamysticland.game.Game;
import org.talesof.talesofamysticland.game.entity.Archer;
import org.talesof.talesofamysticland.game.entity.PlayerCharacter;
import org.talesof.talesofamysticland.game.entity.Warrior;
import org.talesof.talesofamysticland.game.entity.Wizard;
import org.talesof.talesofamysticland.model.Player;
import org.talesof.talesofamysticland.model.Save;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SaveSelectionController {

    private UserService userService;
    private NavigationService navigationService;

    private SaveDAO saveDAO;
    private PlayerDAO playerDAO;

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
        SaveDAO saveDAO,
        PlayerDAO playerDAO) {

        this.userService = userService;
        this.navigationService = navigationService;
        this.saveDAO = saveDAO;
        this.playerDAO = playerDAO;
    }

    @FXML
    public void initialize() {

        Player player = userService.getCurrentPlayer();

        List<Save> saves = saveDAO.findSavesListByPlayer(player);

        for(Save save : saves) {
            if(save != null) {
                switch(save.getSlot()) {
                    case 1 -> {
                        save1 = save;

                        hideNewSaveLabel(lblNewSave1);
                        showSaveBox(boxLastSaveState1);
                        setClassIcon(imgCharacter1, save.getCharacterClass());
                        lblCharacterName1.setText(save.getCharacterName());
                        // lblPlayTime1.setText(save.getPlayTime());
                        // lblLastSaved1.setText(save.getLastSaved());
                    }

                    case 2 -> {
                        save2 = save;

                        hideNewSaveLabel(lblNewSave2);
                        showSaveBox(boxLastSaveState2);
                        setClassIcon(imgCharacter2, save.getCharacterClass());
                        lblCharacterName2.setText(save.getCharacterName());
                        // lblPlayTime2.setText(save.getPlayTime());
                        // lblLastSaved2.setText(save.getLastSaved());
                    }

                    case 3 -> {
                        save3 = save;

                        hideNewSaveLabel(lblNewSave3);
                        showSaveBox(boxLastSaveState3);
                        setClassIcon(imgCharacter3, save.getCharacterClass());
                        lblCharacterName3.setText(save.getCharacterName());
                        // lblPlayTime3.setText(save.getPlayTime());
                        // lblLastSaved3.setText(save.getLastSaved());
                    }
                }
            }
        }
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
        if(save1 == null) {
            navigationService.navigateToSave(1);
            return;
        }

        PlayerCharacter player = getPlayerFromSave(save1);

        navigationService.startGame(player);
    }

    @FXML
    public void onClickBoxSelectSave2() {
        if(save2 == null) {
            navigationService.navigateToSave(2);
            return;
        }

        PlayerCharacter player = getPlayerFromSave(save1);

        navigationService.startGame(player);
    }

    @FXML
    public void onClickBoxSelectSave3() {
        if(save3 == null) {
            navigationService.navigateToSave(3);
            return;
        }

        PlayerCharacter player = getPlayerFromSave(save1);

        navigationService.startGame(player);
    }

    private PlayerCharacter getPlayerFromSave(Save save) {

        PlayerCharacter playerCharacter = null;

        switch(save.getCharacterClass()) {
            case "Warrior" -> playerCharacter = new Warrior(Game.gamePanel);
            case "Wizard" -> playerCharacter = new Wizard(Game.gamePanel);
            case "Archer" -> playerCharacter = new Archer(Game.gamePanel);
        }

        return playerCharacter;
    }

    @FXML
    public void onClickImgDeleteSave() {
        initialize();
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
