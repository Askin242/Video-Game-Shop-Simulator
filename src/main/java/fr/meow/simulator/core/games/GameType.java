package fr.meow.simulator.core.games;

public enum GameType {
    FPS(42, 55, 6, 6),
    Retro(12, 18, 3, 3),
    Sandbox(36, 48, 6, 5),
    Simulator(40, 52, 5, 4),
    Tycoon(30, 40, 4, 4),
    RPG(48, 62, 6, 5);

    private final double baseMarketPrice;
    private final double baseSellingPrice;
    private final int baseMarketWeight;
    private final int baseVolatility;

    GameType(double baseMarketPrice, double baseSellingPrice, int baseMarketWeight, int baseVolatility) {
        this.baseMarketPrice = baseMarketPrice;
        this.baseSellingPrice = baseSellingPrice;
        this.baseMarketWeight = baseMarketWeight;
        this.baseVolatility = baseVolatility;
    }

    public double getBaseMarketPrice() {
        return baseMarketPrice;
    }

    public double getBaseSellingPrice() {
        return baseSellingPrice;
    }

    public int getBaseMarketWeight() {
        return baseMarketWeight;
    }

    public int getBaseVolatility() {
        return baseVolatility;
    }
}
