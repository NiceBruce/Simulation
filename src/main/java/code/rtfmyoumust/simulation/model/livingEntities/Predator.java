package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.Entity;

import java.util.List;

public class Predator extends Creature {
    private int attackStrength;

    public Predator(Coordinates coordinates, int hp, int speed, Class<?> targetType, int healthLoss, int attackStrength) {
        super(coordinates, hp, speed, targetType, healthLoss);
        this.attackStrength = attackStrength;
    }

    @Override
    public void makeMove(List<Coordinates> path, Entity entity) {
        Herbivore target = (Herbivore) entity;
        if (!path.isEmpty()) {
            if (path.size() == CLOSE_TO_TARGET){
                target.setHp(this.atack(target.getHp()));
                if (target.getHp() <= NO_HEALTH) setCoordinates(path.get(path.size() == 1 ? 0 : speed));
            } else if (path.size() > CLOSE_TO_TARGET){
                this.feelHungry();
                setCoordinates(path.get(path.size() == 1 ? 0 : speed));
            }
        } else {
            this.setHp(this.getHp() - hungerHealthLoss);
        }
    }

    public int atack(int targetHp) {
        targetHp = targetHp - this.attackStrength;
        this.setHp(this.getHp() + ((targetHp >= 0) ? this.attackStrength : targetHp));
        return targetHp;
    }

}
