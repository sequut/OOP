package sequut.logic;

public class Point {
    private final int x;
    private final int y;

    Point(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Point deltaPoint(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof Point point))
            return false;
        return point.x == x && point.y == y;
    }

    public String toString() {
        return x + ", " + y;
    }
}
