package org.talesof.talesofamysticland.service;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormErrorListeningService {
    
    public void setupFieldListener(TextField textField, Label... errorLabels) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            hideErrorLabels(errorLabels);
            clearFieldStyles(textField);
        });
    }

    public void hideErrorLabels(Label... errorLabels) {
        for (Label label : errorLabels) {
            label.setVisible(false);
            label.setManaged(false);
        }
    }

    private void clearFieldStyles(TextField textField) {
        textField.getStyleClass().removeAll("form__error-textfield");
    }

    public void showErrors(Label errorLabel, TextField textField) {
        errorLabel.setVisible(true);
        errorLabel.setManaged(true);
        textField.getStyleClass().add("form__error-textfield");
    }
}
