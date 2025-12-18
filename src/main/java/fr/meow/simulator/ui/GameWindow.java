package fr.meow.simulator.ui;

import fr.meow.simulator.core.VideoGameSimulator;
import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.entity.client.Client;
import fr.meow.simulator.core.entity.client.ClientListener;
import fr.meow.simulator.core.entity.client.ClientSprite;
import fr.meow.simulator.core.entity.client.Colors;
import fr.meow.simulator.core.save.ClientSaveData;
import fr.meow.simulator.core.save.Point2DSaveData;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWindow implements ClientListener {

    private final Stage stage;
    private final MainWindow mainWindow;
    private Scene scene;
    private Pane clientLayer;

    private final Map<Colors, Image> clientImages = new EnumMap<>(Colors.class);
    private final Map<Client, ClientSprite> clientSprites = new HashMap<>();
    private final List<List<Point2D>> basePaths = new ArrayList<>();

    private AnimationTimer animationTimer;
    private Label walletLabel;

    private String walletText() {
        return String.format("Wallet: $%.2f", VideoGameSimulator.getInstance().getPlayer().getWallet());
    }

    public GameWindow(Stage stage, MainWindow mainWindow) {
        this.stage = stage;
        this.mainWindow = mainWindow;
        initPaths();
        initUI();
        initAnimation();
        VideoGameSimulator.getInstance().getClientManager().addListener(this);
    }

    public void show() {
        stage.setScene(scene);
        stage.setTitle("Shop Floor - Video Game Shop Simulator");
        stage.show();
        animationTimer.start();
    }

    public void hide() {
        animationTimer.stop();
    }

    @Override
    public void onClientAdded(Client client) {
        handleClientSpawn(client);
    }

    @Override
    public void onClientRemoved(Client client) {
        ClientSprite sprite = clientSprites.remove(client);
        if (sprite != null && clientLayer != null) {
            clientLayer.getChildren().remove(sprite.view);
        }
    }

    public void handleClientSpawn(Client client) {
        if (clientSprites.containsKey(client)) {
            return;
        }
        Colors color;
        try {
            color = Colors.valueOf(client.getColor().toUpperCase());
        } catch (IllegalArgumentException e) {
            color = Colors.GREEN;
        }
        ClientSprite sprite = new ClientSprite(client, getClientImage(color), basePaths);
        clientSprites.put(client, sprite);
        clientLayer.getChildren().add(sprite.view);
    }

    private void initUI() {
        Image background = new Image(getClass().getResource("/images/MainBackground.png").toExternalForm());

        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitWidth(1152);
        backgroundView.setFitHeight(768);

        Pane root = new Pane();
        root.setPrefSize(1152, 768);
        root.getChildren().add(backgroundView);

        walletLabel = new Label(walletText());
        walletLabel.setFont(Font.font("Segoe UI", 25));
        walletLabel.setTextFill(Color.web("#ffffff"));
        walletLabel.layoutXProperty().bind(
                root.widthProperty()
                        .subtract(walletLabel.widthProperty())
                        .subtract(20));
        walletLabel.setLayoutY(15);
        root.getChildren().add(walletLabel);

        Rectangle bookshelfOutline = new Rectangle(235, 15, 890, 325);
        bookshelfOutline.setFill(Color.TRANSPARENT);
        bookshelfOutline.setStroke(Color.web("#969eff5e"));
        bookshelfOutline.setStrokeWidth(3);

        DropShadow glowEffect = new DropShadow();
        glowEffect.setRadius(30);
        glowEffect.setColor(Color.web("#ffffff"));

        bookshelfOutline.setOnMouseEntered(e -> {
            bookshelfOutline.setEffect(glowEffect);
            scene.setCursor(Cursor.HAND);
        });

        bookshelfOutline.setOnMouseExited(e -> {
            bookshelfOutline.setEffect(null);
            scene.setCursor(Cursor.DEFAULT);
        });

        root.getChildren().add(bookshelfOutline);

        Rectangle cashierOutline = new Rectangle(675, 540, 90, 100);
        cashierOutline.setFill(Color.TRANSPARENT);
        cashierOutline.setStroke(Color.web("#969eff5e"));
        cashierOutline.setStrokeWidth(3);

        cashierOutline.setOnMouseEntered(e -> {
            cashierOutline.setEffect(glowEffect);
            scene.setCursor(Cursor.HAND);
        });

        cashierOutline.setOnMouseExited(e -> {
            cashierOutline.setEffect(null);
            scene.setCursor(Cursor.DEFAULT);
        });

        root.getChildren().add(cashierOutline);

        clientLayer = new Pane();
        clientLayer.setPickOnBounds(false);
        root.getChildren().add(clientLayer);

        scene = new Scene(root, 1152, 768);

        scene.setOnMouseClicked(event -> {
            if (event.getButton() != MouseButton.PRIMARY) {
                return;
            }
            double x = event.getX();
            double y = event.getY();

            if (isInBookshelfArea(x, y)) {
                openRefillMenu();
            } else if (isInCashierArea(x, y)) {
                openShopMenu();
            }
        });

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F12) {
                openAdminPanel();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                openEscapeMenu();
            }
        });
    }

    private void initPaths() {
        List<Point2D> path1 = new ArrayList<>();
        path1.add(new Point2D(80, 620));
        path1.add(new Point2D(350, 620));
        path1.add(new Point2D(350, 380));
        path1.add(new Point2D(950, 380));
        path1.add(new Point2D(950, 620));
        path1.add(new Point2D(600, 620));

        List<Point2D> path2 = new ArrayList<>();
        path2.add(new Point2D(100, 620));
        path2.add(new Point2D(250, 500));
        path2.add(new Point2D(250, 360));
        path2.add(new Point2D(1050, 360));
        path2.add(new Point2D(1050, 640));
        path2.add(new Point2D(600, 640));

        List<Point2D> path3 = new ArrayList<>();
        path3.add(new Point2D(120, 640));
        path3.add(new Point2D(420, 640));
        path3.add(new Point2D(420, 400));
        path3.add(new Point2D(900, 400));
        path3.add(new Point2D(900, 650));
        path3.add(new Point2D(580, 650));

        basePaths.add(path1);
        basePaths.add(path2);
        basePaths.add(path3);
    }

    private void initAnimation() {
        animationTimer = new AnimationTimer() {
            private long lastTime = -1;

            @Override
            public void handle(long now) {
                if (lastTime < 0) {
                    lastTime = now;
                    return;
                }
                double seconds = (now - lastTime) / 1000000000.0;
                lastTime = now;
                updateClients(seconds);
            }
        };
    }

    private void updateClients(double seconds) {
        if (basePaths.isEmpty()) {
            return;
        }
        for (ClientSprite sprite : clientSprites.values()) {
            Pair<Point2D, Integer> targetInfo = sprite.target();
            Point2D target = targetInfo.getKey();
            int nextIndex = targetInfo.getValue();

            double dx = target.getX() - sprite.x;
            double dy = target.getY() - sprite.y;
            double distance = Math.hypot(dx, dy);

            if (distance < 1) {
                sprite.pathIndex = nextIndex;
                continue;
            }

            double speed = 70;
            double step = speed * seconds;
            if (step > distance) {
                step = distance;
            }

            double nx = sprite.x + dx / distance * step;
            double ny = sprite.y + dy / distance * step;

            sprite.updateDirection(dx, dy);
            sprite.moveTo(nx, ny);
        }

        if (walletLabel != null) {
            walletLabel.setText(walletText());
        }
    }

    private boolean isInBookshelfArea(double x, double y) {
        return x >= 235 && x <= 1125 && y >= 15 && y <= 340;
    }

    private boolean isInCashierArea(double x, double y) {
        return x >= 675 && x <= 900 && y >= 540 && y <= 765;
    }

    private void openRefillMenu() {
        Player player = VideoGameSimulator.getInstance().getPlayer();
        RefillWindow refillWindow = new RefillWindow(stage, player, this);
        refillWindow.show();
    }

    private void openShopMenu() {
        hide();
        ShopWindow shopWindow = new ShopWindow(stage, () -> {
            show();
            stage.requestFocus();
        });
        shopWindow.show();
    }

    private void openAdminPanel() {
        AdminWindow adminWindow = new AdminWindow(stage, this);
        adminWindow.show();
    }

    private void openEscapeMenu() {
        EscapeMenuWindow escapeMenuWindow = new EscapeMenuWindow(stage, mainWindow, this);
        escapeMenuWindow.show();
    }

    private Image getClientImage(Colors color) {
        if (clientImages.containsKey(color)) {
            return clientImages.get(color);
        }
        String fileName = color.name().toLowerCase() + ".png";
        Image image = new Image(
                getClass().getResource("/images/sprite/" + fileName).toExternalForm());
        clientImages.put(color, image);
        return image;
    }

    public List<ClientSaveData> getClientDataForSave() {
        List<ClientSaveData> clientData = new ArrayList<>();
        for (Map.Entry<Client, ClientSprite> entry : clientSprites.entrySet()) {
            Client client = entry.getKey();
            ClientSprite sprite = entry.getValue();
            List<Point2DSaveData> pathData = new ArrayList<>();
            for (Point2D point : sprite.path) {
                pathData.add(new Point2DSaveData(point.getX(), point.getY()));
            }
            clientData.add(new ClientSaveData(
                    client.getColor(),
                    client.getName(),
                    sprite.x,
                    sprite.y,
                    sprite.pathIndex,
                    pathData));
        }
        return clientData;
    }

    public void restoreClientsFromSave(List<ClientSaveData> clientData) {
        clientSprites.clear();
        clientLayer.getChildren().clear();
        for (ClientSaveData data : clientData) {
            Colors color;
            try {
                color = Colors.valueOf(data.getColor().toUpperCase());
            } catch (IllegalArgumentException e) {
                color = Colors.GREEN;
            }
            Client client = new Client(data.getColor(), data.getName());
            Image image = getClientImage(color);
            ClientSprite sprite = new ClientSprite(client, image, basePaths);
            List<Point2D> restoredPath = new ArrayList<>();
            for (Point2DSaveData pointData : data.getPath()) {
                restoredPath.add(new Point2D(pointData.getX(), pointData.getY()));
            }
            sprite.path.clear();
            sprite.path.addAll(restoredPath);
            sprite.x = data.getX();
            sprite.y = data.getY();
            sprite.pathIndex = data.getPathIndex();
            sprite.moveTo(sprite.x, sprite.y);
            clientSprites.put(client, sprite);
            clientLayer.getChildren().add(sprite.view);
        }
    }
}
