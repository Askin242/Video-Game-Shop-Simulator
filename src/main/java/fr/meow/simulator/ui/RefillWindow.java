package fr.meow.simulator.ui;

import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.games.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RefillWindow {

    private final Stage owner;
    private final Player player;
    private Stage stage;

    private ObservableList<GameStack> inventoryItems;
    private ObservableList<GameStack> shelfItems;

    private ListView<GameStack> inventoryList;
    private ListView<GameStack> shelfList;

    private GameStack selectedStack;

    private Spinner<Integer> quantitySpinner;
    private Label marketPriceLabel;
    private TextField sellingPriceField;
    private Label selectionLabel;

    public RefillWindow(Stage owner, Player player, GameWindow gameWindow) {
        this.owner = owner;
        this.player = player;
    }

    public void show() {
        if (stage == null) {
            initUI();
        }
        refreshStacks();
        stage.showAndWait();
    }

    private void initUI() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #151515;");

        Label title = new Label("Refill Shelf");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#f5f5f5"));

        Label subtitle = new Label("Move games between inventory and shelf, and set selling prices.");
        subtitle.setFont(Font.font("Segoe UI", 13));
        subtitle.setTextFill(Color.web("#bbbbbb"));

        VBox header = new VBox(4, title, subtitle);
        root.setTop(header);

        inventoryItems = FXCollections.observableArrayList();
        shelfItems = FXCollections.observableArrayList();

        inventoryList = new ListView<>(inventoryItems);
        shelfList = new ListView<>(shelfItems);

        inventoryList.setCellFactory(list -> new GameStackCell());
        shelfList.setCellFactory(list -> new GameStackCell());

        String listStyle = "-fx-background-color: #1a1a1a; -fx-control-inner-background: #1a1a1a; -fx-text-fill: #f5f5f5;";
        inventoryList.setStyle(listStyle);
        shelfList.setStyle(listStyle);

        inventoryList.setPrefWidth(330);
        shelfList.setPrefWidth(330);
        inventoryList.setPrefHeight(360);
        shelfList.setPrefHeight(360);

        inventoryList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            shelfList.getSelectionModel().clearSelection();
            setSelection(newVal, true);
        });

        shelfList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            inventoryList.getSelectionModel().clearSelection();
            setSelection(newVal, false);
        });

        inventoryList.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                selectedStack = inventoryList.getSelectionModel().getSelectedItem();
                quantitySpinner.getValueFactory().setValue(1);
                moveToShelf();
            }
        });

        shelfList.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                selectedStack = shelfList.getSelectionModel().getSelectedItem();
                quantitySpinner.getValueFactory().setValue(1);
                moveToInventory();
            }
        });

        VBox leftBox = new VBox(6, createSectionLabel("Inventory"), inventoryList);
        VBox rightBox = new VBox(6, createSectionLabel("On Shelf"), shelfList);

        leftBox.setAlignment(Pos.TOP_LEFT);
        rightBox.setAlignment(Pos.TOP_LEFT);

        quantitySpinner = new Spinner<>();
        quantitySpinner.setEditable(true);
        quantitySpinner.setPrefWidth(120);
        quantitySpinner.setStyle("-fx-base: #262626; -fx-control-inner-background: #262626; -fx-text-inner-color: #ffffff;");

        marketPriceLabel = new Label("-");
        marketPriceLabel.setFont(Font.font("Segoe UI", 13));
        marketPriceLabel.setTextFill(Color.web("#dddddd"));

        sellingPriceField = new TextField();
        sellingPriceField.setPromptText("Selling price");
        sellingPriceField.setPrefWidth(140);
        sellingPriceField.setStyle("-fx-background-color: #262626; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #777777; -fx-border-color: #444444;");
        sellingPriceField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (selectedStack == null) {
                return;
            }
            double updated = parsePrice(sellingPriceField, selectedStack.game.getSellingPrice());
            selectedStack.game.setSellingPrice(updated);
            inventoryList.refresh();
            shelfList.refresh();
        });

        selectionLabel = new Label("Select a game");
        selectionLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
        selectionLabel.setTextFill(Color.web("#f5f5f5"));

        Button toShelfButton = new Button("→ Put On Shelf");
        Button toInventoryButton = new Button("← Return To Inventory");

        styleButton(toShelfButton);
        styleButton(toInventoryButton);

        toShelfButton.setOnAction(e -> moveToShelf());
        toInventoryButton.setOnAction(e -> moveToInventory());

        HBox quantityRow = new HBox(8, label("Quantity"), quantitySpinner);
        quantityRow.setAlignment(Pos.CENTER_LEFT);

        HBox marketRow = new HBox(8, label("Market"), marketPriceLabel);
        marketRow.setAlignment(Pos.CENTER_LEFT);

        HBox sellingRow = new HBox(8, label("Selling"), sellingPriceField);
        sellingRow.setAlignment(Pos.CENTER_LEFT);

        VBox centerBox = new VBox(10, selectionLabel, quantityRow, marketRow, sellingRow, toShelfButton, toInventoryButton);
        centerBox.setAlignment(Pos.CENTER_LEFT);

        HBox lists = new HBox(18, leftBox, centerBox, rightBox);
        lists.setAlignment(Pos.CENTER);
        lists.setPadding(new Insets(15, 0, 15, 0));

        root.setCenter(lists);

        Button closeButton = new Button("Close");
        styleButton(closeButton);
        closeButton.setOnAction(e -> stage.close());

        HBox bottom = new HBox(closeButton);
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.setPadding(new Insets(10, 0, 0, 0));

        root.setBottom(bottom);

        Scene scene = new Scene(root, 960, 540);

        stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Refill Shelf");
        stage.setScene(scene);
    }

    private void refreshStacks() {
        Map<Game, GameStack> map = new HashMap<>();

        for (Game game : new ArrayList<>(player.getCurrentGames())) {
            GameStack stack = map.computeIfAbsent(game, GameStack::new);
            stack.inventoryCount++;
        }

        for (Game game : new ArrayList<>(player.getSellingGames())) {
            GameStack stack = map.computeIfAbsent(game, GameStack::new);
            stack.shelfCount++;
        }

        inventoryItems.clear();
        shelfItems.clear();
        for (GameStack stack : map.values()) {
            if (stack.inventoryCount > 0) {
                inventoryItems.add(stack);
            }
            if (stack.shelfCount > 0) {
                shelfItems.add(stack);
            }
        }

        selectedStack = null;
        selectionLabel.setText("Select a game");
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
        marketPriceLabel.setText("-");
        sellingPriceField.clear();
    }

    private void setSelection(GameStack stack, boolean fromInventory) {
        selectedStack = stack;
        int max = fromInventory ? stack.inventoryCount : stack.shelfCount;
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max, Math.min(1, max)));
        marketPriceLabel.setText(String.format("$%.2f", stack.game.getMarketPrice()));
        sellingPriceField.setText(String.format("%.2f", stack.game.getSellingPrice()));
        String origin = fromInventory ? "Inventory" : "On Shelf";
        selectionLabel.setText(stack.game.getName() + " (" + origin + ")");
    }

    private void moveToShelf() {
        int quantity = quantitySpinner.getValue();
        double selling = parsePrice(sellingPriceField, selectedStack.game.getSellingPrice());
        selectedStack.game.setSellingPrice(selling);
        for (int i = 0; i < quantity; i++) {
            player.addToSellingGames(selectedStack.game);
        }
        refreshStacks();
    }

    private void moveToInventory() {
        int quantity = quantitySpinner.getValue();
        for (int i = 0; i < quantity; i++) {
            player.removeFromSelling(selectedStack.game);
            player.getCurrentGames().add(selectedStack.game);
        }
        refreshStacks();
    }

    private double parsePrice(TextField field, double fallback) {
        try {
            return Double.parseDouble(field.getText().replace(",", "."));
        } catch (Exception e) {
            return fallback;
        }
    }

    private Label createSectionLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
        label.setTextFill(Color.web("#f5f5f5"));
        return label;
    }

    private Label label(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", 13));
        label.setTextFill(Color.web("#dddddd"));
        return label;
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #2d2d2d; -fx-text-fill: #ffffff; -fx-background-radius: 4; -fx-border-color: #444444; -fx-border-radius: 4;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3a3a3a; -fx-text-fill: #ffffff; -fx-background-radius: 4; -fx-border-color: #555555; -fx-border-radius: 4;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #2d2d2d; -fx-text-fill: #ffffff; -fx-background-radius: 4; -fx-border-color: #444444; -fx-border-radius: 4;"));
    }
}

