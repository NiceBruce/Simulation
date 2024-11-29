package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;
import code.rtfmyoumust.simulation.model.alive.Creature;

public class WorldRender {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_EARTH_COLOR = "\u001B[48;2;1;168;1m";
    private static final String ANSI_DAMAGE_HEALT_COLOR = "\u001B[48;2;255;255;0m";
    private static final String ANSI_LOW_HEALTH_COLOR = "\u001B[48;2;255;0;0m";

    public static final void renderMap(World world, int countSimulation) {
        System.out.print("[Simulation iteration# " + countSimulation + "] ");
        world.printCountEntity();

        for (int y = world.getGRID_HEIGHT(); y >= 0; y--) {
            String line = "";
            for (int x = 0; x <= world.getGRID_WIDTH(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (world.isPositionEmpty(coordinates)) {
                    line += ANSI_EARTH_COLOR + "\uD83D\uDFE9" +  " ";
                } else {
                    line += colorEntity(world.getEntity(coordinates));
                }
            }
            line = line.stripTrailing();
            line += ANSI_RESET;
            System.out.println(line);
        }
        System.out.println("Press 'p' to Pause, Press 'r' to Resume, press 'q' to Quit");
    }

    public static final String selectUnicodeForEntity(Entity entity) {

        switch (entity.getClass().getSimpleName()) {
            case "Herbivore":
                return "\uD83D\uDC30";
            case "Predator":
                return "\uD83D\uDC3A";
            case "Grass":
                return "\uD83C\uDF3F";
            case "Rock":
                return "\uD83E\uDEA8";
            case "Tree":
                return "\uD83C\uDF33";
        }

        return "";
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
