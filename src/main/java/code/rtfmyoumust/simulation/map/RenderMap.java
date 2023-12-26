package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RenderMap {

    private World map;
    private final int GRID_SIZE_IN_CELLS;
    private final int CELL_SIZE;

    public RenderMap(int mapSize, int cellSize, World map) {
        this.map = map;
        this.GRID_SIZE_IN_CELLS = mapSize;
        this.CELL_SIZE = cellSize;
    }

    public Parent renderMap() {
        GridPane root = new GridPane();
        root.setPrefSize(GRID_SIZE_IN_CELLS * CELL_SIZE, GRID_SIZE_IN_CELLS * CELL_SIZE);

        for (int y = 0; y < GRID_SIZE_IN_CELLS; y++) {
            for (int x = 0; x < GRID_SIZE_IN_CELLS; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                Cell cell = new Cell(x, y, renderEntity(this.map.getEntity(coordinates)));
                root.getChildren().add(cell);
            }
        }

        return root;
    }

    private class Cell extends StackPane {

        private Rectangle bg;
        private Label entityName;
        Cell(int x, int y, String name) {
            setTranslateX(x * CELL_SIZE);
            setTranslateY(y * CELL_SIZE);
            bg = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GOLD);
            bg.setStroke(Color.WHITE);
            entityName = new Label(name);
            getChildren().addAll(bg, entityName);
        }
    }

    public String renderEntity(Entity entity) {
        if (entity == null) return "";
        System.out.println(entity.getClass().getSimpleName());
        switch (entity.getClass().getSimpleName()) {
            case "Footman":
                return "F";
            case "Grunt":
                return "G";
        }

        return "";
    }
}
