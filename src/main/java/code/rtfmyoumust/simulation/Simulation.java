package code.rtfmyoumust.simulation;

import code.rtfmyoumust.simulation.map.World;
import code.rtfmyoumust.simulation.map.RenderMap;

public class Simulation {
    private World worldMap;
    private int moveCounter;
    private Action action;
    private RenderMap renderMap;

    public void nextTurn() {
        action.turnActions();
    }

    public void startSimulation() {
        while(true) {
            nextTurn();
            renderMap.renderMap();
        }
    }

    public void pauseSimulation() {

    }
}
