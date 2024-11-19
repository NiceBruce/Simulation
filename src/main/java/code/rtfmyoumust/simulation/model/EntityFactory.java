package code.rtfmyoumust.simulation.model;

import code.rtfmyoumust.simulation.map.Coordinates;

public class EntityFactory {
    public static Entity createEntity(Class<? extends Entity> entityType, Coordinates coordinates) {
        try {
            return entityType.getDeclaredConstructor(Coordinates.class).newInstance(coordinates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
