package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.EntityFactory;
import code.rtfmyoumust.simulation.model.alive.Creature;
import code.rtfmyoumust.simulation.model.alive.Herbivore;
import code.rtfmyoumust.simulation.model.alive.Predator;
import code.rtfmyoumust.simulation.model.inanimate.Grass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class World {

    private final int x;
    private final int y;
    private final HashMap<Coordinates, Entity> entities = new HashMap<>();

    public World(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public List<Creature> getCreatures() {
        return this.entities.values().stream()
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .collect(Collectors.toList());
    }

    public void setEntity(Coordinates coordinates, Class<? extends Entity> entityType) {
        Entity entity = EntityFactory.createEntity(entityType, coordinates);
        entity.setCoordinates(coordinates);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Map<Class<? extends Entity>, Integer> countEntity() {
        int herbivoreCount = 0;
        int predatorsCount = 0;
        int grassCount = 0;

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
