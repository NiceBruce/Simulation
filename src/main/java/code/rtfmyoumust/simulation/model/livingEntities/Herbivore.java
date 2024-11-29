package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.Entity;

import java.util.List;

public class Herbivore extends Creature {
    private int heathRecoveryFromEating;

    public Herbivore(Coordinates coordinates, int hp, int speed, Class<?> targetType, int healthLoss, int hpRecovery) {
        super(coordinates, hp, speed, targetType, healthLoss);
        this.heathRecoveryFromEating = hpRecovery;
    }

    @Override
    public void makeMove(List<Coordinates> path, Entity entity) {
        if (!path.isEmpty()) {
            if (path.size() == CLOSE_TO_TARGET) this.eat();
            if (path.size() > CLOSE_TO_TARGET) this.feelHungry();
            setCoordinates(path.get(path.size() == 1 ? 0 : speed));
        } else {
            this.setHp(this.getHp() - hungerHealthLoss);
        }
    }

    public void eat() {
        this.setHp(this.getHp() + heathRecoveryFromEating);
    }
}
