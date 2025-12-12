package fr.meow.simulator.core.save;

import java.io.Serial;
import java.io.Serializable;

public class GameSaveData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String gameName;
    private final double marketPrice;
    private final double sellingPrice;
    private final boolean unlocked;

    public GameSaveData(String gameName, double marketPrice, double sellingPrice, boolean unlocked) {
        this.gameName = gameName;
        this.marketPrice = marketPrice;
        this.sellingPrice = sellingPrice;
        this.unlocked = unlocked;
    }

    public String getGameName() {
        return gameName;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public boolean isUnlocked() {
        return unlocked;
    }
}

