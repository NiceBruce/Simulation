package code.rtfmyoumust.simulation.model.livingEntities;

import code.rtfmyoumust.simulation.map.Coordinates;

public class Predator extends Creature {

    private int attackStrength;

    public Predator(Coordinates coordinates) {
        super(coordinates);
    }


    // переметиться к жертве-травоядному
    // Атаковать травоядное, его НР уменьшится на силу атаки хищника. Если НР травоядного = 0, он исчезает
    @Override
    public Coordinates makeMove() {
        return this.coordinates;
    }
}
