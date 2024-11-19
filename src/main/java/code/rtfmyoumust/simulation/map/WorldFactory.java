package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.livingEntities.Herbivore;
import code.rtfmyoumust.simulation.model.livingEntities.Predator;
import code.rtfmyoumust.simulation.model.staticEntities.Grass;
import code.rtfmyoumust.simulation.model.staticEntities.Rock;
import code.rtfmyoumust.simulation.model.staticEntities.Tree;

import java.util.Random;

public class WorldFactory {

    // задача расположить сущности в указанном диапазоне по х и у мирке
    // при этом расставлять нужно только на пустые клетки
    // генерация рандомного количества каждой сущности
    // генерация рандомной координаты для каждой сущности

    // распределение такое: 40% клетов должны быть свободными - 60% клеток под сущности
    // тоесть на полученных значениях Х и У должны быть посчитаны 40% и 60%
    // деревья и камни 15/60 - деревья 8 и камни 7
    // растения 15/60
    // травоядные 15/60
    // хищники 15/60

    static final double DISTRIBUTION_COEFF_OF_CREATURE = 0.15;
    private Random random = new Random();
    private World world;

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
        world = new World(sizeByX, sizeByY);
        int entityPercent = (int) (sizeByX * sizeByY * DISTRIBUTION_COEFF_OF_CREATURE);

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
}
