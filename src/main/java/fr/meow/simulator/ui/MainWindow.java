package main.java.fr.meow.simulator.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainWindow {

    private final Stage stage;

    public MainWindow(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        stage.setTitle("Video Game Shop Simulator");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e1e;");

        Label titleLabel = new Label("Game Shop Simulator");
        titleLabel.setTextFill(Color.web("#ffffff"));
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titleLabel.setPadding(new Insets(20));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(50));

        Label welcomeLabel = new Label("Welcome to your shop!");
        welcomeLabel.setTextFill(Color.web("#cccccc"));
        welcomeLabel.setFont(Font.font("Segoe UI", 16));

        Button startButton = createStyledButton("Open Shop");
        Button settingsButton = createStyledButton("Settings");
        Button exitButton = createStyledButton("Exit");

        exitButton.setOnAction(e -> stage.close());

        centerBox.getChildren().addAll(welcomeLabel, startButton, settingsButton, exitButton);
        root.setCenter(centerBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        button.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
        
        String normalStyle = "-fx-background-color: #3d3d3d; -fx-text-fill: white; -fx-background-radius: 5;";
        String hoverStyle = "-fx-background-color: #505050; -fx-text-fill: white; -fx-background-radius: 5;";
        
        button.setStyle(normalStyle);
        
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(normalStyle));
        
        return button;
    }

    public void show() {
        stage.show();
    }
}
