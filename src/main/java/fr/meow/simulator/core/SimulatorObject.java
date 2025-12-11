package fr.meow.simulator.core;

public class SimulatorObject {
    private final String name;

    public SimulatorObject(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void update() {}
    public void newGameDay() {}
    public void endGameDay() {}
}
