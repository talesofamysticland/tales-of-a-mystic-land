package org.talesof.talesofamysticland.controller;

import java.sql.SQLException;

import org.talesof.talesofamysticland.dao.SaveDAO;
import org.talesof.talesofamysticland.game.Game;
import org.talesof.talesofamysticland.game.entity.Archer;
import org.talesof.talesofamysticland.game.entity.PlayerCharacter;
import org.talesof.talesofamysticland.game.entity.Warrior;
import org.talesof.talesofamysticland.game.entity.Wizard;
import org.talesof.talesofamysticland.model.Save;
import org.talesof.talesofamysticland.service.FormErrorListeningService;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CharacterCreationController {

    private UserService userService;
    private NavigationService navigationService;
    private FormErrorListeningService formErrorListeningService;

    private SaveDAO saveDAO;

    @FXML
    private TextField txfCharacterName;

    @FXML
    private VBox warriorBox, wizardBox, archerBox;

    @FXML
    private Label lblBlankCharacterName, lblBlankCharacterClass;

    private VBox selectedBox;
    
    public CharacterCreationController(
        UserService userService, 
        NavigationService navigationService, 
        FormErrorListeningService formErrorListeningService,
        SaveDAO saveDAO) {

        this.userService = userService;
        this.navigationService = navigationService;
        this.formErrorListeningService = formErrorListeningService;
        this.saveDAO = saveDAO;
    }

    @FXML
    public void initialize() {
        txfCharacterName.setFocusTraversable(false);

        setHoverEffect(warriorBox);
        setHoverEffect(wizardBox);
        setHoverEffect(archerBox);

        formErrorListeningService.setupFieldListener(txfCharacterName, lblBlankCharacterName);
    }

    @FXML 
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onClickSelectClass(MouseEvent event) {
        VBox clickedBox = (VBox) event.getSource();
        setBorderStyle(clickedBox);
    }

    private void setBorderStyle(VBox box) {
        if (selectedBox != null) {
            selectedBox.setStyle("-fx-border-width: 0px");
        }

        box.setStyle("-fx-border-width: 3px");
        selectedBox = box;
    }

    private void setHoverEffect(VBox box) {
        box.setOnMouseEntered(e -> {
            if (box != selectedBox) {
                box.setStyle("-fx-border-width: 1px");
            }
        });

        box.setOnMouseExited(e -> {
            if (box != selectedBox) {
                box.setStyle("-fx-border-width: 0px");
            }
        });
    }

    @FXML
    private void onActionBtnStartGame() {
        String characterName = txfCharacterName.getText().trim();
        String characterClass = selectedBox.getId();

        if(characterName.isBlank()) {
            lblBlankCharacterName.setVisible(true);
            lblBlankCharacterName.setManaged(true);
            return;
        }

        if(selectedBox == null) {
            lblBlankCharacterClass.setVisible(true);
            lblBlankCharacterClass.setManaged(true);
            return;
        }

        try {
            Save save = new Save(userService.getCurrentPlayer().getId(), navigationService.getSelectedSaveSlot(), characterName, characterClass);
            saveDAO.save(save);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PlayerCharacter player = null;

        switch(characterClass) {
            case "Warrior" -> player = new Warrior(Game.gamePanel);
            case "Wizard" -> player = new Wizard(Game.gamePanel);
            case "Archer" -> player = new Archer(Game.gamePanel);
        }

        navigationService.startGame(player);
    }
    
    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
