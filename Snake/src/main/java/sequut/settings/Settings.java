package sequut.settings;

import java.util.Random;

public class Settings {
    private int width = 600;
    private int height = 600;
    private int frameRate = 5;
    private float interval = 2000.0f / frameRate;
    private int foodCount = 10;
    private int cellSize = 100;

    private final Random random = new Random();
    private final int cellsCount = (width/cellSize) * (height/cellSize);
    private int goal = cellsCount/2 + (random.nextInt() % (cellsCount / 20));

    Settings(int width, int height, int frameRate, int interval, int foodCount, int cellSize){
        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
        this.interval = interval;
        this.foodCount = foodCount;
        this.cellSize = cellSize;
    }
    public Settings(){

    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
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