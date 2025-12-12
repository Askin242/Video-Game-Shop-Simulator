package fr.meow.simulator.core.entity.employee;

import fr.meow.simulator.core.SimulatorObject;

public class Employee extends SimulatorObject {

    private final String color;
    private final String type;

    public Employee(String color, String name, String type) {
        super(name);
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public void DoTask() {}
}
