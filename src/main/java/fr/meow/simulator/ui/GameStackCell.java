package fr.meow.simulator.ui;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

public class GameStackCell extends ListCell<GameStack> {

    @Override
    protected void updateItem(GameStack item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            StringBuilder sb = new StringBuilder(item.game.getName());
            if (item.inventoryCount > 0) {
                sb.append("  [Inv: ").append(item.inventoryCount).append("]");
            }
            if (item.shelfCount > 0) {
                sb.append("  [Shelf: ").append(item.shelfCount).append("]");
            }
            sb.append("  (M: $").append(String.format("%.2f", item.game.getMarketPrice()))
              .append(" / S: $").append(String.format("%.2f", item.game.getSellingPrice())).append(")");
            setText(sb.toString());
            setTextFill(Color.web("#f5f5f5"));
        }
    }
}

