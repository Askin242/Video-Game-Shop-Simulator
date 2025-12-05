package fr.meow.simulator.core.game;

import java.util.ArrayList;

public class GameManager {
    public ArrayList<Game> gameArrayList;

    public GameManager() {
        this.gameArrayList = new ArrayList<>();
        gameArrayList.addAll(initializeFps());
    }

    public ArrayList<Game> initializeFps() {
        ArrayList<Game> gameArrayList = new ArrayList<>();
        gameArrayList.add(new Game("Doom Eternal", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Half-Life 2", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Counter-Strike: Global Offensive", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Call of Duty 4: Modern Warfare", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Battlefield 1", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Halo: Combat Evolved", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Titanfall 2", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));

        gameArrayList.add(new Game("Overwatch 2", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Rainbow Six Siege", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Metro Exodus", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Bioshock", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Far Cry 3", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Borderlands 2", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Quake", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));

        gameArrayList.add(new Game("Destiny 2", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Apex Legends", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Crysis", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Wolfenstein: The New Order", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Escape from Tarkov", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Warhammer 40K: Boltgun", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("STALKER: Shadow of Chernobyl", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        return gameArrayList;
    }

    public ArrayList<Game> initializeRpg() {
        ArrayList<Game> gameArrayList = new ArrayList<>();
        gameArrayList.add(new Game("The Witcher 3", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Skyrim", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Dragon Age: Origins", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Baldur’s Gate 3", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Mass Effect 2", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Fallout: New Vegas", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));
        gameArrayList.add(new Game("Divinity: Original Sin 2", 20, 20, 1, 1, GameType.FPS, GameTier.FIRST));

        gameArrayList.add(new Game("Diablo II", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Final Fantasy VII", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Persona 5", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Dark Souls", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Far Cry 3", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Borderlands 2", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));
        gameArrayList.add(new Game("Quake", 20, 20, 1, 1, GameType.FPS, GameTier.SECOND));

        gameArrayList.add(new Game("Destiny 2", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Apex Legends", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Crysis", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Wolfenstein: The New Order", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Escape from Tarkov", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("Warhammer 40K: Boltgun", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        gameArrayList.add(new Game("STALKER: Shadow of Chernobyl", 20, 20, 1, 1, GameType.FPS, GameTier.THIRD));
        return gameArrayList;
    }
}
