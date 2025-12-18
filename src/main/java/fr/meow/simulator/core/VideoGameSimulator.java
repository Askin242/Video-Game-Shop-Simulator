package fr.meow.simulator.core;

import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.games.GamesManager;

public class VideoGameSimulator {
    private static VideoGameSimulator simulator;
    private final Player player;
    private final GamesManager gamesManager;

    public VideoGameSimulator() {
        this.player = new Player(1000);
        this.gamesManager = new GamesManager();
    }

    public Player getPlayer() {
        return player;
    }

    public GamesManager getGamesManager() {
        return gamesManager;
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
