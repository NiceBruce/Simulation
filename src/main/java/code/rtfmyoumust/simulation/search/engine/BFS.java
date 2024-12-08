package code.rtfmyoumust.simulation.search.engine;

import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.map.World;
import code.rtfmyoumust.simulation.model.Entity;

import java.util.*;

public class BFS {
    private World world;
    private LinkedList<Coordinates> searched;
    private List<Coordinates> passed;
    private Map<Coordinates, List<Coordinates>> parents;
    private Class<?> target;

    private List<Coordinates> getChild(Coordinates currentCoordinate) {
        List<Coordinates> child = new ArrayList<>();
        int[][] shifts = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

        for (int[] shift : shifts) {
            int x = currentCoordinate.getX() + shift[0];
            int y = currentCoordinate.getY() + shift[1];

            if (checkCoordinate(x, y)) {
                child.add(new Coordinates(x, y));
            }
        }

        return child;
    }

    private boolean checkCoordinate(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        if ((x >= 0 && x <= world.getX()) && (y >= 0  && y <= world.getY())) {
            if (world.isPositionEmpty(coordinates)) {
                return true;
            } else {
                return world.getEntity(coordinates).getClass() == target;
            }
        }
        return false;
    }

    private boolean isTarget(Coordinates coordinates) {
        Entity entity = world.getEntity(coordinates);
        if (entity == null) return false;
        return entity.getClass() == target;
    }

    public List<Coordinates> findPathBFS(World world, Coordinates start, Class target) {
        this.world = world;
        this.target = target;
        searched = new LinkedList<>();
        passed = new ArrayList<>();
        parents = new HashMap<>(); //создаем структуру для хранения узла - его чайлды
        searched.add(start); // добавляем текущие координаты узла в очередь проверки
        passed.add(start); // добавляем стартовыую координату в посещенные

        while(!searched.isEmpty()) { // пока очередь не пуста
            Coordinates currentCoordinates = searched.poll(); // достаем первую координату из очереди
            if (isTarget(currentCoordinates)) { // Является ли она целью?
                return new ArrayList<>(getPath(start, currentCoordinates));
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

    private ArrayDeque<Coordinates> getPath(Coordinates start, Coordinates target) {
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
