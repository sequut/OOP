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

    private static int currentFood;

    private final Snake snake;
    private List<Snake> snakes;
    private final List<Food> food;

    private final Settings settings;

    public Grid(Settings settings){
        this.settings = settings;

        Random random = new Random();
        rows = settings.getRows();
        columns = settings.getColumns();

        SIZE = settings.getCellSize();
        int foodCount = settings.getFoodCount();

        snake = new Snake(this, new Point((columns/2), rows/2));
        food = new ArrayList<>();
        Food foodAdd;
        currentFood = 0;

        for (int i = 0; i < foodCount; i++){
            if (currentFood + snake.getLength() >= columns * rows)
                break;

            int number = 4;//random.nextInt(0, Food.getNumberOfColors());
            foodAdd = new Food(getRandPoint(), number);
            food.add(foodAdd);
            currentFood += 1;
        }
    }

    private Point getRandPoint(){
        Random random = new Random();
        Point generatedPoint;
        int status = 0;

        do {
            generatedPoint = new Point(random.nextInt(columns), random.nextInt(rows));

            for (Food check: food) {
                if (check.getPoint().equals(generatedPoint)){
                    status = 1;
                    break;
                }
                status = 0;
            }

        } while (snake.getPoints().contains(generatedPoint) || status == 1);

        return generatedPoint;
    }

    public void update(){
        if (food.stream().anyMatch(food1 -> food1.getPoint().equals(snake.getHead()))){
            Food thisOne =  food.stream().filter(food1 -> food1.getPoint().equals(snake.getHead())).findFirst().get();
            snake.extend();

            if (snake.getLength() + currentFood - 1> rows * columns){
                food.remove(thisOne);
                currentFood -= 1;
            }
            else
                food.stream().filter(food1 -> food1.getPoint().equals(snake.getHead())).findFirst().get().setPoint(getRandPoint());
        }
        snake.move();
    }

    public int getCols() {
        return settings.getColumns();
    }

    public int getRows() {
        return settings.getRows();
    }

    public double getWidth() {
        return settings.getWidth();
    }

    public double getHeight() {
        return settings.getHeight();
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFood() {
        return food;
    }
}
