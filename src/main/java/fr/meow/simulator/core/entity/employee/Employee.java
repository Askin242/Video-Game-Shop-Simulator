package fr.meow.simulator.core.entity.employee;

public class Employee {
    private String color;
    private String name;
    private String type;

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    Employee(String color, String name, String type) {
        this.color = color;
        this.name = name;
        this.type = type;
    }

    public void DoTask() {}

}
