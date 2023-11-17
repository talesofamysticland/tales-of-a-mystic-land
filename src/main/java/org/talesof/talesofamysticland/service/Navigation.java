package org.talesof.talesofamysticland.service;

import javafx.scene.Parent;

public interface Navigation {

    void navigateTo(Parent origin, String view);
    void navigateBack();
}