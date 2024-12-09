package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<Entity> getEntities(Coordinates coordinates) {
        return new ArrayList<>(entities.values());
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
    }

    public void removeCreature(Coordinates coordinates) {
        if (entities.containsKey(coordinates)) entities.remove(coordinates);
        else {
            throw new IllegalArgumentException("coordinate does not contain Entity");
        }
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
}
