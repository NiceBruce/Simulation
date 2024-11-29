package code.rtfmyoumust.simulation.model;

import code.rtfmyoumust.simulation.map.Coordinates;

public abstract class Entity {
    private Coordinates coordinates;

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
