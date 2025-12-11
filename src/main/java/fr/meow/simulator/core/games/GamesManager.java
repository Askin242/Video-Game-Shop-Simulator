package fr.meow.simulator.core.games;

import fr.meow.simulator.utils.MathUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GamesManager {
    public ArrayList<Game> gameArrayList;

    public GamesManager() {
        this.gameArrayList = new ArrayList<>();
        gameArrayList.addAll(initializeFps());
        gameArrayList.addAll(initializeRpg());
        gameArrayList.addAll(initializeTycoon());
        gameArrayList.addAll(initializeSimulator());
        gameArrayList.addAll(initializeRetro());
        gameArrayList.addAll(initializeSandbox());
    }

    public Game getGameByName(String name) {
        return gameArrayList.stream()
                .filter(g -> g.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Game> getGamesByTypeAndTier(GameType type, GameTier tier) {
        return gameArrayList.stream()
                .filter(g -> g.getGameType().equals(type) && g.getGameTier().equals(tier))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Game> getGamesByType(GameType type) {
        return gameArrayList.stream()
                .filter(g -> g.getGameType().equals(type))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void unlockGameTier(GameType type, GameTier tier) {
        for (Game game : getGamesByTypeAndTier(type, tier)) {
            game.setUnlocked(true);
        }
        for (Game game : getGamesByTypeAndTier(type, tier)) {
            IO.println(game.isUnlocked());
        }
    }

    public ArrayList<Game> initializeFps() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(buildGame("Doom Eternal", GameType.FPS, GameTier.FIRST));
        games.add(buildGame("Half-Life 2", GameType.FPS, GameTier.FIRST));
        games.add(buildGame("Counter-Strike: Global Offensive", GameType.FPS, GameTier.FIRST));
        games.add(buildGame("Call of Duty 4: Modern Warfare", GameType.FPS, GameTier.FIRST));
        games.add(buildGame("Battlefield 1", GameType.FPS, GameTier.FIRST));
        games.add(buildGame("Halo: Combat Evolved", GameType.FPS, GameTier.FIRST));
        games.add(buildGame("Titanfall 2", GameType.FPS, GameTier.FIRST));

        games.add(buildGame("Overwatch 2", GameType.FPS, GameTier.SECOND));
        games.add(buildGame("Rainbow Six Siege", GameType.FPS, GameTier.SECOND));
        games.add(buildGame("Metro Exodus", GameType.FPS, GameTier.SECOND));
        games.add(buildGame("Bioshock", GameType.FPS, GameTier.SECOND));
        games.add(buildGame("Far Cry 3", GameType.FPS, GameTier.SECOND));
        games.add(buildGame("Borderlands 2", GameType.FPS, GameTier.SECOND));
        games.add(buildGame("Quake", GameType.FPS, GameTier.SECOND));

        games.add(buildGame("Destiny 2", GameType.FPS, GameTier.THIRD));
        games.add(buildGame("Apex Legends", GameType.FPS, GameTier.THIRD));
        games.add(buildGame("Crysis", GameType.FPS, GameTier.THIRD));
        games.add(buildGame("Wolfenstein: The New Order", GameType.FPS, GameTier.THIRD));
        games.add(buildGame("Escape from Tarkov", GameType.FPS, GameTier.THIRD));
        games.add(buildGame("Warhammer 40K: Boltgun", GameType.FPS, GameTier.THIRD));
        games.add(buildGame("STALKER: Shadow of Chernobyl", GameType.FPS, GameTier.THIRD));
        return games;
    }

    public ArrayList<Game> initializeRpg() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(buildGame("The Witcher 3", GameType.RPG, GameTier.FIRST));
        games.add(buildGame("Skyrim", GameType.RPG, GameTier.FIRST));
        games.add(buildGame("Dragon Age: Origins", GameType.RPG, GameTier.FIRST));
        games.add(buildGame("Baldur’s Gate 3", GameType.RPG, GameTier.FIRST));
        games.add(buildGame("Mass Effect 2", GameType.RPG, GameTier.FIRST));
        games.add(buildGame("Fallout: New Vegas", GameType.RPG, GameTier.FIRST));
        games.add(buildGame("Divinity: Original Sin 2", GameType.RPG, GameTier.FIRST));

        games.add(buildGame("Diablo II", GameType.RPG, GameTier.SECOND));
        games.add(buildGame("Final Fantasy VII", GameType.RPG, GameTier.SECOND));
        games.add(buildGame("Persona 5", GameType.RPG, GameTier.SECOND));
        games.add(buildGame("Dark Souls", GameType.RPG, GameTier.SECOND));
        games.add(buildGame("Elden Ring", GameType.RPG, GameTier.SECOND));
        games.add(buildGame("Xenoblade Chronicles", GameType.RPG, GameTier.SECOND));
        games.add(buildGame("Kingdom Come: Deliverance", GameType.RPG, GameTier.SECOND));

        games.add(buildGame("Path of Exile", GameType.RPG, GameTier.THIRD));
        games.add(buildGame("Star Wars: KOTOR", GameType.RPG, GameTier.THIRD));
        games.add(buildGame("Bloodborne", GameType.RPG, GameTier.THIRD));
        games.add(buildGame("Oblivion", GameType.RPG, GameTier.THIRD));
        games.add(buildGame("Pokémon HeartGold", GameType.RPG, GameTier.THIRD));
        games.add(buildGame("Tales of Arise", GameType.RPG, GameTier.THIRD));
        games.add(buildGame("Monster Hunter: World", GameType.RPG, GameTier.THIRD));
        return games;
    }

    public ArrayList<Game> initializeTycoon() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(buildGame("RollerCoaster Tycoon 2", GameType.Tycoon, GameTier.FIRST));
        games.add(buildGame("Transport Tycoon Deluxe", GameType.Tycoon, GameTier.FIRST));
        games.add(buildGame("Zoo Tycoon", GameType.Tycoon, GameTier.FIRST));
        games.add(buildGame("Planet Coaster", GameType.Tycoon, GameTier.FIRST));
        games.add(buildGame("Game Dev Tycoon", GameType.Tycoon, GameTier.FIRST));
        games.add(buildGame("Capitalism II", GameType.Tycoon, GameTier.FIRST));
        games.add(buildGame("Tropico 5", GameType.Tycoon, GameTier.FIRST));

        games.add(buildGame("Cities: Skylines", GameType.Tycoon, GameTier.SECOND));
        games.add(buildGame("Prison Architect", GameType.Tycoon, GameTier.SECOND));
        games.add(buildGame("Railroad Tycoon 3", GameType.Tycoon, GameTier.SECOND));
        games.add(buildGame("Industry Giant II", GameType.Tycoon, GameTier.SECOND));
        games.add(buildGame("Anno 1404", GameType.Tycoon, GameTier.SECOND));
        games.add(buildGame("Planet Zoo", GameType.Tycoon, GameTier.SECOND));
        games.add(buildGame("SimCity 4", GameType.Tycoon, GameTier.SECOND));

        games.add(buildGame("Factorio", GameType.Tycoon, GameTier.THIRD));
        games.add(buildGame("Mashinky", GameType.Tycoon, GameTier.THIRD));
        games.add(buildGame("Airline Tycoon", GameType.Tycoon, GameTier.THIRD));
        games.add(buildGame("Port Royale 3", GameType.Tycoon, GameTier.THIRD));
        games.add(buildGame("Project Highrise", GameType.Tycoon, GameTier.THIRD));
        games.add(buildGame("Two Point Hospital", GameType.Tycoon, GameTier.THIRD));
        games.add(buildGame("Constructor HD", GameType.Tycoon, GameTier.THIRD));
        return games;
    }

    public ArrayList<Game> initializeSimulator() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(buildGame("Microsoft Flight Simulator", GameType.Simulator, GameTier.FIRST));
        games.add(buildGame("Farming Simulator 22", GameType.Simulator, GameTier.FIRST));
        games.add(buildGame("Euro Truck Simulator 2", GameType.Simulator, GameTier.FIRST));
        games.add(buildGame("American Truck Simulator", GameType.Simulator, GameTier.FIRST));
        games.add(buildGame("The Sims 4", GameType.Simulator, GameTier.FIRST));
        games.add(buildGame("Arma 3", GameType.Simulator, GameTier.FIRST));
        games.add(buildGame("BeamNG.drive", GameType.Simulator, GameTier.FIRST));

        games.add(buildGame("Kerbal Space Program", GameType.Simulator, GameTier.SECOND));
        games.add(buildGame("PowerWash Simulator", GameType.Simulator, GameTier.SECOND));
        games.add(buildGame("Train Simulator Classic", GameType.Simulator, GameTier.SECOND));
        games.add(buildGame("IL-2 Sturmovik", GameType.Simulator, GameTier.SECOND));
        games.add(buildGame("Bus Simulator 21", GameType.Simulator, GameTier.SECOND));
        games.add(buildGame("PC Building Simulator", GameType.Simulator, GameTier.SECOND));
        games.add(buildGame("House Flipper", GameType.Simulator, GameTier.SECOND));

        games.add(buildGame("Car Mechanic Simulator 2021", GameType.Simulator, GameTier.THIRD));
        games.add(buildGame("Football Manager 2024", GameType.Simulator, GameTier.THIRD));
        games.add(buildGame("Surgeon Simulator", GameType.Simulator, GameTier.THIRD));
        games.add(buildGame("Cities in Motion 2", GameType.Simulator, GameTier.THIRD));
        games.add(buildGame("SnowRunner", GameType.Simulator, GameTier.THIRD));
        games.add(buildGame("World of Warships", GameType.Simulator, GameTier.THIRD));
        games.add(buildGame("Airport CEO", GameType.Simulator, GameTier.THIRD));
        return games;
    }

    public ArrayList<Game> initializeRetro() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(buildGame("Super Mario Bros.", GameType.Retro, GameTier.FIRST));
        games.add(buildGame("Sonic the Hedgehog 2", GameType.Retro, GameTier.FIRST));
        games.add(buildGame("Pac-Man", GameType.Retro, GameTier.FIRST));
        games.add(buildGame("Tetris", GameType.Retro, GameTier.FIRST));
        games.add(buildGame("Doom (1993)", GameType.Retro, GameTier.FIRST));
        games.add(buildGame("Chrono Trigger", GameType.Retro, GameTier.FIRST));
        games.add(buildGame("Street Fighter II", GameType.Retro, GameTier.FIRST));

        games.add(buildGame("Castlevania: Symphony of the Night", GameType.Retro, GameTier.SECOND));
        games.add(buildGame("Mega Man 2", GameType.Retro, GameTier.SECOND));
        games.add(buildGame("The Legend of Zelda: A Link to the Past", GameType.Retro, GameTier.SECOND));
        games.add(buildGame("Donkey Kong Country", GameType.Retro, GameTier.SECOND));
        games.add(buildGame("Secret of Mana", GameType.Retro, GameTier.SECOND));
        games.add(buildGame("EarthBound", GameType.Retro, GameTier.SECOND));
        games.add(buildGame("Metroid", GameType.Retro, GameTier.SECOND));

        games.add(buildGame("GoldenEye 007", GameType.Retro, GameTier.THIRD));
        games.add(buildGame("Ultima VII", GameType.Retro, GameTier.THIRD));
        games.add(buildGame("Prince of Persia", GameType.Retro, GameTier.THIRD));
        games.add(buildGame("Final Fantasy VI", GameType.Retro, GameTier.THIRD));
        games.add(buildGame("Dragon Quest III", GameType.Retro, GameTier.THIRD));
        games.add(buildGame("Space Invaders", GameType.Retro, GameTier.THIRD));
        games.add(buildGame("Mortal Kombat II", GameType.Retro, GameTier.THIRD));
        return games;
    }

    public ArrayList<Game> initializeSandbox() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(buildGame("Minecraft", GameType.Sandbox, GameTier.FIRST));
        games.add(buildGame("Terraria", GameType.Sandbox, GameTier.FIRST));
        games.add(buildGame("Garry's Mod", GameType.Sandbox, GameTier.FIRST));
        games.add(buildGame("Roblox", GameType.Sandbox, GameTier.FIRST));
        games.add(buildGame("Factorio", GameType.Sandbox, GameTier.FIRST));
        games.add(buildGame("Space Engineers", GameType.Sandbox, GameTier.FIRST));
        games.add(buildGame("Kerbal Space Program", GameType.Sandbox, GameTier.FIRST));

        games.add(buildGame("Scrap Mechanic", GameType.Sandbox, GameTier.SECOND));
        games.add(buildGame("Satisfactory", GameType.Sandbox, GameTier.SECOND));
        games.add(buildGame("Valheim", GameType.Sandbox, GameTier.SECOND));
        games.add(buildGame("Ark: Survival Evolved", GameType.Sandbox, GameTier.SECOND));
        games.add(buildGame("Conan Exiles", GameType.Sandbox, GameTier.SECOND));
        games.add(buildGame("7 Days to Die", GameType.Sandbox, GameTier.SECOND));
        games.add(buildGame("Astroneer", GameType.Sandbox, GameTier.SECOND));

        games.add(buildGame("The Forest", GameType.Sandbox, GameTier.THIRD));
        games.add(buildGame("Eco", GameType.Sandbox, GameTier.THIRD));
        games.add(buildGame("Teardown", GameType.Sandbox, GameTier.THIRD));
        games.add(buildGame("Project Zomboid", GameType.Sandbox, GameTier.THIRD));
        games.add(buildGame("Besiege", GameType.Sandbox, GameTier.THIRD));
        games.add(buildGame("Subnautica", GameType.Sandbox, GameTier.THIRD));
        games.add(buildGame("No Man's Sky", GameType.Sandbox, GameTier.THIRD));
        return games;
    }

    private Game buildGame(String name, GameType type, GameTier tier) {
        double priceMultiplier = tier.getPriceMultiplier() + MathUtils.jitter(tier.getRandomRange());
        double marketPrice = MathUtils.roundTwoDecimals(type.getBaseMarketPrice() * priceMultiplier);
        double sellingPrice = MathUtils.roundTwoDecimals(type.getBaseSellingPrice() * priceMultiplier);

        int marketWeight = Math.max(1, type.getBaseMarketWeight() + tier.getMarketWeightDelta() + MathUtils.randomInt(-1, 1));
        int volatility = Math.max(1, type.getBaseVolatility() + tier.getVolatilityDelta() + MathUtils.randomInt(-1, 1));

        return new Game(name, marketPrice, sellingPrice, marketWeight, volatility, type, tier);
    }
}
