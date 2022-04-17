package sequut.settings;

public class Settings {
    private int width = 1000;
    private int height = 1000;
    private int frameRate = 10;
    private float interval = 2000.0f / frameRate;
    private int foodCount = 1;
    private int cellSize = 100;

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
}