package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.Entity;

import java.util.LinkedList;
import java.util.List;

public abstract class Creature extends Entity {

    private static int creatureCount;

    private int speed;
    private int hp;
    private Class<?> targetType;
    public LinkedList<Coordinates> pathToTarget = new LinkedList<>();


    public Creature(Coordinates coordinates) {
        super(coordinates);
        this.creatureCount++;
        this.hp = 100;
    }

    public abstract void makeMove(List<Coordinates> path);

    public Class<?> getTargetType() {
        return this.targetType;
    }

    public int getCreatureCount() {
        return creatureCount;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public abstract void feelHungry();

    public abstract void getStatistics();
}
