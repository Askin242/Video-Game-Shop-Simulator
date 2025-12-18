package fr.meow.simulator.core.entity.client;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Random;

public class ClientManager {

    private final Random random = new Random();
    private final ArrayList<Client> clients = new ArrayList<>();
    private final ArrayList<ClientListener> listeners = new ArrayList<>();

    public ClientManager() {
    }

    public void spawnClient() {
        Colors randomColor = Colors.values()[random.nextInt(Colors.values().length)];
        Client client = new Client(randomColor.name(), "Client");
        addClient(client);

        new Thread(() -> {
            try {
                Thread.sleep(3000L + random.nextInt(5000));
                client.BuyGame();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            try {
                Thread.sleep(3000L + random.nextInt(5000));
                removeClient(client);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void addClient(Client client) {
        this.clients.add(client);
        notifyListeners(client);
    }

    public void removeClient(Client client) {
        this.clients.remove(client);
        notifyListenersRemove(client);
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public void addListener(ClientListener listener) {
        this.listeners.add(listener);
    }

    private void notifyListeners(Client client) {
        // Ensure UI listeners (like GameWindow) are always called on the JavaFX Application Thread.
        Platform.runLater(() -> {
            for (ClientListener listener : listeners) {
                listener.onClientAdded(client);
            }
        });
    }

    private void notifyListenersRemove(Client client) {
        // Ensure UI listeners (like GameWindow) are always called on the JavaFX Application Thread.
        Platform.runLater(() -> {
            for (ClientListener listener : listeners) {
                listener.onClientRemoved(client);
            }
        });
    }
}
