package org.talesof.talesofamysticland.service;

import java.io.IOException;
import java.util.Stack;

import org.talesof.talesofamysticland.injection.DependencyInjector;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class NavigationService implements Navigation {

    private Stack<String> history = new Stack<>();
    private String currentView;

    public NavigationService() {
        currentView = "title-screen.fxml";
    }

    @Override
    public void navigateTo(Parent root, String view) {
        if(history.isEmpty() || !history.peek().equals(currentView)) {
            history.add(currentView);
        }

        try {
            Scene scene = root.getScene();
            root = DependencyInjector.load(view);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Navegando para a view: " + view);
        currentView = view;
    }

    @Override
    public void navigateBack() {
        if (!history.isEmpty()) {
            try {
                String previousView = history.pop();
                System.out.println(currentView);
                navigateTo(DependencyInjector.load(currentView), previousView);
                System.out.println("Voltando para a view anterior: " + previousView);

            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Não há histórico para voltar.");
        }
    }
}
