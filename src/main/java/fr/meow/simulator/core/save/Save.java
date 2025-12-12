package fr.meow.simulator.core.save;

import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.games.Game;
import fr.meow.simulator.core.games.GamesManager;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private double playerWallet;
    private final List<String> playerCurrentGameNames;
    private final List<String> playerSellingGameNames;

    private final List<GameSaveData> gameStates;
    private final List<UnlockedTier> unlockedTiers;

    public Save(Player player, GamesManager gamesManager) {
        this.playerWallet = player.getWallet();
        this.playerCurrentGameNames = new ArrayList<>();
        this.playerSellingGameNames = new ArrayList<>();

        for (Game game : player.getCurrentGames()) {
            this.playerCurrentGameNames.add(game.getName());
        }

        for (Game game : player.getSellingGames()) {
            this.playerSellingGameNames.add(game.getName());
        }

        this.gameStates = new ArrayList<>();
        for (Game game : gamesManager.gameArrayList) {
            gameStates.add(new GameSaveData(
                    game.getName(),
                    game.getMarketPrice(),
                    game.getSellingPrice(),
                    game.isUnlocked()
            ));
        }

        this.unlockedTiers = new ArrayList<>();
        ArrayList<String> processedTiers = new ArrayList<>();
        for (Game game : gamesManager.gameArrayList) {
            if (game.isUnlocked()) {
                String tierKey = game.getGameType().name() + "_" + game.getGameTier().name();
                if (!processedTiers.contains(tierKey)) {
                    unlockedTiers.add(new UnlockedTier(
                            game.getGameType().name(),
                            game.getGameTier().name()
                    ));
                    processedTiers.add(tierKey);
                }
            }
        }
    }

    public Save() {
        this.playerCurrentGameNames = new ArrayList<>();
        this.playerSellingGameNames = new ArrayList<>();
        this.gameStates = new ArrayList<>();
        this.unlockedTiers = new ArrayList<>();
    }

    public double getPlayerWallet() {
        return playerWallet;
    }

    public List<String> getPlayerCurrentGameNames() {
        return playerCurrentGameNames;
    }

    public List<String> getPlayerSellingGameNames() {
        return playerSellingGameNames;
    }

    public List<GameSaveData> getGameStates() {
        return gameStates;
    }

    public List<UnlockedTier> getUnlockedTiers() {
        return unlockedTiers;
    }
}
