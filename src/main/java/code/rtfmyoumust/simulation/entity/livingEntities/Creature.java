package code.rtfmyoumust.simulation.entity.livingEntities;

import code.rtfmyoumust.simulation.entity.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;

    public Creature() {
        super(true);
    }

    public abstract void makeMove();
}
