package fr.meow.simulator.core;

import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.entity.client.ClientManager;
import fr.meow.simulator.core.games.GamesManager;

public class Magasin {
    private static Magasin simulator;
    private final Player player;
    private final GamesManager gamesManager;
    private final ClientManager clientManager;
    private final Clock clock;
    private NotificationListener notificationListener;

    public Magasin() {
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

    public static synchronized Magasin getInstance() {
        if (simulator == null) {
            simulator = new Magasin();
        }
        return simulator;
    }

    public static synchronized void reset() {
        simulator = new Magasin();
    }
}
