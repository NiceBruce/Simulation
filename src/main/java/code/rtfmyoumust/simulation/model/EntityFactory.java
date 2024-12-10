package code.rtfmyoumust.simulation.model;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.alive.Herbivore;
import code.rtfmyoumust.simulation.model.alive.Predator;
import code.rtfmyoumust.simulation.model.inanimate.Grass;
import code.rtfmyoumust.simulation.model.inanimate.Rock;
import code.rtfmyoumust.simulation.model.inanimate.Tree;

public class EntityFactory {

    private final static int HP = 100;
    private final static int CREATURE_SPEED = 1;
    private final static int HERBIVORE_HEALTH_LOSS = 10;
    private final static int PREDATOR_HEALTH_LOSS = 20;
    private final static int PREDATOR_ATTACK_STRENGTH = 40;
    private final static int HERBIVORE_HEALTH_RECOVERY_FROM_EAT = 40;

    public static Entity createEntity(Class<? extends Entity> entityType, Coordinates coordinates) {
        return switch (entityType.getSimpleName()) {
            case "Herbivore" -> new Herbivore(
                    coordinates, HP, CREATURE_SPEED, Grass.class, HERBIVORE_HEALTH_LOSS,
                    HERBIVORE_HEALTH_RECOVERY_FROM_EAT);
            case "Predator" -> new Predator(
                    coordinates, HP, CREATURE_SPEED, Herbivore.class, PREDATOR_HEALTH_LOSS,
                    PREDATOR_ATTACK_STRENGTH);
            case "Grass" -> new Grass(coordinates);
            case "Rock" -> new Rock(coordinates);
            case "Tree" -> new Tree(coordinates);
            default -> throw new IllegalArgumentException("Unsupported entity type: " + entityType.getSimpleName());
        };
    }
}
