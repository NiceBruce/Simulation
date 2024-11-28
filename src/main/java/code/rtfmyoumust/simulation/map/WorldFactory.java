package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.livingEntities.Herbivore;
import code.rtfmyoumust.simulation.model.livingEntities.Predator;
import code.rtfmyoumust.simulation.model.staticEntities.Grass;
import code.rtfmyoumust.simulation.model.staticEntities.Rock;
import code.rtfmyoumust.simulation.model.staticEntities.Tree;

import java.util.Random;

public class WorldFactory {
    private static final double DISTRIBUTION_COEFF_OF_CREATURE = 0.15;
    private Random random = new Random();
    private World world;
    private int entityPercent;

    private Coordinates generateRandomEmptyCoordinates(int x, int y) {
        Coordinates coordinates = null;

        while(coordinates == null) {
            coordinates = new Coordinates(random.nextInt(x + 1), random.nextInt(y + 1));
            if (world.isPositionEmpty(coordinates)) {
                return coordinates;
            } else {
                coordinates = null;
            }
        }

        return coordinates;
    }


    public World createWorld(int sizeByX, int sizeByY) {
        this.world = new World(sizeByX, sizeByY);
        this.entityPercent = (int) (sizeByX * sizeByY * DISTRIBUTION_COEFF_OF_CREATURE);

        for (int i = 0; i <= entityPercent; i++) {
            world.setEntity(generateRandomEmptyCoordinates(sizeByX, sizeByY), Herbivore.class);
            world.setEntity(generateRandomEmptyCoordinates(sizeByX, sizeByY), Predator.class);
            world.setEntity(generateRandomEmptyCoordinates(sizeByX, sizeByY), Grass.class);

            if (i <= entityPercent/2) {
                world.setEntity(generateRandomEmptyCoordinates(sizeByX, sizeByY), Rock.class);
                world.setEntity(generateRandomEmptyCoordinates(sizeByX, sizeByY), Tree.class);
            }
        }

        return world;
    }

    public World addEntity(World world, Class<? extends Entity> entity) {
        this.world = world;
        for (int i = 0; i <= this.entityPercent; i++) {
            world.setEntity(generateRandomEmptyCoordinates(world.getGRID_WIDTH(), world.getGRID_HEIGHT()), entity);
        }
        return this.world;
    }
}
