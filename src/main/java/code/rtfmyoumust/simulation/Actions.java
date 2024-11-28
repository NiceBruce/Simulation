package code.rtfmyoumust.simulation;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.map.World;
import code.rtfmyoumust.simulation.map.WorldFactory;
import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.livingEntities.Creature;
import code.rtfmyoumust.simulation.model.livingEntities.Herbivore;
import code.rtfmyoumust.simulation.model.livingEntities.Predator;
import code.rtfmyoumust.simulation.search.engine.BFS;

import java.util.List;

public class Actions {

    World world;
    WorldFactory worldFactory = new WorldFactory();
    BFS bfs = new BFS();

    /*
    *
    * Действие, совершаемое над миром.
    * Например - сходить всеми существами.
    * Это действие итерировало бы существ и вызывало каждому makeMove().
    * Каждое действие описывается отдельным классом и совершает операции над картой.
    * Симуляция содержит 2 массива действий:
    *
    * */

    // Действия, совершаемые перед стартом симуляции
    public World initActions() {
        this.world = worldFactory.createWorld(5, 5);
        return this.world;
    };

    // Действия, совершаемые каждый ход.
    public void turnActions() {
        List<Entity> entities = world.getEntities();

        this.world.countEntity().forEach((entity, count) -> {
            if (count <= 1) this.worldFactory.addEntity(world, entity);
        });

        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                Coordinates oldPosition = creature.getCoordinates();

                if (world.getEntity(oldPosition).getClass() != creature.getClass()) {
                    continue;
                }

                if (creature.getHp() <= 0) {
                    world.removeCreature(oldPosition);
                    continue;
                }


                List<Coordinates> path = bfs.getSource(world, creature.getCoordinates(), creature, creature.getTargetType());

                if (path.isEmpty()) creature.setHp(creature.getHp() - 10);

                if (creature.getClass() == Predator.class && path.size() == 2) {
                    Predator predator = (Predator) creature;
                    Herbivore target = (Herbivore) world.getEntity(path.get(path.size() - 1));
                    target.setHp(predator.atack(target.getHp()));
                    predator.setHp(predator.getHp() + 40);
                    if (target.getHp() <= 0) {
                        predator.makeMove(path);
                        world.shiftEntity(oldPosition, creature.getCoordinates(), creature);
                    }
                } else if (creature.getClass() == Herbivore.class && path.size() == 2) {
                    Herbivore herbivore = (Herbivore) creature;
                    herbivore.setHp(herbivore.getHp() + 40);
                    herbivore.makeMove(path);
                    world.shiftEntity(oldPosition, creature.getCoordinates(), creature);
                } else {
                    creature.makeMove(path);
                    world.shiftEntity(oldPosition, creature.getCoordinates(), creature);
                }
//                creature.getStatistics();
            }
        }
    };
}
