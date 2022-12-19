public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof Coordinate coordinate)
            return coordinate.x == x && coordinate.y == y;
        return super.equals(object);
    }
}
