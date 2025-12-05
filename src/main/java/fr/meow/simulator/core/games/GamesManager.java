package fr.meow.simulator.core.games;

import java.util.ArrayList;

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
        return gameArrayList.stream().filter(g -> g.getName().equals(name)).findFirst().orElse(null);
    }

    public ArrayList<Game> getGamesByTypeAndTier(GameType type, GameTier tier) {
        return (ArrayList<Game>) gameArrayList.stream().filter(g -> g.getGameType().equals(type) && g.getGameTier().equals(tier));
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
        gameArrayList.add(new Game("The Witcher 3", 20, 20, 1, 1, GameType.RPG, GameTier.FIRST));
        gameArrayList.add(new Game("Skyrim", 20, 20, 1, 1, GameType.RPG, GameTier.FIRST));
        gameArrayList.add(new Game("Dragon Age: Origins", 20, 20, 1, 1, GameType.RPG, GameTier.FIRST));
        gameArrayList.add(new Game("Baldur’s Gate 3", 20, 20, 1, 1, GameType.RPG, GameTier.FIRST));
        gameArrayList.add(new Game("Mass Effect 2", 20, 20, 1, 1, GameType.RPG, GameTier.FIRST));
        gameArrayList.add(new Game("Fallout: New Vegas", 20, 20, 1, 1, GameType.RPG, GameTier.FIRST));
        gameArrayList.add(new Game("Divinity: Original Sin 2", 20, 20, 1, 1, GameType.RPG, GameTier.FIRST));

        gameArrayList.add(new Game("Diablo II", 20, 20, 1, 1, GameType.RPG, GameTier.SECOND));
        gameArrayList.add(new Game("Final Fantasy VII", 20, 20, 1, 1, GameType.RPG, GameTier.SECOND));
        gameArrayList.add(new Game("Persona 5", 20, 20, 1, 1, GameType.RPG, GameTier.SECOND));
        gameArrayList.add(new Game("Dark Souls", 20, 20, 1, 1, GameType.RPG, GameTier.SECOND));
        gameArrayList.add(new Game("Elden Ring", 20, 20, 1, 1, GameType.RPG, GameTier.SECOND));
        gameArrayList.add(new Game("Xenoblade Chronicles", 20, 20, 1, 1, GameType.RPG, GameTier.SECOND));
        gameArrayList.add(new Game("Kingdom Come: Deliverance", 20, 20, 1, 1, GameType.RPG, GameTier.SECOND));

        gameArrayList.add(new Game("Path of Exile", 20, 20, 1, 1, GameType.RPG, GameTier.THIRD));
        gameArrayList.add(new Game("Star Wars: KOTOR", 20, 20, 1, 1, GameType.RPG, GameTier.THIRD));
        gameArrayList.add(new Game("Bloodborne", 20, 20, 1, 1, GameType.RPG, GameTier.THIRD));
        gameArrayList.add(new Game("Oblivion", 20, 20, 1, 1, GameType.RPG, GameTier.THIRD));
        gameArrayList.add(new Game("Pokémon HeartGold", 20, 20, 1, 1, GameType.RPG, GameTier.THIRD));
        gameArrayList.add(new Game("Tales of Arise", 20, 20, 1, 1, GameType.RPG, GameTier.THIRD));
        gameArrayList.add(new Game("Monster Hunter: World", 20, 20, 1, 1, GameType.RPG, GameTier.THIRD));
        return gameArrayList;
    }

    public ArrayList<Game> initializeTycoon() {
        ArrayList<Game> gameArrayList = new ArrayList<>();
        gameArrayList.add(new Game("RollerCoaster Tycoon 2", 20, 20, 1, 1, GameType.Tycoon, GameTier.FIRST));
        gameArrayList.add(new Game("Transport Tycoon Deluxe", 20, 20, 1, 1, GameType.Tycoon, GameTier.FIRST));
        gameArrayList.add(new Game("Zoo Tycoon", 20, 20, 1, 1, GameType.Tycoon, GameTier.FIRST));
        gameArrayList.add(new Game("Planet Coaster", 20, 20, 1, 1, GameType.Tycoon, GameTier.FIRST));
        gameArrayList.add(new Game("Game Dev Tycoon", 20, 20, 1, 1, GameType.Tycoon, GameTier.FIRST));
        gameArrayList.add(new Game("Capitalism II", 20, 20, 1, 1, GameType.Tycoon, GameTier.FIRST));
        gameArrayList.add(new Game("Tropico 5", 20, 20, 1, 1, GameType.Tycoon, GameTier.FIRST));

        gameArrayList.add(new Game("Cities: Skylines", 20, 20, 1, 1, GameType.Tycoon, GameTier.SECOND));
        gameArrayList.add(new Game("Prison Architect", 20, 20, 1, 1, GameType.Tycoon, GameTier.SECOND));
        gameArrayList.add(new Game("Railroad Tycoon 3", 20, 20, 1, 1, GameType.Tycoon, GameTier.SECOND));
        gameArrayList.add(new Game("Industry Giant II", 20, 20, 1, 1, GameType.Tycoon, GameTier.SECOND));
        gameArrayList.add(new Game("Anno 1404", 20, 20, 1, 1, GameType.Tycoon, GameTier.SECOND));
        gameArrayList.add(new Game("Planet Zoo", 20, 20, 1, 1, GameType.Tycoon, GameTier.SECOND));
        gameArrayList.add(new Game("SimCity 4", 20, 20, 1, 1, GameType.Tycoon, GameTier.SECOND));

        gameArrayList.add(new Game("Factorio", 20, 20, 1, 1, GameType.Tycoon, GameTier.THIRD));
        gameArrayList.add(new Game("Mashinky", 20, 20, 1, 1, GameType.Tycoon, GameTier.THIRD));
        gameArrayList.add(new Game("Airline Tycoon", 20, 20, 1, 1, GameType.Tycoon, GameTier.THIRD));
        gameArrayList.add(new Game("Port Royale 3", 20, 20, 1, 1, GameType.Tycoon, GameTier.THIRD));
        gameArrayList.add(new Game("Project Highrise", 20, 20, 1, 1, GameType.Tycoon, GameTier.THIRD));
        gameArrayList.add(new Game("Two Point Hospital", 20, 20, 1, 1, GameType.Tycoon, GameTier.THIRD));
        gameArrayList.add(new Game("Constructor HD", 20, 20, 1, 1, GameType.Tycoon, GameTier.THIRD));
        return gameArrayList;
    }

    public ArrayList<Game> initializeSimulator() {
        ArrayList<Game> gameArrayList = new ArrayList<>();
        gameArrayList.add(new Game("Microsoft Flight Simulator", 20, 20, 1, 1, GameType.Simulator, GameTier.FIRST));
        gameArrayList.add(new Game("Farming Simulator 22", 20, 20, 1, 1, GameType.Simulator, GameTier.FIRST));
        gameArrayList.add(new Game("Euro Truck Simulator 2", 20, 20, 1, 1, GameType.Simulator, GameTier.FIRST));
        gameArrayList.add(new Game("American Truck Simulator", 20, 20, 1, 1, GameType.Simulator, GameTier.FIRST));
        gameArrayList.add(new Game("The Sims 4", 20, 20, 1, 1, GameType.Simulator, GameTier.FIRST));
        gameArrayList.add(new Game("Arma 3", 20, 20, 1, 1, GameType.Simulator, GameTier.FIRST));
        gameArrayList.add(new Game("BeamNG.drive", 20, 20, 1, 1, GameType.Simulator, GameTier.FIRST));

        gameArrayList.add(new Game("Kerbal Space Program", 20, 20, 1, 1, GameType.Simulator, GameTier.SECOND));
        gameArrayList.add(new Game("PowerWash Simulator", 20, 20, 1, 1, GameType.Simulator, GameTier.SECOND));
        gameArrayList.add(new Game("Train Simulator Classic", 20, 20, 1, 1, GameType.Simulator, GameTier.SECOND));
        gameArrayList.add(new Game("IL-2 Sturmovik", 20, 20, 1, 1, GameType.Simulator, GameTier.SECOND));
        gameArrayList.add(new Game("Bus Simulator 21", 20, 20, 1, 1, GameType.Simulator, GameTier.SECOND));
        gameArrayList.add(new Game("PC Building Simulator", 20, 20, 1, 1, GameType.Simulator, GameTier.SECOND));
        gameArrayList.add(new Game("House Flipper", 20, 20, 1, 1, GameType.Simulator, GameTier.SECOND));

        gameArrayList.add(new Game("Car Mechanic Simulator 2021", 20, 20, 1, 1, GameType.Simulator, GameTier.THIRD));
        gameArrayList.add(new Game("Football Manager 2024", 20, 20, 1, 1, GameType.Simulator, GameTier.THIRD));
        gameArrayList.add(new Game("Surgeon Simulator", 20, 20, 1, 1, GameType.Simulator, GameTier.THIRD));
        gameArrayList.add(new Game("Cities in Motion 2", 20, 20, 1, 1, GameType.Simulator, GameTier.THIRD));
        gameArrayList.add(new Game("SnowRunner", 20, 20, 1, 1, GameType.Simulator, GameTier.THIRD));
        gameArrayList.add(new Game("World of Warships", 20, 20, 1, 1, GameType.Simulator, GameTier.THIRD));
        gameArrayList.add(new Game("Airport CEO", 20, 20, 1, 1, GameType.Simulator, GameTier.THIRD));
        return gameArrayList;
    }

    public ArrayList<Game> initializeRetro() {
        ArrayList<Game> gameArrayList = new ArrayList<>();
        gameArrayList.add(new Game("Super Mario Bros.", 20, 20, 1, 1, GameType.Retro, GameTier.FIRST));
        gameArrayList.add(new Game("Sonic the Hedgehog 2", 20, 20, 1, 1, GameType.Retro, GameTier.FIRST));
        gameArrayList.add(new Game("Pac-Man", 20, 20, 1, 1, GameType.Retro, GameTier.FIRST));
        gameArrayList.add(new Game("Tetris", 20, 20, 1, 1, GameType.Retro, GameTier.FIRST));
        gameArrayList.add(new Game("Doom (1993)", 20, 20, 1, 1, GameType.Retro, GameTier.FIRST));
        gameArrayList.add(new Game("Chrono Trigger", 20, 20, 1, 1, GameType.Retro, GameTier.FIRST));
        gameArrayList.add(new Game("Street Fighter II", 20, 20, 1, 1, GameType.Retro, GameTier.FIRST));

        gameArrayList
                .add(new Game("Castlevania: Symphony of the Night", 20, 20, 1, 1, GameType.Retro, GameTier.SECOND));
        gameArrayList.add(new Game("Mega Man 2", 20, 20, 1, 1, GameType.Retro, GameTier.SECOND));
        gameArrayList.add(
                new Game("The Legend of Zelda: A Link to the Past", 20, 20, 1, 1, GameType.Retro, GameTier.SECOND));
        gameArrayList.add(new Game("Donkey Kong Country", 20, 20, 1, 1, GameType.Retro, GameTier.SECOND));
        gameArrayList.add(new Game("Secret of Mana", 20, 20, 1, 1, GameType.Retro, GameTier.SECOND));
        gameArrayList.add(new Game("EarthBound", 20, 20, 1, 1, GameType.Retro, GameTier.SECOND));
        gameArrayList.add(new Game("Metroid", 20, 20, 1, 1, GameType.Retro, GameTier.SECOND));

        gameArrayList.add(new Game("GoldenEye 007", 20, 20, 1, 1, GameType.Retro, GameTier.THIRD));
        gameArrayList.add(new Game("Ultima VII", 20, 20, 1, 1, GameType.Retro, GameTier.THIRD));
        gameArrayList.add(new Game("Prince of Persia", 20, 20, 1, 1, GameType.Retro, GameTier.THIRD));
        gameArrayList.add(new Game("Final Fantasy VI", 20, 20, 1, 1, GameType.Retro, GameTier.THIRD));
        gameArrayList.add(new Game("Dragon Quest III", 20, 20, 1, 1, GameType.Retro, GameTier.THIRD));
        gameArrayList.add(new Game("Space Invaders", 20, 20, 1, 1, GameType.Retro, GameTier.THIRD));
        gameArrayList.add(new Game("Mortal Kombat II", 20, 20, 1, 1, GameType.Retro, GameTier.THIRD));
        return gameArrayList;
    }

    public ArrayList<Game> initializeSandbox() {
        ArrayList<Game> gameArrayList = new ArrayList<>();
        gameArrayList.add(new Game("Minecraft", 20, 20, 1, 1, GameType.Sandbox, GameTier.FIRST));
        gameArrayList.add(new Game("Terraria", 20, 20, 1, 1, GameType.Sandbox, GameTier.FIRST));
        gameArrayList.add(new Game("Garry's Mod", 20, 20, 1, 1, GameType.Sandbox, GameTier.FIRST));
        gameArrayList.add(new Game("Roblox", 20, 20, 1, 1, GameType.Sandbox, GameTier.FIRST));
        gameArrayList.add(new Game("Factorio", 20, 20, 1, 1, GameType.Sandbox, GameTier.FIRST));
        gameArrayList.add(new Game("Space Engineers", 20, 20, 1, 1, GameType.Sandbox, GameTier.FIRST));
        gameArrayList.add(new Game("Kerbal Space Program", 20, 20, 1, 1, GameType.Sandbox, GameTier.FIRST));

        gameArrayList.add(new Game("Scrap Mechanic", 20, 20, 1, 1, GameType.Sandbox, GameTier.SECOND));
        gameArrayList.add(new Game("Satisfactory", 20, 20, 1, 1, GameType.Sandbox, GameTier.SECOND));
        gameArrayList.add(new Game("Valheim", 20, 20, 1, 1, GameType.Sandbox, GameTier.SECOND));
        gameArrayList.add(new Game("Ark: Survival Evolved", 20, 20, 1, 1, GameType.Sandbox, GameTier.SECOND));
        gameArrayList.add(new Game("Conan Exiles", 20, 20, 1, 1, GameType.Sandbox, GameTier.SECOND));
        gameArrayList.add(new Game("7 Days to Die", 20, 20, 1, 1, GameType.Sandbox, GameTier.SECOND));
        gameArrayList.add(new Game("Astroneer", 20, 20, 1, 1, GameType.Sandbox, GameTier.SECOND));

        gameArrayList.add(new Game("The Forest", 20, 20, 1, 1, GameType.Sandbox, GameTier.THIRD));
        gameArrayList.add(new Game("Eco", 20, 20, 1, 1, GameType.Sandbox, GameTier.THIRD));
        gameArrayList.add(new Game("Teardown", 20, 20, 1, 1, GameType.Sandbox, GameTier.THIRD));
        gameArrayList.add(new Game("Project Zomboid", 20, 20, 1, 1, GameType.Sandbox, GameTier.THIRD));
        gameArrayList.add(new Game("Besiege", 20, 20, 1, 1, GameType.Sandbox, GameTier.THIRD));
        gameArrayList.add(new Game("Subnautica", 20, 20, 1, 1, GameType.Sandbox, GameTier.THIRD));
        gameArrayList.add(new Game("No Man's Sky", 20, 20, 1, 1, GameType.Sandbox, GameTier.THIRD));
        return gameArrayList;
    }
}
