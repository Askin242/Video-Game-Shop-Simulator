package fr.meow.simulator.core.entity;

import fr.meow.simulator.core.SimulatorObject;
import fr.meow.simulator.core.games.Game;

import java.util.ArrayList;

public class Player extends SimulatorObject {
    private double wallet;
    private final ArrayList<Game> currentGames;
    private final ArrayList<Game> sellingGames;

    public Player(double wallet) {
        super("Player");

        this.wallet = wallet;
        this.currentGames = new ArrayList<>();
        this.sellingGames = new ArrayList<>();
    }

    public double getWallet() {
        return this.wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void addToWallet(double amount) {
        this.wallet += amount;
    }

    public void removeToWallet(double amount) {
        this.wallet -= amount;
    }

    public ArrayList<Game> getCurrentGames() {
        return this.currentGames;
    }

    public ArrayList<Game> getSellingGames() {
        return this.sellingGames;
    }

    public void addToCurrentGames(Game game) {
        this.currentGames.add(game);
    }

    public void addToSellingGames(Game game) {
        this.currentGames.remove(game);
        this.sellingGames.add(game);
    }

    public void removeFromSelling(Game game) {
        this.sellingGames.remove(game);
    }

    public int countGameInInventory(Game game) {
        int count = 0;
        for (Game g : currentGames) {
            if (g.getName().equals(game.getName())) {
                count++;
            }
        }
        return count;
    }

    public int countGameOnShelf(Game game) {
        int count = 0;
        for (Game g : sellingGames) {
            if (g.getName().equals(game.getName())) {
                count++;
            }
        }
        return count;
    }

    public int countTotalGame(Game game) {
        return countGameInInventory(game) + countGameOnShelf(game);
    }
}
