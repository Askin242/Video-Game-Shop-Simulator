package fr.meow.simulator.core.entity.client;

import fr.meow.simulator.core.SimulatorObject;
import fr.meow.simulator.core.VideoGameSimulator;
import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.games.Game;
import fr.meow.simulator.core.games.GamesManager;
import fr.meow.simulator.utils.MathUtils;

import java.util.ArrayList;

public class Client extends SimulatorObject {

    private static final double MAX_PRICE_MULTIPLIER = 1.125;

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
        int unlockedGames = VideoGameSimulator.getInstance().getGamesManager().getUnlockedGames().size();

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
        ArrayList<Game> games = null;
        try {
            games = VideoGameSimulator.getInstance().getGamesManager().getUnlockedGames();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int wantedAmount = getProductsAmounts();
        int attempts = 0;

        while (res.size() < wantedAmount && attempts < 50) {
            Game chosenGame = games.get(MathUtils.randomInt(0, games.size()));

            if (canBuy(chosenGame)) {
                res.add(chosenGame);
            }

            attempts++;
        }

        return res;
    }

    public void BuyGame() {
        ArrayList<Game> selling = VideoGameSimulator.getInstance().getPlayer().getSellingGames();

        for (int i = 0; i < this.lookingFor.size(); i++) {
            if (selling.contains(basket.get(i))) {
                IO.println("can buy");
            } else {
                IO.println("can't buy");
            }
        }
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Game> getLookingFor() {
        return lookingFor;
    }

    public void addLookingFor(Game game) {
        if (canBuy(game)) {
            this.lookingFor.add(game);
        }
    }

    public void removeLookingFor(Game game) {
        this.lookingFor.remove(game);
    }

    public ArrayList<Game> getBasket() {
        return basket;
    }

    public boolean addBasket(Game game) {
        if (!canBuy(game)) {
            return false;
        }

        this.basket.add(game);
        return true;
    }

    public void removeBasket(Game game) {
        this.basket.remove(game);
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
}
