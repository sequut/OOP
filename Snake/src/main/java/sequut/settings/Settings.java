package sequut.settings;

import java.util.Random;

public class Settings {
    private Integer rows = 10;
    private Integer columns = 10;
    private Integer frameRate = 20;
    private float interval = 2000.0f / frameRate;
    private Integer foodCount = 8;
    private Integer cellSize = 100;

    private Integer width = columns * cellSize;
    private Integer height = rows * cellSize;

    private final Random random = new Random();
    private Integer cellsCount = rows * columns;
    private Integer goal = cellsCount/2 + (random.nextInt() % (cellsCount / 20));


    public Settings(Integer columns, Integer rows, Integer frameRate, Integer foodCount, Integer cellSize){
        this.columns = columns;
        this.rows = rows;
        this.frameRate = frameRate;
        this.interval = 2000.0f / frameRate;
        this.foodCount = foodCount;
        this.cellSize = cellSize;

        width = columns * cellSize;
        height = rows * cellSize;

        cellsCount = rows * columns;
        goal = cellsCount/2 + (random.nextInt() % (cellsCount / 20));
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
        this.goal = cellsCount/2 + (random.nextInt() % (cellsCount / 20));
    }
}