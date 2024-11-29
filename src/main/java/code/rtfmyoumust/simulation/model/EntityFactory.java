package code.rtfmyoumust.simulation.model;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.model.alive.Herbivore;
import code.rtfmyoumust.simulation.model.alive.Predator;
import code.rtfmyoumust.simulation.model.inanimate.Grass;

public class EntityFactory {

    private final static int HP = 100;
    private final static int CREATURE_SPEED = 1;
    private final static int HERBIVORE_HEALTH_LOSS = 10;
    private final static int PREDATOR_HEALTH_LOSS = 20;
    private final static int PREDATOR_ATTACK_STRENGTH = 40;
    private final static int HERBIVORE_HEALTH_RECOVERY_FROM_EAT = 40;

    public static Entity createEntity(Class<? extends Entity> entityType, Coordinates coordinates) {
        try {
            return switch (entityType.getSimpleName()) {
                case "Herbivore" -> new Herbivore(
                        coordinates, HP, CREATURE_SPEED, Grass.class, HERBIVORE_HEALTH_LOSS,
                        HERBIVORE_HEALTH_RECOVERY_FROM_EAT);
                case "Predator" -> new Predator(
                        coordinates, HP, CREATURE_SPEED, Herbivore.class, PREDATOR_HEALTH_LOSS,
                        PREDATOR_ATTACK_STRENGTH);
                default ->  entityType.getDeclaredConstructor(Coordinates.class).newInstance(coordinates);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
