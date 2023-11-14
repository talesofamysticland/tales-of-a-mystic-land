package org.talesof.talesofamysticland.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CharacterCreationController {

    @FXML
    private VBox warriorBox, wizardBox, archerBox;

    private VBox selectedBox;
    
    @FXML 
    public void onClickImgOpenConfigurations() {
        // TODO
    }

    @FXML
    public void onClickSelectClass(MouseEvent event) {
        setHoverEffect(warriorBox);
        setHoverEffect(wizardBox);
        setHoverEffect(archerBox);
        
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
        // TODO
    }
}
