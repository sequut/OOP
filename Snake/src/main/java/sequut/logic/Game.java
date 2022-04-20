package sequut.logic;
import javafx.scene.canvas.GraphicsContext;
import sequut.gui.Painter;
import sequut.settings.Settings;

public class Game implements Runnable{
    private final Grid grid;
    private final GraphicsContext context;
    private int frameRate;
    private final float interval;
    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;
    private final Settings settings;

    public Game(final Grid grid, final GraphicsContext context, Settings settings) {
        this.grid = grid;
        this.context = context;
        frameRate = settings.getFrameRate();
        interval = settings.getInterval();
        running = true;
        paused = false;
        keyIsPressed = false;
        this.settings = settings;
    }

    @Override
    public void run() {
        while (running && !paused) {
            grid.getSnake().updateMoveValue();
            float time = System.currentTimeMillis();

            keyIsPressed = false;
            grid.update();
            Painter.paint(grid, context, settings);

            if (!grid.getSnake().isAlive()) {
                pause();
                System.out.println(grid.getSnake().getLength() + " " + grid.getCols() * grid.getRows());
                if (grid.getSnake().getLength() - 1 == grid.getCols() * grid.getRows()){
                    Painter.paintMegaCongrats(context);
                    break;
                }
                else {
                    Painter.paintResetMessage(context);
                    Painter.paintFinalGoal(context, settings, grid);
                    break;
                }
            }

            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    public void setKeyPressed() {
        keyIsPressed = true;
    }

    public void resume() {
        paused = false;
    }

    public void revertGame(){
        paused = !paused;
    }

    public void pause() {
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
}
