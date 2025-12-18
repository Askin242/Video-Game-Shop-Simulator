package fr.meow.simulator.ui;

import fr.meow.simulator.core.Magasin;
import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.entity.client.Client;
import fr.meow.simulator.core.entity.client.Colors;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.UUID;

public class AdminWindow {

    private final Stage owner;
    private final GameWindow gameWindow;

    private Stage stage;

    public AdminWindow(Stage owner, GameWindow gameWindow) {
        this.owner = owner;
        this.gameWindow = gameWindow;
    }

    public void show() {
        if (stage == null) {
            initUI();
        }
        stage.showAndWait();
    }

    private void initUI() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #1a1a1a;");

        Label title = new Label("Admin Panel");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        title.setTextFill(Color.web("#f5f5f5"));

        Label subtitle = new Label("Debug tools");
        subtitle.setFont(Font.font("Segoe UI", 13));
        subtitle.setTextFill(Color.web("#bbbbbb"));

        VBox header = new VBox(4, title, subtitle);
        root.setTop(header);

        Player player = Magasin.getInstance().getPlayer();

        Button walletButton = new Button("Set Wallet To 99999999999");
        walletButton.setOnAction(e -> {
            player.setWallet(99999999999d);
        });

        ChoiceBox<Colors> colorChoice = new ChoiceBox<>(FXCollections.observableArrayList(Colors.values()));
        colorChoice.getSelectionModel().selectFirst();

        Button spawnButton = new Button("Spawn Client");
        spawnButton.setOnAction(e -> {
            Colors color = colorChoice.getSelectionModel().getSelectedItem();
            String name = "Client-" + UUID.randomUUID();
            Client client = new Client(color.name(), name);
            Magasin.getInstance().getClientManager().addClient(client);
        });

        HBox spawnRow = new HBox(8, new Label("Color"), colorChoice, spawnButton);
        spawnRow.setAlignment(Pos.CENTER_LEFT);

        VBox center = new VBox(12, walletButton, spawnRow);
        center.setAlignment(Pos.CENTER_LEFT);
        center.setPadding(new Insets(15, 0, 0, 0));

        for (Node node : center.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setPrefWidth(260);
            }
        }

        root.setCenter(center);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        HBox bottom = new HBox(closeButton);
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.setPadding(new Insets(10, 0, 0, 0));
        root.setBottom(bottom);

        Scene scene = new Scene(root, 420, 220);

        stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Admin Panel");
        stage.setScene(scene);
    }
}
