package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;

public class Footman extends Creature{


    public Footman(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Coordinates makeMove() {
        return this.coordinates;
    }

}
