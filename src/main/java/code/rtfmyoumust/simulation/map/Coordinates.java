package code.rtfmyoumust.simulation.map;

import java.util.Objects;

public class Coordinates {
    private Integer x;
    private Integer y;

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void setCoordinates(Integer latitude, Integer longitude) {
        this.x = latitude;
        this.y = longitude;
    }

    public int distance(Coordinates targetCoordinates) {
        return (int) Math.sqrt(Math.pow(this.x - targetCoordinates.x, 2) +
                Math.pow(this.y - targetCoordinates.y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
