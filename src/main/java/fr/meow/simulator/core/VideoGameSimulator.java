package fr.meow.simulator.core;

import fr.meow.simulator.core.entity.Player;

public class VideoGameSimulator {
    private static VideoGameSimulator simulator;
    private final Player player;

    public VideoGameSimulator() {
        this.player = new Player(1000);
    }

    public Player getPlayer() {
        return player;
    }

    public static synchronized VideoGameSimulator getInstance( ) {
        if (simulator == null)
            simulator = new VideoGameSimulator();
        return simulator;
    }
}
