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
    private World world;
    private WorldFactory worldFactory = new WorldFactory();
    private BFS bfs = new BFS();
    private static final int WORLD_SIZE_BY_X = 5;
    private static final int WORLD_SIZE_BY_Y = 5;

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
        this.world = worldFactory.createWorld(WORLD_SIZE_BY_X, WORLD_SIZE_BY_Y);
        return this.world;
    };

    // Действия, совершаемые каждый ход.
    public void turnActions() {
        List<Creature> creatures = world.getCreatures();
        addEntities();
        makeMoveAllCreatures(creatures);
    }

    public void addEntities(){
        this.world.countEntity().forEach((entity, count) -> {
            if (count <= 1) this.worldFactory.addEntity(world, entity);
        });
    }

    public void makeMoveAllCreatures(List<Creature> creatures) {
        for (Creature creature : creatures) {
            Coordinates oldPosition = creature.getCoordinates();

            if (world.getEntity(oldPosition).getClass() != creature.getClass()) {
                continue;
            }

            if (creature.getHp() <= 0) {
                world.removeCreature(oldPosition);
                continue;
            }

            List<Coordinates> pathToTarget = bfs.findPathBFS(world, creature, creature.getTargetType());
            Entity entity = (!pathToTarget.isEmpty()) ? world.getEntity(pathToTarget.get(pathToTarget.size() - 1)) : null;
            creature.makeMove(pathToTarget, entity);
            world.shiftEntity(oldPosition, creature.getCoordinates(), creature);

            creature.getStatistics();
        }
    }
}
