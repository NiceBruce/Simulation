package code.rtfmyoumust.simulation.map;

import code.rtfmyoumust.simulation.model.Entity;

public class WorldRender {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_EARTH_COLOR = "\u001B[48;2;1;168;1m";
    private World world;

    public WorldRender(World map) {
        this.world = map;
    }

    public void renderMap() {
        world.printCountEntity();
        for (int y = world.getGRID_SIZE_BY_Y(); y >= 0; y--) {
            String line = "";
            for (int x = 0; x <= world.getGRID_SIZE_BY_X(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (world.isPositionEmpty(coordinates)) {
                    line += ANSI_EARTH_COLOR + "\uD83D\uDFE9" +  " ";
                } else {
                    line += ANSI_EARTH_COLOR + selectUnicodeForEntity(world.getEntity(coordinates)) + " ";
                }
            }
            line = line.stripTrailing();
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    public String selectUnicodeForEntity(Entity entity) {

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
}
