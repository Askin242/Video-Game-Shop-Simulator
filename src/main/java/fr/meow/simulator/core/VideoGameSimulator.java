package fr.meow.simulator.core;

import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.entity.client.ClientManager;
import fr.meow.simulator.core.games.GamesManager;

public class VideoGameSimulator {
    private static VideoGameSimulator simulator;
    private final Player player;
    private final GamesManager gamesManager;
    private final ClientManager clientManager;
    private final Clock clock;
    private NotificationListener notificationListener;

    public VideoGameSimulator() {
        this.player = new Player(1000);
        this.gamesManager = new GamesManager();
        this.clientManager = new ClientManager();
        this.clock = new Clock();
        this.clock.loop();
    }

    public Player getPlayer() {
        return player;
    }

    public GamesManager getGamesManager() {
        return gamesManager;
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setNotificationListener(NotificationListener listener) {
        this.notificationListener = listener;
    }

    public NotificationListener getNotificationListener() {
        return notificationListener;
    }

    public static synchronized VideoGameSimulator getInstance() {
        if (simulator == null) {
            simulator = new VideoGameSimulator();
        }
        return simulator;
    }

    public static synchronized void reset() {
        simulator = new VideoGameSimulator();
    }
}
