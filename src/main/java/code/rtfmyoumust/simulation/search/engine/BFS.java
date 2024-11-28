package code.rtfmyoumust.simulation.search.engine;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.map.World;
import code.rtfmyoumust.simulation.model.livingEntities.Creature;
import code.rtfmyoumust.simulation.model.livingEntities.Herbivore;
import code.rtfmyoumust.simulation.model.livingEntities.Predator;
import code.rtfmyoumust.simulation.model.staticEntities.Grass;
import code.rtfmyoumust.simulation.model.staticEntities.Rock;
import code.rtfmyoumust.simulation.model.staticEntities.Tree;

import java.util.*;

public class BFS {
    World world;

    Creature currentCreature;

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
        if ((x >= 0 && x <= world.getGRID_WIDTH()) && (y >= 0  && y <= world.getGRID_HEIGHT())) {
            if (world.isPositionEmpty(new Coordinates(x, y))) {
                return true;
            } else if (this.currentCreature.getClass() == Predator.class) {
                return (world.getEntity(new Coordinates(x, y)).getClass() != Predator.class)
                        && (world.getEntity(new Coordinates(x, y)).getClass() != Tree.class)
                        && (world.getEntity(new Coordinates(x, y)).getClass() != Grass.class)
                        && (world.getEntity(new Coordinates(x, y)).getClass() != Rock.class);
            } else if (this.currentCreature.getClass() == Herbivore.class) {
                return (world.getEntity(new Coordinates(x, y)).getClass() != Predator.class)
                        && (world.getEntity(new Coordinates(x, y)).getClass() != Tree.class)
                        && (world.getEntity(new Coordinates(x, y)).getClass() != Herbivore.class)
                        && (world.getEntity(new Coordinates(x, y)).getClass() != Rock.class);
            }
        }
        return false;
    }

    public boolean isTarget(Coordinates coordinates, Class<?> targetType) {
        if (world.getEntity(coordinates) == null) return false;
        return world.getEntity(coordinates).getClass() == targetType;
    }

    public List<Coordinates> getSource(World world, Coordinates startCoordinates, Creature cr, Class<?> targetType) {
        this.world = world;
        this.currentCreature = cr;
        LinkedList<Coordinates> searched = new LinkedList<>();
        ArrayList<Coordinates> passed = new ArrayList<>();
        HashMap<Coordinates, List<Coordinates>> parents = new HashMap<>(); //создаем структуру для хранения узла - его чайлды
        searched.add(startCoordinates); // добавляем текущие координаты узла в очередь проверки
        passed.add(startCoordinates); // добавляем стартовыую координату в посещенные

        while(!searched.isEmpty()) { // пока очередь не пуста
            Coordinates currentCoordinates = searched.poll(); // достаем первую координату из очереди
            if (isTarget(currentCoordinates, targetType)) { // Является ли она целью?
                return new ArrayList<>(getPath(parents, startCoordinates, currentCoordinates)); // Возвращаем структуру узел -чайлд в функцию поиска обратного пути
            }

            for (Coordinates childNode : getChild(currentCoordinates)) {
                if (!passed.contains(childNode)) { // если текущей координаты НЕТ в посещенных
                    passed.add(childNode);
                    searched.add(childNode);
                    parents.computeIfAbsent(currentCoordinates, v -> new ArrayList<>()).add(childNode);
                }
            }

        }

        return new ArrayList<>();
    }

    public ArrayDeque<Coordinates> getPath(Map<Coordinates, List<Coordinates>> parents , Coordinates start, Coordinates target) {
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
