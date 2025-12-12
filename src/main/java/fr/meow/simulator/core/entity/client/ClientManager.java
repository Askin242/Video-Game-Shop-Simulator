package fr.meow.simulator.core.entity.client;

import java.util.ArrayList;
import java.util.Random;

public class ClientManager extends Client {

    private final Random random = new Random();
    private final ArrayList<Client> clients = new ArrayList<>();

    public ClientManager(String color, String name) {
        super(color, name);
    }

    public void spawnClients() {
        Colors randomColor = Colors.values()[random.nextInt(Colors.values().length)];
        this.clients.add(new Client(randomColor.name(), "Client"));
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }
}
