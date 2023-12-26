package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;

public class Grunt extends Creature {


    public Grunt(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Coordinates makeMove() {
        return this.coordinates;
    }
}
