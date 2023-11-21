package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.game.main.Game;
import org.talesof.talesofamysticland.service.NavigationService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SaveSelectionController {

    private NavigationService navigationService;

    @FXML
    private Label lblCharacterName1;

    @FXML
    private Label lblPlayTime1;

    @FXML
    private Label lblLastSaved1;

    @FXML
    private Label lblCharacterName2;

    @FXML
    private Label lblPlayTime2;

    @FXML
    private Label lblLastSaved2;

    @FXML
    private Label lblCharacterName3;

    @FXML
    private Label lblPlayTime3;

    @FXML
    private Label lblLastSaved3;
    
    public SaveSelectionController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @FXML 
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onClickBoxSelectSave1() {
        Game.start();
    }

    @FXML
    public void onClickBoxSelectSave2() {
        navigationService.navigateTo("character-creation.fxml");
    }

    @FXML
    public void onClickBoxSelectSave3() {
        navigationService.navigateTo("character-creation.fxml");
    }

    @FXML
    public void onClickImgDeleteSave() {

    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
