package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.service.NavigationService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CharacterCreationController {

    private NavigationService navigationService;

    @FXML
    private TextField txfCharacterName;

    @FXML
    private VBox warriorBox, wizardBox, archerBox;

    private VBox selectedBox;
    
    public CharacterCreationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @FXML 
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void initialize() {
        txfCharacterName.setFocusTraversable(false);

        setHoverEffect(warriorBox);
        setHoverEffect(wizardBox);
        setHoverEffect(archerBox);
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
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
