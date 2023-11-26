package code.rtfmyoumust.simulation.entity;

import java.util.HashMap;

public class Map {
    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void setEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }
}
