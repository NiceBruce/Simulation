package code.rtfmyoumust.simulation.model;

import code.rtfmyoumust.simulation.map.Coordinates;

public abstract class Entity {
    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
