package fr.meow.simulator.core.entity.employee;

import fr.meow.simulator.core.Magasin;
import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.games.Game;
import fr.meow.simulator.core.games.GameTier;
import fr.meow.simulator.core.games.GamesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Buyer extends Employee {
    private static final int MIN_STOCK_LEVEL = 1;
    private static final double WALLET_RESERVE_RATIO = 0.3;

    public Buyer(String color, String name, String type) {
        super(color, name, type);
    }

    @Override
    public void DoTask() {
        Player player = Magasin.getInstance().getPlayer();
        GamesManager gamesManager = Magasin.getInstance().getGamesManager();
        
        ArrayList<Game> unlockedGames = gamesManager.getUnlockedGames();
        
        List<Game> outOfStockGames = new ArrayList<>();
        for (Game game : unlockedGames) {
            int totalCount = player.countTotalGame(game);
            if (totalCount < MIN_STOCK_LEVEL) {
                outOfStockGames.add(game);
            }
        }
        
        if (outOfStockGames.isEmpty()) {
            return;
        }
        
        outOfStockGames.sort(Comparator.comparingDouble(Game::getMarketPrice));
        
        double availableMoney = player.getWallet() * (1.0 - WALLET_RESERVE_RATIO);
        
        for (Game game : outOfStockGames) {
            double price = game.getMarketPrice();
            if (price <= availableMoney && price <= player.getWallet()) {
                player.removeToWallet(price);
                player.addToCurrentGames(game);
                availableMoney -= price;
            } else {
                break;
            }
        }
    }

    public static double calculateWage() {
        GamesManager gamesManager = Magasin.getInstance().getGamesManager();
        ArrayList<Game> unlockedGames = gamesManager.getUnlockedGames();
        
        if (unlockedGames.isEmpty()) {
            return 50.0;
        }
        
        int firstTierCount = 0;
        int secondTierCount = 0;
        int thirdTierCount = 0;
        
        for (Game game : unlockedGames) {
            GameTier tier = game.getGameTier();
            if (tier == GameTier.FIRST) {
                firstTierCount++;
            } else if (tier == GameTier.SECOND) {
                secondTierCount++;
            } else if (tier == GameTier.THIRD) {
                thirdTierCount++;
            }
        }
        
        double baseWage = 50.0;
        double wageMultiplier = 1.0 + (firstTierCount * 0.01) + (secondTierCount * 0.02) + (thirdTierCount * 0.03);
        
        return baseWage * wageMultiplier;
    }

    public double getDailyWage() {
        return calculateWage();
    }
}
