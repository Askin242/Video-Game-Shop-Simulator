package fr.meow.simulator.core.game;

public interface GameInterface {
    String getName();

    double getMarketPrice();

    double getSellingPrice();

    int getMarketWeight();

    int getVolatility();

    GameType getGameType();

    GameTier getGameTier();

    void setMarketPrice(double marketPrice);

    void setSellingPrice(double sellingPrice);

}
