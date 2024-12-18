package code.rtfmyoumust.simulation.core;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.map.World;
import code.rtfmyoumust.simulation.map.WorldFactory;
import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.alive.Creature;
import code.rtfmyoumust.simulation.search.engine.BFS;

import java.util.List;

public class Actions {
    private World world;
    private WorldFactory worldFactory = new WorldFactory();
    private BFS bfs = new BFS();
    private static final int WORLD_SIZE_BY_X = 5;
    private static final int WORLD_SIZE_BY_Y = 5;

    public World initActions() {
        this.world = worldFactory.createWorld(WORLD_SIZE_BY_X, WORLD_SIZE_BY_Y);
        return this.world;
    };

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
