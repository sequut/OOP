import java.util.ArrayList;
import java.util.Random;

public class Field {
    private static Coord size;
    private static ArrayList<Coord> allCoords;
    private static final Random random = new Random();
    public static void setSize(Coord _size){
        size = _size;
        allCoords = new ArrayList<>();
        for (int y = 0; y < size.y; y+=1)
            for (int x = 0; x < size.x; x+=1)
                allCoords.add(new Coord(x, y));
    }
    public static Coord getSize(){
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    public static boolean inField(Coord coord){
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }

    public static Coord getRandomCoord(){
        return new Coord(random.nextInt(size.x), random.nextInt(size.y));
    }

    public static ArrayList<Coord> getCoordsAround(Coord coord){
        Coord around;
        ArrayList<Coord> coords = new ArrayList<>();
        for (int x = coord.x - 1; x <= coord.x + 1; x += 1)
            for (int y = coord.y - 1; y <= coord.y + 1; y += 1)
                if (inField(around = new Coord(x, y)))
                    if (!around.equals(coord))
                        coords.add(around);
        return coords;
    }
}
