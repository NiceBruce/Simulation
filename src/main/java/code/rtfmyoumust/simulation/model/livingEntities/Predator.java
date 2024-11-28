package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;

import java.util.List;

public class Predator extends Creature {

    private int predatorCount = 0;
    private int attackStrength = 0;
    private int speed = 1;
    private Class<?> targetType = Herbivore.class;

    public Predator(Coordinates coordinates) {
        super(coordinates);
        this.predatorCount = this.getCreatureCount();
        this.attackStrength = 40;
    }

    @Override
    public void makeMove(List<Coordinates> path) {
        if (!path.isEmpty()) {
            if (path.size() > 2) this.feelHungry();
            setCoordinates(path.get(path.size() == 1 ? 0 : speed));
        }
    }

    public int atack(int targetHp) {
        targetHp = targetHp - this.attackStrength;
        return targetHp;
    }

    @Override
    public Class<?> getTargetType() {
        return targetType;
    }

    @Override
    public void feelHungry() {
        this.setHp(this.getHp() - 20);
    }

    @Override
    public void getStatistics() {
        System.out.println(String.format("[Predator #%s, position: [%s, %s], HP: %s, Status: %s]",
                this.predatorCount,
                this.getCoordinates().getX(),
                this.getCoordinates().getY(),
                this.getHp(),
                this.getHp() <= 0 ? "Died" : "Alive and Hungry!"
                ));
    }
}
