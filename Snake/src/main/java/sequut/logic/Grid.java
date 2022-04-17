package sequut.logic;

import javafx.scene.paint.Color;
import sequut.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
    public static int SIZE;

    public static final Color COLOR = new Color(0, 0.2, 0.2, 1);

    private final int columns;
    private final int rows;

    private static int foodCount;
    private final Snake snake;
    private final List<Food> food;
    private Settings settings;

    public Grid(Settings settings){
        this.settings = settings;
        Random random = new Random();
        rows = (int)settings.getWidth()/settings.getCellSize();
        columns = (int)settings.getHeight()/settings.getCellSize();
        SIZE = settings.getCellSize();
        foodCount = settings.getFoodCount();

        snake = new Snake(this, new Point(rows/2, columns/2));
        food = new ArrayList<>();
        Food foodAdd;

        for (int i = 0; i < foodCount; i++){
            int number = random.nextInt(0, Food.getNumberOfColors());
            foodAdd = new Food(getRandPoint(), number);
            food.add(foodAdd);
        }
    }

    private Point getRandPoint(){
        Random random = new Random();
        Point point;
        do {
            point = new Point(random.nextInt(rows), random.nextInt(columns));
        } while (snake.getPoints().contains(point) || food.contains(point));

        return point;
    }

    public void update(){
        if (food.stream().anyMatch(food1 -> food1.getPoint().equals(snake.getHead()))){
            snake.extend();
            food.stream().filter(food1 -> food1.getPoint().equals(snake.getHead())).findFirst().get().setPoint(getRandPoint());
        }
        snake.move();
    }

    public int getCols() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public double getWidth() {
        return rows * SIZE;
    }

    public double getHeight() {
        return columns * SIZE;
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFood() {
        return food;
    }
}
