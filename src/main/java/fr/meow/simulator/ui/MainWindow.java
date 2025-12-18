package fr.meow.simulator.ui;

import fr.meow.simulator.core.Magasin;
import fr.meow.simulator.core.save.Save;
import fr.meow.simulator.core.save.SaveManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MainWindow {

    private final Stage stage;
    private Scene mainScene;

    private final SaveManager saveManager = new SaveManager();
    private GameWindow currentGameWindow;

    @FXML
    private Label titleLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    public MainWindow(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/meow/simulator/ui/main_window.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            mainScene = new Scene(root, 1200, 600);
            stage.setScene(mainScene);
            stage.setTitle("Video Game Shop Simulator");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load main_window.fxml", e);
        }
    }

    @FXML
    private void initialize() {
        if (startButton != null) {
            startButton.setText("New Game");
        }
        if (settingsButton != null) {
            settingsButton.setText("Load Game");
        }
        setupButtons();
    }

    private void setupButtons() {
        startButton.setOnAction(e -> startNewGame());
        settingsButton.setOnAction(e -> loadExistingGame());
        exitButton.setOnAction(e -> stage.close());
    }

    private void startNewGame() {
        Magasin.reset();
        openGameWindow();
    }

    private void loadExistingGame() {
        List<String> saves = saveManager.getAvailableSaves();
        if (saves.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Saves");
            alert.setHeaderText(null);
            alert.setContentText("No save files were found.");
            alert.initOwner(stage);
            alert.showAndWait();
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(saves.getFirst(), FXCollections.observableArrayList(saves));
        dialog.setTitle("Load Game");
        dialog.setHeaderText("Select a save to load");
        dialog.setContentText("Save:");
        dialog.initOwner(stage);

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) {
            return;
        }

        Save save = saveManager.loadGame(Magasin.getInstance(), result.get());
        if (save == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Load Failed");
            alert.setHeaderText(null);
            alert.setContentText("Unable to load the selected save.");
            alert.initOwner(stage);
            alert.showAndWait();
            return;
        }
        openGameWindow();
        if (currentGameWindow != null && save.getClients() != null) {
            currentGameWindow.restoreClientsFromSave(save.getClients());
        }
    }

    private void openGameWindow() {
        if (currentGameWindow != null) {
            currentGameWindow.hide();
        }
        currentGameWindow = new GameWindow(stage, this);
        currentGameWindow.show();
    }

    public void show() {
        stage.setScene(mainScene);
        stage.setTitle("Video Game Shop Simulator");
        stage.show();
    }
}
