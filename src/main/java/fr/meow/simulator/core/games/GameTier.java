package fr.meow.simulator.core.games;

public enum GameTier {
    FIRST(1.00, 0, 0, 0.10),
    SECOND(1.15, 1, 1, 0.15),
    THIRD(1.30, 2, 2, 0.20);

    private final double priceMultiplier;
    private final int marketWeightDelta;
    private final int volatilityDelta;
    private final double randomRange;

    GameTier(double priceMultiplier, int marketWeightDelta, int volatilityDelta, double randomRange) {
        this.priceMultiplier = priceMultiplier;
        this.marketWeightDelta = marketWeightDelta;
        this.volatilityDelta = volatilityDelta;
        this.randomRange = randomRange;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public int getMarketWeightDelta() {
        return marketWeightDelta;
    }

    public int getVolatilityDelta() {
        return volatilityDelta;
    }

    public double getRandomRange() {
        return randomRange;
    }
}
