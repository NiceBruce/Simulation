package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.EntityFactory;
import code.rtfmyoumust.simulation.model.livingEntities.Herbivore;
import code.rtfmyoumust.simulation.model.livingEntities.Predator;
import code.rtfmyoumust.simulation.model.staticEntities.Grass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {

    private final int GRID_WIDTH;
    private final int GRID_HEIGHT;
    private HashMap<Coordinates, Entity> entities = new HashMap<>();

    public World(int x, int y) {
        GRID_WIDTH = x;
        GRID_HEIGHT = y;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(this.entities.values());
    }

    public void setEntity(Coordinates coordinates, Class<? extends Entity> entityType) {
        Entity entity = EntityFactory.createEntity(entityType, coordinates);
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void shiftEntity(Coordinates oldCoordinate, Coordinates newCoordinate, Entity entity) {
        entities.remove(oldCoordinate);
        entities.put(newCoordinate, entity);
    }

    public void removeCreature(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public boolean isPositionEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public int getGRID_WIDTH() {
        return GRID_WIDTH;
    }

    public int getGRID_HEIGHT() {
        return GRID_HEIGHT;
    }

    public Map<Class<? extends Entity>, Integer> countEntity() {
        Integer herbivoreCount = 0;
        Integer predatorsCount = 0;
        Integer grassCount = 0;

        for (Map.Entry<Coordinates, Entity> entity : entities.entrySet()) {
            switch (entity.getValue().getClass().getSimpleName()) {
                case "Herbivore":
                    herbivoreCount += 1;
                    break;
                case "Predator":
                    predatorsCount += 1;
                    break;
                case "Grass":
                    grassCount += 1;
                    break;
            }
        }

        return Map.of(Herbivore.class, herbivoreCount,
                Predator.class, predatorsCount,
                Grass.class, grassCount);
    }

    public void printCountEntity() {
        Map<Class<? extends Entity>, Integer> entityCount = countEntity();
        System.out.println(String.format("Herbivore: %s Predator: %s Grass: %s",
                entityCount.get(Herbivore.class),
                entityCount.get(Predator.class),
                entityCount.get(Grass.class)));
    }
}
