package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.alive.Creature;

public class WorldRender {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_EARTH_COLOR = "\u001B[48;2;1;168;1m";
    private static final String ANSI_DAMAGE_HEALT_COLOR = "\u001B[48;2;255;255;0m";
    private static final String ANSI_LOW_HEALTH_COLOR = "\u001B[48;2;255;0;0m";

    public static void renderMap(World world, int countSimulation) {
        System.out.print("[Simulation iteration# " + countSimulation + "] ");
        world.printCountEntity();

        for (int y = world.getY(); y >= 0; y--) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x <= world.getX(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (world.isPositionEmpty(coordinates)) {
                    line.append(ANSI_EARTH_COLOR + "\uD83D\uDFE9" + " ");
                } else {
                    line.append(colorEntity(world.getEntity(coordinates)));
                }
            }
            line = new StringBuilder(line.toString().stripTrailing());
            line.append(ANSI_RESET);
            System.out.println(line);
        }
        System.out.println("Press 'p' to Pause, Press 'r' to Resume, press 'q' to Quit");
    }

    public static String selectUnicodeForEntity(Entity entity) {

        return switch (entity.getClass().getSimpleName()) {
            case "Herbivore" -> "\uD83D\uDC30";
            case "Predator" -> "\uD83D\uDC3A";
            case "Grass" -> "\uD83C\uDF3F";
            case "Rock" -> "\uD83E\uDEA8";
            case "Tree" -> "\uD83C\uDF33";
            default ->
                    throw new IllegalArgumentException("Неизвестный тип объекта: " + entity.getClass().getSimpleName());
        };
    }

    public static String colorEntity(Entity entity) {
        String line;
        if (entity instanceof Creature creature) {
            line = colorCreatureHealth(creature);
        } else {
            line = ANSI_EARTH_COLOR + selectUnicodeForEntity(entity) + " ";
        }
        return line;
    }

    public static String colorCreatureHealth(Creature creature) {
        String line;
        if (creature.getHp() < 100 && creature.getHp() > 50) {
            line = ANSI_DAMAGE_HEALT_COLOR;
        } else if (creature.getHp() <= 50 ) {
            line = ANSI_LOW_HEALTH_COLOR;
        } else {
            line = ANSI_EARTH_COLOR;
        }

        return line + selectUnicodeForEntity(creature) + " ";
    }
}
