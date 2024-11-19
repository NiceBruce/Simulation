package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.Entity;

public abstract class Creature extends Entity {

    private int speed;
    private int hp;

    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    public abstract Coordinates makeMove();
}
