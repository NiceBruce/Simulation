package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.model.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;
    public abstract void makeMove();
}
