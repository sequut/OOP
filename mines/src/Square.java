import java.util.ArrayList;
import java.util.Random;

public class Square {
    private static final Random random = new Random();
    private static Coordinate rate;
    private static ArrayList<Coordinate> coordinateArrayList;

    public static void setRate(Coordinate _rate){
        rate = _rate;
        coordinateArrayList = new ArrayList<>();
        for (int y = 0; y < rate.getY(); y++)
            for (int x = 0; x < rate.getX(); x++)
                coordinateArrayList.add(new Coordinate(x, y));
    }

    public static ArrayList<Coordinate> getCoordinateArrayList(){
        return coordinateArrayList;
    }

    public static Coordinate getRate() {
        return rate;
    }

    public static Coordinate randomCoordinate(){
        return new Coordinate(random.nextInt(rate.getX()),
                random.nextInt(rate.getY()));
    }

    public static ArrayList<Coordinate> coordinatesAround(Coordinate coordinate){
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate help;
        int coordinateX = coordinate.getX();
        int coordinateY = coordinate.getY();
        for (int x = coordinateX - 1; x <= coordinateX + 1; x++)
            for (int y = coordinateY - 1; y <= coordinateY + 1; y++)
                if (inSquare(help = new Coordinate(x, y)))
                    if (!help.equals(coordinate)){
                        coordinates.add(help);
                    }
        return coordinates;
    }

    public static boolean inSquare(Coordinate coordinate){
        return coordinate.getX() >= 0 && coordinate.getX() < rate.getX() &&
                coordinate.getY() >=0 && coordinate.getY() < rate.getY();
    }
}
