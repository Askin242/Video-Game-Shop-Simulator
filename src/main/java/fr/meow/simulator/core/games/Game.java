package fr.meow.simulator.core.games;

import fr.meow.simulator.core.SimulatorObject;

public class Game extends SimulatorObject implements GameInterface{
    private static final double MIN_MARKET_PRICE = 0.01;
    private static final double MEAN_REVERSION_RATE = 0.1; // how fast price moves toward target

    private final GameType gameType;
    private final GameTier gameTier;
    private final int volatility;
    private final int marketWeight;
    private double marketPrice;
    private double sellingPrice;

    public Game(String name, double marketPrice, double sellingPrice, int marketWeight, int volatility, GameType gameType, GameTier gameTier) {
        super(name);
        this.marketPrice = marketPrice;
        this.sellingPrice = sellingPrice;
        this.marketWeight = marketWeight;
        this.volatility = volatility;
        this.gameType = gameType;
        this.gameTier = gameTier;
    }

    @Override
    public void newGameDay() {
        if (Math.random() <= this.volatility) {
            // Mean-reversion towards the selling price (as a proxy for a fair value).
            double targetPrice = this.sellingPrice > 0 ? this.sellingPrice : this.marketPrice;
            double meanReversion = MEAN_REVERSION_RATE * (targetPrice - this.marketPrice);

            // Noise scaled by marketWeight. Use a light Gaussian (Box-Muller) to avoid huge jumps.
            double sigma = Math.max(0.25d, this.marketWeight * 0.05d);
            double noise = sigma * gaussian();

            this.marketPrice = Math.max(MIN_MARKET_PRICE, this.marketPrice + meanReversion + noise);
        }
    }

    private double gaussian() {
        // Box-Muller transform to approximate N(0,1).
        double u1 = Math.random();
        double u2 = Math.random();
        return Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
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
