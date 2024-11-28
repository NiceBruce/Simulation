package code.rtfmyoumust.simulation;

import code.rtfmyoumust.simulation.map.World;

import java.io.IOException;
import java.util.Scanner;

import static code.rtfmyoumust.simulation.map.WorldRender.*;

public class Simulation {

    private World world;
    private Integer countMove = 0;
    private Actions actions = new Actions();
    private boolean pauseSimulation = false;

    private static final int LOOP_TIME_DELAY_MS = 2000;

    public void nextTurn() {
        actions.turnActions();
        renderMap(this.world, countMove);

    }

    public void startSimulation() throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(LOOP_TIME_DELAY_MS);
        this.world = this.actions.initActions();
        renderMap(this.world, countMove);

        while (true) {
            if (!pauseSimulation) {
                this.countMove++;
                nextTurn();
                Thread.sleep(LOOP_TIME_DELAY_MS);
            }

            if (System.in.available() > 0) {
                String userInput = scanner.nextLine();
                if ("q".equalsIgnoreCase(userInput)) break;
                pauseSimulation(userInput);
            }
        }
    }

    public void pauseSimulation(String userInput) {
        switch (userInput) {
            case "p": {
                this.pauseSimulation = true;
                break;
            }
            case "r": {
                this.pauseSimulation = false;
                break;
            }
        }
    }
}
