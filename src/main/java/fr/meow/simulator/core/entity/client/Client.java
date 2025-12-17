package fr.meow.simulator.core.entity.client;

import fr.meow.simulator.core.SimulatorObject;
import fr.meow.simulator.core.games.Game;

import java.util.ArrayList;

public class Client extends SimulatorObject {

    private final String color;
    private final ArrayList<Game> basket;
    private ArrayList<Game> lookingFor;

    private int xPos;
    private int yPos;

    public Client(String color, String name) {
        super(name);
        this.color = color;
        this.basket = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Game> getLookingFor() {
        return lookingFor;
    }

    public void addLookingFor(Game object) {
        this.lookingFor.add(object);
    }

    public void removeLookingFor(Game object) {
        this.lookingFor.remove(object);
    }

    public ArrayList<Game> getBasket() {
        return basket;
    }

    public void removeBasket(Game object) {
        this.basket.remove(object);
    }

    public void addBasket(Game object) {
        this.basket.add(object);
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
}
