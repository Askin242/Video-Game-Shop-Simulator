package fr.meow.simulator.ui;

import fr.meow.simulator.core.VideoGameSimulator;
import fr.meow.simulator.core.games.Game;
import fr.meow.simulator.core.games.GameTier;
import fr.meow.simulator.core.games.GameType;
import fr.meow.simulator.core.games.GamesManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ShopWindow {

    private final Stage stage;
    private final MainWindow mainWindow;
    private TableView<Game> gamesTable;
    private ObservableList<Game> gameItems;
    private Label walletLabel;
    private Label totalLabel;
    private Label cartBreakdownLabel;
    private Button purchaseButton;
    private final Map<Game, Integer> quantities = new HashMap<>();
    private final Map<String, Button> tierUnlockButtons = new HashMap<>();

    public ShopWindow(Stage stage, MainWindow mainWindow) {
        this.stage = stage;
        this.mainWindow = mainWindow;
    }

    public void show() {
        stage.setScene(buildScene());
        stage.setTitle("Shop - Video Game Shop Simulator");
        stage.show();
    }

    private Scene buildScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #1a1a1a;");

        Label title = new Label("Shop");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#f5f5f5"));

        walletLabel = new Label(walletText());
        walletLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        walletLabel.setTextFill(Color.web("#90caf9"));

        Button backButton = new Button("← Go Back");
        backButton.setStyle("-fx-background-color: #3d3d3d; -fx-text-fill: white; -fx-background-radius: 5;");
        backButton.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        backButton.setOnAction(e -> mainWindow.show());
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #505050; -fx-text-fill: white; -fx-background-radius: 5;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #3d3d3d; -fx-text-fill: white; -fx-background-radius: 5;"));

        HBox leftBox = new HBox(15, backButton, title);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        HBox topBar = new HBox(20);
        topBar.getChildren().addAll(leftBox, walletLabel);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 0, 15, 0));
        HBox.setHgrow(leftBox, javafx.scene.layout.Priority.ALWAYS);
        BorderPane.setAlignment(topBar, Pos.CENTER_LEFT);
        root.setTop(topBar);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(buildEmployeesTab(), buildGamesTab(), buildTiersTab());
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle(
            "-fx-background-color: #1a1a1a;" +
            "-fx-border-color: #2d2d2d;" +
            "-fx-border-width: 1;"
        );
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1200, 650);
        scene.getStylesheets().add(getClass().getResource("/css/shop.css").toExternalForm());
        return scene;
    }

    private Tab buildEmployeesTab() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));
        box.setStyle("-fx-background-color: #1a1a1a;");

        Label placeholder = new Label("Hiring employees coming soon.");
        placeholder.setFont(Font.font("Segoe UI", 16));
        placeholder.setTextFill(Color.web("#bbbbbb"));
        box.getChildren().add(placeholder);

        Tab tab = new Tab("Employees");
        tab.setContent(box);
        return tab;
    }

    private Tab buildGamesTab() {
        gameItems = FXCollections.observableArrayList(unlockedGames());
        gamesTable = new TableView<>(gameItems);
        gamesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        gamesTable.setStyle("-fx-base: #1a1a1a; -fx-control-inner-background: #1a1a1a; -fx-text-inner-color: #f5f5f5;");

        TableColumn<Game, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Game, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGameType().name()));

        TableColumn<Game, String> tierCol = new TableColumn<>("Tier");
        tierCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGameTier().name()));

        TableColumn<Game, Double> buyCol = new TableColumn<>("Market Price");
        buyCol.setCellValueFactory(new PropertyValueFactory<>("marketPrice"));

        TableColumn<Game, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellFactory(buildQuantitySpinnerFactory());

        gamesTable.getColumns().addAll(nameCol, typeCol, tierCol, buyCol, qtyCol);

        totalLabel = new Label("Total: $0.00");
        totalLabel.setTextFill(Color.web("#f5f5f5"));
        totalLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));

        cartBreakdownLabel = new Label("Cart is empty");
        cartBreakdownLabel.setTextFill(Color.web("#f5f5f5"));
        cartBreakdownLabel.setFont(Font.font("Segoe UI", 13));

        ScrollPane cartScrollPane = new ScrollPane(cartBreakdownLabel);
        cartScrollPane.setFitToWidth(true);
        cartScrollPane.setMaxHeight(150);
        cartScrollPane.setMinHeight(50);
        cartScrollPane.setStyle("-fx-background: #1a1a1a; -fx-background-color: #1a1a1a;");

        purchaseButton = new Button("Purchase");
        purchaseButton.setOnAction(e -> buyCart());
        purchaseButton.setDisable(true);

        VBox summaryBox = new VBox(6, totalLabel, cartScrollPane, purchaseButton);
        summaryBox.setAlignment(Pos.CENTER_LEFT);
        summaryBox.setPadding(new Insets(10, 0, 0, 0));

        VBox box = new VBox(10, gamesTable, summaryBox);
        box.setPadding(new Insets(20));
        box.setStyle("-fx-background-color: #1a1a1a;");

        updateTotals();

        Tab tab = new Tab("Games");
        tab.setContent(box);
        return tab;
    }

    private Callback<TableColumn<Game, Integer>, TableCell<Game, Integer>> buildQuantitySpinnerFactory() {
        return column -> new TableCell<>() {
            private Spinner<Integer> spinner;

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (spinner == null) {
                    spinner = new Spinner<>();
                    spinner.setPrefWidth(90);
                    spinner.setEditable(true);
                    spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
                    spinner.valueProperty().addListener((obs, oldVal, newVal) -> {
                        Game game = getTableView().getItems().get(getIndex());
                        quantities.put(game, newVal);
                        updateTotals();
                    });
                }
                Game rowGame = getTableView().getItems().get(getIndex());
                int current = quantities.getOrDefault(rowGame, 0);
                spinner.getValueFactory().setValue(current);
                setGraphic(spinner);
            }
        };
    }

    private Tab buildTiersTab() {
        VBox wrapper = new VBox(12);
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #1a1a1a;");

        for (GameType type : GameType.values()) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);

            Label typeLabel = new Label(type.name());
            typeLabel.setMinWidth(120);
            typeLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
            typeLabel.setTextFill(Color.web("#ffffff"));
            row.getChildren().add(typeLabel);

            for (GameTier tier : GameTier.values()) {
                double cost = tierUnlockCost(type, tier);
                Button unlockBtn = new Button("Unlock " + tier.name() + " ($" + String.format("%.2f", cost) + ")");

                String key = type.name() + "_" + tier.name();
                tierUnlockButtons.put(key, unlockBtn);

                unlockBtn.setOnAction(e -> {
                    if (VideoGameSimulator.getInstance().getPlayer().getWallet() < cost || isTierUnlocked(type, tier)) {
                        return;
                    }
                    VideoGameSimulator.getInstance().getPlayer().removeToWallet(cost);
                    walletLabel.setText(walletText());
                    VideoGameSimulator.getInstance().getGamesManager().unlockGameTier(type, tier);
                    refreshGamesTable();
                    updateTotals();
                    updateTierButtonStates();
                });
                row.getChildren().add(unlockBtn);
            }

            wrapper.getChildren().add(row);
        }

        updateTierButtonStates();

        Tab tab = new Tab("Tiers");
        tab.setContent(wrapper);
        return tab;
    }

    private void refreshGamesTable() {
        gameItems.setAll(unlockedGames());
        gamesTable.refresh();
    }

    private void buyCart() {
        double total = calculateTotal();
        if (total <= 0 || VideoGameSimulator.getInstance().getPlayer().getWallet() < total) {
            return;
        }
        VideoGameSimulator.getInstance().getPlayer().removeToWallet(total);
        walletLabel.setText(walletText());

        quantities.entrySet().stream()
                .filter(e -> e.getValue() != null && e.getValue() > 0)
                .forEach(e -> {
                    Game game = e.getKey();
                    int quantity = e.getValue();
                    for (int i = 0; i < quantity; i++) {
                        VideoGameSimulator.getInstance().getPlayer().addToCurrentGames(game);
                    }
                });

        quantities.replaceAll((g, v) -> 0);
        updateTotals();
        updateTierButtonStates();
        gamesTable.refresh();
    }

    private void updateTotals() {
        double total = calculateTotal();
        totalLabel.setText(String.format("Total: $%.2f", total));

        String breakdown = quantities.entrySet().stream()
                .filter(e -> e.getValue() != null && e.getValue() > 0)
                .map(e -> {
                    Game g = e.getKey();
                    int qty = e.getValue();
                    double price = g.getMarketPrice() * qty;
                    return g.getName() + " x" + qty + " = $" + String.format("%.2f", price);
                })
                .collect(Collectors.joining("\n"));
        if (breakdown.isBlank()) {
            cartBreakdownLabel.setText("Cart is empty");
        } else {
            cartBreakdownLabel.setText(breakdown);
        }
        purchaseButton.setDisable(total <= 0 || VideoGameSimulator.getInstance().getPlayer().getWallet() < total);
    }

    private double calculateTotal() {
        return quantities.entrySet().stream()
                .filter(e -> e.getValue() != null && e.getValue() > 0)
                .mapToDouble(e -> e.getKey().getMarketPrice() * e.getValue())
                .sum();
    }

    private double tierUnlockCost(GameType type, GameTier tier) {
        return type.getBaseSellingPrice() * tier.getPriceMultiplier() * 3;
    }

    private ObservableList<Game> unlockedGames() {
        return FXCollections.observableArrayList(
                VideoGameSimulator.getInstance().getGamesManager().gameArrayList.stream()
                        .filter(Game::isUnlocked)
                        .collect(Collectors.toList())
        );
    }

    private String walletText() {
        return String.format("Wallet: $%.2f", VideoGameSimulator.getInstance().getPlayer().getWallet());
    }

    private boolean isTierUnlocked(GameType type, GameTier tier) {
        return VideoGameSimulator.getInstance().getGamesManager().getGamesByTypeAndTier(type, tier).stream()
                .anyMatch(Game::isUnlocked);
    }

    private void updateTierButtonStates() {
        for (GameType type : GameType.values()) {
            for (GameTier tier : GameTier.values()) {
                String key = type.name() + "_" + tier.name();
                Button button = tierUnlockButtons.get(key);
                if (button != null) {
                    double cost = tierUnlockCost(type, tier);
                    boolean isUnlocked = isTierUnlocked(type, tier);
                    boolean canAfford = VideoGameSimulator.getInstance().getPlayer().getWallet() >= cost;
                    button.setDisable(isUnlocked || !canAfford);
                }
            }
        }
    }
}

