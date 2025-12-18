package fr.meow.simulator.core.entity.client;

import fr.meow.simulator.core.SimulatorObject;
import fr.meow.simulator.core.Magasin;
import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.games.Game;
import fr.meow.simulator.utils.MathUtils;

import java.util.ArrayList;

public class Client extends SimulatorObject {

    private static final double MAX_PRICE_MULTIPLIER = 1.4;

    private final String color;
    private final ArrayList<Game> basket;
    private final ArrayList<Game> lookingFor;

    private int xPos;
    private int yPos;

    public Client(String color, String name) {
        super(name);
        this.color = color;
        this.basket = new ArrayList<>();
        this.lookingFor = getChosenProducts();
    }

    private boolean canBuy(Game game) {
        return game.getSellingPrice() <= game.getMarketPrice() * MAX_PRICE_MULTIPLIER;
    }

    private int getProductsAmounts() {
        int unlockedGames = Magasin.getInstance().getGamesManager().getUnlockedGames().size();

        double growthRate = 0.035;
        double scale = 3.0;

        int dynamicMax = (int) Math.floor(
                scale * (Math.exp(growthRate * unlockedGames) - 1)
        );

        dynamicMax = Math.max(1, Math.min(dynamicMax, 15));

        return MathUtils.randomInt(1, dynamicMax);
    }

    private ArrayList<Game> getChosenProducts() {
        ArrayList<Game> res = new ArrayList<>();
        ArrayList<Game> games = Magasin.getInstance().getGamesManager().getUnlockedGames();

        if (games.isEmpty()) {
            return res;
        }

        while (res.size() < getProductsAmounts()) {
            Game chosenGame = games.get(MathUtils.randomInt(0, games.size()-1));
            res.add(chosenGame);
        }

        return res;
    }

    public void BuyGame() {
        Player player = Magasin.getInstance().getPlayer();
        ArrayList<Game> selling = player.getSellingGames();

        for (Game desired : new ArrayList<>(lookingFor)) {
            if (!selling.contains(desired)) {
                if (Magasin.getInstance().getNotificationListener() != null) {
                    Magasin.getInstance().getNotificationListener().notify("No " + desired.getName() + " available.");
                }
                continue;
            }

            if (!canBuy(desired)) {
                if (Magasin.getInstance().getNotificationListener() != null) {
                    Magasin.getInstance().getNotificationListener().notify(desired.getName() + " too expensive.");
                }
                continue;
            }

            try {
                Thread.sleep(MathUtils.randomInt(500, 1500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (selling.contains(desired)) {
                player.removeFromSelling(desired);
                player.addToWallet(desired.getSellingPrice());
                basket.add(desired);
                if (Magasin.getInstance().getNotificationListener() != null) {
                    Magasin.getInstance().getNotificationListener().notify(getName() + " bought " + desired.getName());
                }
            }
        }
    }

    public String getColor() {
        return color;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
}
