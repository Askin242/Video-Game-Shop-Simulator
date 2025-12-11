package fr.meow.simulator.core.entity.client;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Client {
    private String color;
    private String name;
    private final ArrayList<String> basket;
    private ArrayList<String> LookingFor;

    Client(String color, String name) {
        this.color = color;
        this.name = name;
        this.basket = new ArrayList<String>();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLookingFor() {
        return LookingFor;
    }

    public void setLookingFor(ArrayList<String> lookingFor) {
        LookingFor = lookingFor;
    }

    public ArrayList<String> getBasket() {
        return basket;
    }


}
