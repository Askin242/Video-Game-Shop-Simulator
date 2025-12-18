package fr.meow.simulator.core.entity.client;

import java.util.ArrayList;
import java.util.Random;

public class ClientManager {

    private final Random random = new Random();
    private final ArrayList<Client> clients = new ArrayList<>();
    private final ArrayList<ClientListener> listeners = new ArrayList<>();

    public ClientManager() {
    }

    public void spawnClients() {
        Colors randomColor = Colors.values()[random.nextInt(Colors.values().length)];
        Client client = new Client(randomColor.name(), "Client");
        addClient(client);
    }

    public void addClient(Client client) {
        this.clients.add(client);
        notifyListeners(client);
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public void addListener(ClientListener listener) {
        this.listeners.add(listener);
    }

    private void notifyListeners(Client client) {
        for (ClientListener listener : listeners) {
            listener.onClientAdded(client);
        }
    }
}
