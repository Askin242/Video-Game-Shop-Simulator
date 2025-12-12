package fr.meow.simulator.core.save;

import java.io.Serial;
import java.io.Serializable;

public class UnlockedTier implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String gameType;
    private final String gameTier;

    public UnlockedTier(String gameType, String gameTier) {
        this.gameType = gameType;
        this.gameTier = gameTier;
    }

    public String getGameType() {
        return gameType;
    }

    public String getGameTier() {
        return gameTier;
    }
}

