package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.Entity;

import java.util.LinkedList;
import java.util.List;

public abstract class Creature extends Entity {
    private static int creatureCount;
    private int creatureNumber;
    protected int speed;
    protected int hp;
    protected Class<?> targetType;
    protected int hungerHealthLoss;
    protected static final int CLOSE_TO_TARGET = 2;
    protected static final int NO_HEALTH = 0;

    public Creature(Coordinates coordinates, int hp, int speed, Class<?> targetType, int healthLoss) {
        super(coordinates);
        this.hp = hp;
        this.speed = speed;
        this.targetType = targetType;
        this.hungerHealthLoss = healthLoss;
        creatureCount++;
        this.creatureNumber = creatureCount;
    }

    public abstract void makeMove(List<Coordinates> path, Entity entity);

    public Class<?> getTargetType() {
        return this.targetType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void feelHungry() {
        this.setHp(this.getHp() - this.hungerHealthLoss);
    }

    public int getCreatureNumber() {
        return creatureNumber;
    }

    public void getStatistics() {
        System.out.println(String.format("[%s #%s, position: [%s, %s], HP: %s, Status: %s]",
                this.getClass().getSimpleName(),
                this.getCreatureNumber(),
                this.getCoordinates().getX(),
                this.getCoordinates().getY(),
                this.getHp(),
                this.getHp() <= 0 ? "Died" : "Alive and Hungry!"
        ));
    };
}
