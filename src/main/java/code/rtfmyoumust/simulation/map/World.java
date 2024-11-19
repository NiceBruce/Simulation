package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.EntityFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class World {

    private final int GRID_SIZE_BY_X;
    private final int GRID_SIZE_BY_Y;
    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public World(int gridSizeByX, int gridSizeByY) {
        GRID_SIZE_BY_X = gridSizeByX;
        GRID_SIZE_BY_Y = gridSizeByY;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return this.entities;
    }

    public void setEntity(Coordinates coordinates, Class<? extends Entity> entityType) {

        Entity entity = EntityFactory.createEntity(entityType, coordinates);
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public boolean isPositionEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

//    public void setupEntity() {
//        setEntity(new Coordinates(4, 4), new Predator(new Coordinates(4, 4)));
//        setEntity(new Coordinates(3, 9), new Herbivore(new Coordinates(3, 9)));
//        setEntity(new Coordinates(7, 3), new Grass(new Coordinates(7, 3)));
//        setEntity(new Coordinates(5, 5), new Rock(new Coordinates(5, 5)));
//        setEntity(new Coordinates(1, 1), new Tree(new Coordinates(1, 1)));
//        setEntity(new Coordinates(2, 8), new Tree(new Coordinates(2, 8)));
//    }

    public int getGRID_SIZE_BY_X() {
        return GRID_SIZE_BY_X;
    }

    public int getGRID_SIZE_BY_Y() {
        return GRID_SIZE_BY_Y;
    }

    public void printCountEntity() {
        int herbivore = 0;
        int predator = 0;
        int tree = 0;
        int rock = 0;
        int grass = 0;

        for (Map.Entry<Coordinates, Entity> entity : entities.entrySet()) {
            switch (entity.getValue().getClass().getSimpleName()) {
                case "Herbivore":
                    herbivore += 1;
                    break;
                case "Predator":
                    predator += 1;
                    break;
                case "Grass":
                    grass += 1;
                    break;
                case "Rock":
                    rock += 1;
                    break;
                case "Tree":
                    tree += 1;
                    break;
            }
        }

        System.out.println(String.format("Herbivore: %s Predator: %s Grass: %s Rock: %s Tree: %s",
                herbivore, predator, grass, rock, tree));

    }
}
