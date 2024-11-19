package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    // движение в сторону травы
    @Override
    public Coordinates makeMove() {
        return this.coordinates;
    }

}
