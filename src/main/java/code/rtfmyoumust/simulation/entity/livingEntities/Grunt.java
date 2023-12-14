package code.rtfmyoumust.simulation.entity.livingEntities;

public class Grunt extends Creature {
    public void eat(int i, int j, int k) {
        System.out.println("I were hungry! Now i'm gonna eat -" + (i + j + k) + "herbivore!");
    }

    private int attackStrength;

    public Grunt() {
        System.out.println("created Predator (3)");
    }

    @Override
    public void makeMove() {
    }

    @Override
    public int getCoordinates() {
        return 0;
    }

    public void attack() {

    }
}
