package fr.meow.simulator.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {

    private final Stage stage;
    private Scene mainScene;

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
        setupButtons();
    }

    private void setupButtons() {
        exitButton.setOnAction(e -> stage.close());
    }

    public void show() {
        stage.setScene(mainScene);
        stage.setTitle("Video Game Shop Simulator");
        stage.show();
    }
}
