import java.util.HashSet;
import java.util.Set;

/**
 * this class storage attribute of coordinates of tile
 */
public class Coordinates {

    private int x;
    private int y;
    private boolean isSolved;


    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    /**
     * collection of coordinates of neighbours for tile.
     * The tile is not own neighbour.
     * The neighbours are all tiles that have the same X - coordinates, Y - coordinates and are in the same big rectangle.
     *
     * @return list of coordinates of neighbours
     */
    public Set<Coordinates> neighbours() {
        Set<Coordinates> listOfNeigbours = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            listOfNeigbours.add(new Coordinates(i, this.getY()));
            listOfNeigbours.add(new Coordinates(this.getX(), i));
            for (int j = 0; j < 9; j++) {
                if (i / 3 == this.getX() / 3 && j / 3 == this.getY() / 3) {
                    listOfNeigbours.add(new Coordinates(i, j));
                }
            }
        }

        listOfNeigbours.remove(new Coordinates(this.getX(), this.getY()));

        return listOfNeigbours;
    }

    /**
     * method checed if two tiles are neighbours
     *
     * @param first
     * @return true if two tiles are neighbours and false otherwise
     */
    public boolean isNeighbour(Coordinates first) {
        if (first.getY() == this.getY()) return true;
        if (first.getX() == this.getX()) return true;
        if (first.getX() / 3 == this.getX() / 3 && first.getX() / 3 == this.getX() / 3) return true;
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
