package fr.meow.simulator.core.entity.client;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientSprite {
    private final Client client;
    public final ImageView view;
    private final double frameWidth;
    private final double frameHeight;
    private final double displayWidth;
    private final double displayHeight;
    public List<Point2D> path;

    public double x;
    public double y;
    public int pathIndex;

    public ClientSprite(Client client, Image image, List<List<Point2D>> basePaths) {
        this.client = client;
        this.frameWidth = 200;
        this.frameHeight = image.getHeight();
        double scale = 0.4;
        this.displayWidth = frameWidth * scale;
        this.displayHeight = frameHeight * scale;
        this.view = new ImageView(image);
        this.view.setViewport(new javafx.geometry.Rectangle2D(0, 0, frameWidth, frameHeight));
        this.view.setScaleX(scale);
        this.view.setScaleY(scale);

        Random random = new Random();
        List<Point2D> base = basePaths.get(random.nextInt(basePaths.size()));
        this.path = new ArrayList<>();
        for (Point2D p : base) {
            double ox = p.getX() + random.nextDouble(-25, 25);
            double oy = p.getY() + random.nextDouble(-15, 15);
            this.path.add(new Point2D(ox, oy));
        }

        this.x = this.path.getFirst().getX();
        this.y = this.path.getFirst().getY();
        this.pathIndex = 1;
        moveTo(x, y);
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
        client.setxPos((int) x);
        client.setyPos((int) y);
        view.setTranslateX(x - displayWidth / 2.0);
        view.setTranslateY(y - displayHeight);
    }

    public void updateDirection(double dx, double dy) {
        int frame;
        if (Math.abs(dx) >= Math.abs(dy)) {
            frame = dx >= 0 ? 0 : 1;
        } else {
            frame = 2;
        }
        view.setViewport(new javafx.geometry.Rectangle2D(frame * frameWidth, 0, frameWidth, frameHeight));
    }

    public Pair<Point2D, Integer> target() {
        if (path.isEmpty()) {
            return new Pair<>(new Point2D(x, y), 0);
        }
        if (pathIndex < 0 || pathIndex >= path.size()) {
            pathIndex = 0;
        }
        Point2D currentTarget = path.get(pathIndex);
        int nextIndex = (pathIndex + 1) % path.size();
        return new Pair<>(currentTarget, nextIndex);
    }
}