package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;

import java.util.HashMap;

public class World {
    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return this.entities;
    }

    public void setEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }
}
