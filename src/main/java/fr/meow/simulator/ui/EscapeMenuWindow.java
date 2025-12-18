package fr.meow.simulator.ui;

import fr.meow.simulator.core.VideoGameSimulator;
import fr.meow.simulator.core.save.SaveManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EscapeMenuWindow {

    private final Stage owner;
    private final MainWindow mainWindow;
    private final GameWindow gameWindow;

    private Stage stage;

    public EscapeMenuWindow(Stage owner, MainWindow mainWindow, GameWindow gameWindow) {
        this.owner = owner;
        this.mainWindow = mainWindow;
        this.gameWindow = gameWindow;
    }

    public void show() {
        if (stage == null) {
            initUI();
        }
        stage.showAndWait();
    }

    private void initUI() {
        VBox root = new VBox(14);
        root.setPadding(new Insets(18));
        root.setStyle("-fx-background-color: #1a1a1a;");

        Label title = new Label("Pause");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        title.setTextFill(Color.web("#f5f5f5"));

        Label subtitle = new Label("What do you want to do?");
        subtitle.setFont(Font.font("Segoe UI", 13));
        subtitle.setTextFill(Color.web("#bbbbbb"));

        Button saveAndExit = new Button("Save And Exit Game");
        Button saveAndMenu = new Button("Save And Return To Main Menu");
        Button exitWithoutSave = new Button("Exit Game Without Saving");
        Button backToMenu = new Button("Return To Main Menu");
        Button cancel = new Button("Resume Game");

        saveAndExit.setMaxWidth(Double.MAX_VALUE);
        saveAndMenu.setMaxWidth(Double.MAX_VALUE);
        exitWithoutSave.setMaxWidth(Double.MAX_VALUE);
        backToMenu.setMaxWidth(Double.MAX_VALUE);
        cancel.setMaxWidth(Double.MAX_VALUE);

        styleButton(saveAndExit);
        styleButton(saveAndMenu);
        styleButton(exitWithoutSave);
        styleButton(backToMenu);
        styleButton(cancel);

        TextInputDialog td = new TextInputDialog("savename");
        td.setHeaderText("Enter save name: ");

        SaveManager saveManager = new SaveManager();
        saveAndExit.setOnAction(e -> {
            td.showAndWait();
            saveManager.saveGame(VideoGameSimulator.getInstance(), td.getEditor().getText(), gameWindow.getClientDataForSave());
            owner.close();
        });

        saveAndMenu.setOnAction(e -> {
            td.showAndWait();
            saveManager.saveGame(VideoGameSimulator.getInstance(), td.getEditor().getText(), gameWindow.getClientDataForSave());
            stage.close();
            mainWindow.show();
        });

        exitWithoutSave.setOnAction(e -> {
            owner.close();
        });

        backToMenu.setOnAction(e -> {
            stage.close();
            mainWindow.show();
        });

        cancel.setOnAction(e -> {
            stage.close();
        });

        root.getChildren().addAll(title, subtitle, saveAndExit, saveAndMenu, exitWithoutSave, backToMenu, cancel);

        Scene scene = new Scene(root, 360, 280);

        stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Pause");
        stage.setScene(scene);
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #2d2d2d; -fx-text-fill: #ffffff; -fx-background-radius: 4; -fx-border-color: #444444; -fx-border-radius: 4;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3a3a3a; -fx-text-fill: #ffffff; -fx-background-radius: 4; -fx-border-color: #555555; -fx-border-radius: 4;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #2d2d2d; -fx-text-fill: #ffffff; -fx-background-radius: 4; -fx-border-color: #444444; -fx-border-radius: 4;"));
    }
}

