package code.rtfmyoumust.simulation;

import code.rtfmyoumust.simulation.core.Simulation;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }
}