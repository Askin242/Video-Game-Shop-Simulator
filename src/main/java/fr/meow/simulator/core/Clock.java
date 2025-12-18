package fr.meow.simulator.core;

import fr.meow.simulator.core.entity.client.ClientManager;
import java.util.Random;

public class Clock {

    private static int hours = 9;
    private static int minutes = 0;
    
    private static final int MINUTES_PER_TICK = 2;
    private static final double TICKS_PER_HOUR = 60.0 / MINUTES_PER_TICK;
    private static final Random RANDOM = new Random();

    public Clock() {}

    public void loop() {
        new Thread(() -> {
            try {
                while (true) {
                    Clock.tick();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
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
            hours = 9;

            VideoGameSimulator.getInstance().getPlayer().newGameDay();
        }
    }

    private static void handleClientSpawning() {
        double hourlyRate = getRandomizedSpawnRateForHour(hours);

        if (hourlyRate <= 0) {
            return;
        }

        double spawnProbabilityPerTick = hourlyRate / TICKS_PER_HOUR;

        if (RANDOM.nextDouble() < spawnProbabilityPerTick) {
            VideoGameSimulator.getInstance().getClientManager().spawnClient();
        }
    }

    private static double getRandomizedSpawnRateForHour(int hour) {
        double base;

        if (hour >= 10 && hour <= 12) {
            base = 2.0;
        } else if (hour >= 13 && hour <= 17) {
            base = 4.0;
        } else if (hour >= 18 && hour <= 22) {
            base = 1.0;
        } else {
            return 0.0;
        }

        double variation = 0.75 + (RANDOM.nextDouble() * 0.5);
        return base * variation;
    }

    public static String getFormattedTime() {
        return String.format("%02d:%02d", hours, minutes);
    }
}
