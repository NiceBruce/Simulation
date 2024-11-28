package code.rtfmyoumust.simulation.model;

import code.rtfmyoumust.simulation.map.Coordinates;

public abstract class Entity {

    public Coordinates coordinates;

    public Entity() {
    }
    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates()  {
        return this.coordinates;
    }
}
