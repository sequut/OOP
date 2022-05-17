package sequut.settings;

import java.util.Random;

public class Settings {
    private int rows = 10;
    private int columns = 10;
    private int frameRate = 20;
    private float interval = 2000.0f / frameRate;
    private int foodCount = 8;
    private int cellSize = 100;

    private int width = columns * cellSize;
    private int height = rows * cellSize;

    private final Random random = new Random();
    private int cellsCount = rows * columns;
    private int goal = cellsCount / 2 + (random.nextInt() % (cellsCount / 20));

    private int snakes = 1;

    public Settings(int columns, int rows, int frameRate, int foodCount, int cellSize, int snakes){
        this.columns = columns;
        this.rows = rows;
        this.frameRate = frameRate;
        this.interval = 2000.0f / frameRate;
        this.foodCount = foodCount;
        this.cellSize = cellSize;
        this.snakes = snakes;

        width = columns * cellSize;
        height = rows * cellSize;

        cellsCount = rows * columns;
        try{
            goal = cellsCount / 2 + (random.nextInt() % (cellsCount / 20));
        }
        catch (Exception exc){
            goal = cellsCount / 2 + 1;
        }

    }
    public Settings(){

    }

    public void setFoodCount(int foodCount){
        this.foodCount = foodCount;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public int getFrameRate() {
        return frameRate;
    }
    public float getInterval() {
        return interval;
    }
    public int getFoodCount() {
        return foodCount;
    }
    public int getCellSize() {
        return cellSize;
    }
    public int getGoal(){
        return goal;
    }

    public void updateGoal(){
        try{
            goal = cellsCount / 2 + (random.nextInt() % (cellsCount / 20));
        }
        catch (Exception exc){
            goal = cellsCount / 2 + 1;
        }
    }
}