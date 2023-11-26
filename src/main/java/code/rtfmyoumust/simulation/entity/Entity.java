package code.rtfmyoumust.simulation.entity;

public abstract class Entity {
    public Coordinates coordinates;
    public final boolean isEdible;

    protected Entity(boolean isEdible) {
        this.isEdible = isEdible;
    }

    public abstract int getCoordinates();

}
