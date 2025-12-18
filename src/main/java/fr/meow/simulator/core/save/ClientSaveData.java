package fr.meow.simulator.core.save;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientSaveData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String color;
    private final String name;
    private final double x;
    private final double y;
    private final int pathIndex;
    private final List<Point2DSaveData> path;

    public ClientSaveData(String color, String name, double x, double y, int pathIndex, List<Point2DSaveData> path) {
        this.color = color;
        this.name = name;
        this.x = x;
        this.y = y;
        this.pathIndex = pathIndex;
        this.path = new ArrayList<>(path);
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getPathIndex() {
        return pathIndex;
    }

    public List<Point2DSaveData> getPath() {
        return path;
    }
}
