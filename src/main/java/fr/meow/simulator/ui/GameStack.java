package fr.meow.simulator.ui;

import fr.meow.simulator.core.games.Game;

public class GameStack {

    public Game game;
    public int inventoryCount;
    public int shelfCount;

    public GameStack(Game game) {
        this.game = game;
        this.inventoryCount = 0;
        this.shelfCount = 0;
    }
}

