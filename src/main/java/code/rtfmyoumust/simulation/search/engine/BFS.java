package code.rtfmyoumust.simulation.search.engine;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.map.World;
import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.alive.Creature;
import code.rtfmyoumust.simulation.model.alive.Herbivore;
import code.rtfmyoumust.simulation.model.alive.Predator;
import code.rtfmyoumust.simulation.model.inanimate.Grass;
import code.rtfmyoumust.simulation.model.inanimate.Rock;
import code.rtfmyoumust.simulation.model.inanimate.Tree;

import java.util.*;

public class BFS {
    private World world;
    private Creature currentCreature;
    private LinkedList<Coordinates> searched;
    private ArrayList<Coordinates> passed;
    private HashMap<Coordinates, List<Coordinates>> parents;

    public List<Coordinates> getChild(Coordinates currentCoordinate) {
        List<Coordinates> child = new ArrayList<>();

        if (checkCoordinate(currentCoordinate.getX() + 1, currentCoordinate.getY())) {
            child.add(new Coordinates(currentCoordinate.getX() + 1, currentCoordinate.getY()));
        }

        if (checkCoordinate(currentCoordinate.getX(),currentCoordinate.getY() - 1)) {
            child.add(new Coordinates(currentCoordinate.getX(),currentCoordinate.getY() - 1));
        }

        if (checkCoordinate(currentCoordinate.getX() - 1, currentCoordinate.getY())) {
            child.add(new Coordinates( currentCoordinate.getX() - 1, currentCoordinate.getY()));
        }

        if (checkCoordinate(currentCoordinate.getX(), currentCoordinate.getY() + 1)) {
            child.add(new Coordinates(currentCoordinate.getX(), currentCoordinate.getY() + 1));
        }

        return child;
    }

    public boolean checkCoordinate(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        Entity entity = world.getEntity(coordinates);
        if ((x >= 0 && x <= world.getGRID_WIDTH()) && (y >= 0  && y <= world.getGRID_HEIGHT())) {
            if (world.isPositionEmpty(coordinates)) {
                return true;
            } else if (this.currentCreature instanceof Predator) {
                return !(entity instanceof Predator)
                        && !(entity instanceof Tree)
                        && !(entity instanceof Grass)
                        && !(entity instanceof Rock);
            } else if (this.currentCreature instanceof Herbivore) {
                return !(entity instanceof Predator)
                        && !(entity instanceof Tree)
                        && !(entity instanceof Herbivore)
                        && !(entity instanceof Rock);
            }
        }
        return false;
    }

    public boolean isTarget(Coordinates coordinates, Class<?> targetType) {
        Entity entity = world.getEntity(coordinates);
        if (entity == null) return false;
        return entity.getClass() == targetType;
    }

    public List<Coordinates> findPathBFS(World world, Creature creature, Class<?> targetType) {
        this.world = world;
        currentCreature = creature;
        Coordinates startCoordinates = creature.getCoordinates();
        searched = new LinkedList<>();
        passed = new ArrayList<>();
        parents = new HashMap<>(); //создаем структуру для хранения узла - его чайлды

        searched.add(startCoordinates); // добавляем текущие координаты узла в очередь проверки
        passed.add(startCoordinates); // добавляем стартовыую координату в посещенные

        while(!searched.isEmpty()) { // пока очередь не пуста
            Coordinates currentCoordinates = searched.poll(); // достаем первую координату из очереди
            if (isTarget(currentCoordinates, targetType)) { // Является ли она целью?
                return new ArrayList<>(getPath(startCoordinates, currentCoordinates));
            }

            for (Coordinates childNode : getChild(currentCoordinates)) { // пробегаемся по возможным дочерним узлам
                if (!passed.contains(childNode)) { // если текущей координаты НЕТ в посещенных
                    passed.add(childNode);
                    searched.add(childNode);
                    parents.computeIfAbsent(currentCoordinates, v -> new ArrayList<>()).add(childNode);
                }
            }
        }
        return new ArrayList<>();
    }

    public ArrayDeque<Coordinates> getPath(Coordinates start, Coordinates target) {
        ArrayDeque<Coordinates> path = new ArrayDeque<>();
        path.push(target);
        while(target != start) {
            for (Map.Entry<Coordinates, List<Coordinates>> parent : parents.entrySet()) {
                if (parent.getValue().contains(target)) {
                    path.push(parent.getKey());
                    target = parent.getKey();
                }
            }
        }
        return path;
    }
}
