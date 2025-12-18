package fr.meow.simulator.core;

import fr.meow.simulator.core.entity.client.ClientManager;

public class Clock {

    private static int day;
    private static int hours;
    private static int minutes;
    
    private static final int MINUTES_PER_TICK = 2;

    public Clock(ClientManager clientManager) {
        day = 0;
        hours = 0;
        minutes = 0;
        Clock.clientManager = clientManager;
    }

    public static void tick() {
        advanceTime();
        handleClientSpawning();
    }

    private static void advanceTime() {
        minutes += MINUTES_PER_TICK;

        if (minutes >= 60) {
            minutes -= 60;
            hours++;
        }

        if (hours >= 24) {
            hours = 0;
            day++;

            VideoGameSimulator.getInstance().getPlayer().newGameDay();
        }
    }

    private static void handleClientSpawning() {
        int amount = getSpawnAmountForHour(hours);

        if (amount > 0) {
            clientManager.spawnClient();
        }
    }

    private static int getSpawnAmountForHour(int hour) {

        if (hour >= 10 && hour < 12) {
            return 2;
        }

        if (hour >= 13 && hour < 17) {
            return 4;
        }

        if (hour >= 18 && hour < 22) {
            return 1;
        }

        return 0;
    }

    public int getDay() {
        return day;
    }

    public String getTime() {
        return String.format("%02d:%02d", hours, minutes);
    }
}
