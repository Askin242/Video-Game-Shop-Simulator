package fr.meow.simulator.core.game;

public class Game implements GameInterface {
    private final String name;
    private final GameType gameType;
    private final GameTier gameTier;
    private final int volatility;
    private final int marketWeight;
    private double marketPrice;
    private double sellingPrice;

    public Game(String name, double marketPrice, double sellingPrice, int marketWeight, int volatility, GameType gameType, GameTier gameTier) {
        this.name = name;
        this.marketPrice = marketPrice;
        this.sellingPrice = sellingPrice;
        this.marketWeight = marketWeight;
        this.volatility = volatility;
        this.gameType = gameType;
        this.gameTier = gameTier;
    }

    public String getName() {
        return name;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public int getMarketWeight() {
        return marketWeight;
    }

    public int getVolatility() {
        return volatility;
    }

    public GameTier getGameTier() {
        return gameTier;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
