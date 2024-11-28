package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.staticEntities.Grass;

import java.util.List;

public class Herbivore extends Creature {

    private int herbivoreCount = 0;
    private Class<?> targetType = Grass.class;
    private int speed = 1;

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
        this.herbivoreCount = this.getCreatureCount();
    }

    @Override
    public void makeMove(List<Coordinates> path) {
        if (!path.isEmpty()) {
            if (path.size() > 2) this.feelHungry();
            setCoordinates(path.get(path.size() == 1 ? 0 : speed));
        }
    }

    @Override
    public Class<?> getTargetType() {
        return targetType;
    }

    @Override
    public void feelHungry() {
        this.setHp(this.getHp() - 10);
    }

    @Override
    public void getStatistics() {
        System.out.println(String.format("[Herbivore #%s, position: [%s, %s], HP: %s, Status: %s]",
                this.herbivoreCount,
                this.getCoordinates().getX(),
                this.getCoordinates().getY(),
                this.getHp(),
                this.getHp() <= 0 ? "Died" : "Alive and Hungry!"
        ));
    }
}
