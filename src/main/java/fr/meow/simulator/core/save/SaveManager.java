package fr.meow.simulator.core.save;

import fr.meow.simulator.core.VideoGameSimulator;
import fr.meow.simulator.core.entity.Player;
import fr.meow.simulator.core.games.Game;
import fr.meow.simulator.core.games.GameTier;
import fr.meow.simulator.core.games.GameType;
import fr.meow.simulator.core.games.GamesManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private static final String SAVE_DIRECTORY = "saves";

    public boolean saveGame(VideoGameSimulator simulator, String filename) {
        try {
            Path saveDir = Paths.get(SAVE_DIRECTORY);
            if (!Files.exists(saveDir)) {
                Files.createDirectories(saveDir);
            }

            if (!filename.endsWith(".dat")) {
                filename += ".dat";
            }

            Save save = new Save(simulator.getPlayer(), simulator.getGamesManager());

            Path savePath = saveDir.resolve(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(savePath.toFile())
            );
            objectOutputStream.writeObject(save);
            objectOutputStream.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadGame(VideoGameSimulator simulator, String filename) {
        try {
            if (!filename.endsWith(".dat")) {
                filename += ".dat";
            }

            Path savePath = Paths.get(SAVE_DIRECTORY, filename);

            if (!Files.exists(savePath)) {
                System.err.println("Save file not found: " + savePath);
                return false;
            }

            Save save;
            ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(savePath.toFile())
            );
            save = (Save) objectInputStream.readObject();
            objectInputStream.close();

            restoreGameState(simulator, save);

            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void restoreGameState(VideoGameSimulator simulator, Save save) {
        Player player = simulator.getPlayer();
        GamesManager gamesManager = simulator.getGamesManager();

        player.setWallet(save.getPlayerWallet());

        player.getCurrentGames().clear();
        for (String gameName : save.getPlayerCurrentGameNames()) {
            Game game = gamesManager.getGameByName(gameName);
            if (game != null) {
                player.addToCurrentGames(game);
            }
        }

        player.getSellingGames().clear();
        for (String gameName : save.getPlayerSellingGameNames()) {
            Game game = gamesManager.getGameByName(gameName);
            if (game != null) {
                player.addToSellingGames(game);
            }
        }

        for (GameSaveData gameData : save.getGameStates()) {
            Game game = gamesManager.getGameByName(gameData.getGameName());
            if (game != null) {
                game.setMarketPrice(gameData.getMarketPrice());
                game.setSellingPrice(gameData.getSellingPrice());
                game.setUnlocked(gameData.isUnlocked());
            }
        }

        for (UnlockedTier unlockedTier : save.getUnlockedTiers()) {
            try {
                GameType gameType = GameType.valueOf(unlockedTier.getGameType());
                GameTier gameTier = GameTier.valueOf(unlockedTier.getGameTier());
                gamesManager.unlockGameTier(gameType, gameTier);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid game type or tier in save: " + unlockedTier.getGameType() + "_" + unlockedTier.getGameTier());
            }
        }
    }

    public boolean saveExists(String filename) {
        if (!filename.endsWith(".dat")) {
            filename += ".dat";
        }
        Path savePath = Paths.get(SAVE_DIRECTORY, filename);
        return Files.exists(savePath);
    }

    public List<String> getAvailableSaves() {
        List<String> saves = new ArrayList<>();
        try {
            Path saveDir = Paths.get(SAVE_DIRECTORY);
            if (Files.exists(saveDir)) {
                Files.list(saveDir)
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".dat"))
                        .forEach(path -> saves.add(path.getFileName().toString()));
            }
        } catch (IOException e) {
            System.err.println("Error listing save files: " + e.getMessage());
        }
        return saves;
    }

    public boolean deleteSave(String filename) {
        try {
            String saveFileName = filename;
            if (!saveFileName.endsWith(".dat")) {
                saveFileName += ".dat";
            }
            Path savePath = Paths.get(SAVE_DIRECTORY, saveFileName);
            return Files.deleteIfExists(savePath);
        } catch (IOException e) {
            System.err.println("Error deleting save file: " + e.getMessage());
            return false;
        }
    }
}

