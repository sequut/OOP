package sequut.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sequut.logic.*;
import sequut.settings.Settings;

public class Painter {
    private static int width;
    private static int height;
    private static int textSize;
    private static int textHelp;

    public static void paint(Grid grid, GraphicsContext gc, Settings settings) {
        width = (int) grid.getWidth();
        textSize = Math.min(width, height) / 30;
        textHelp = (textSize/4);
        height = (int) grid.getHeight() + textSize;

        paintGrid(gc, grid);
        paintLine(gc, grid);
        paintFood(gc, grid);

        Snake snake = grid.getSnake();
        paintSnake(gc, snake);
        paintScore(gc, snake);
        paintGoal(gc, settings);
    }

    private static void paintGrid(GraphicsContext gc, Grid grid){
        gc.setFill(Grid.COLOR);
        gc.fillRect(0, 0, grid.getWidth(), height);
    }

    private static void paintLine(GraphicsContext gc, Grid grid){
        gc.setFill(Color.BEIGE);
        gc.fillRect(0, grid.getHeight(), grid.getWidth(), 1);
    }

    private static void paintSnake(GraphicsContext gc, Snake snake){

        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        if (!snake.isAlive()) {
            gc.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), gc);
        }
    }

    private static void paintFood(GraphicsContext gc, Grid grid){
        for (int i = 0; i < grid.getFood().size(); i++){
            gc.setFill(grid.getFood().get(i).getCOLOR());
            paintPoint((grid.getFood().get(i).getPoint()), gc);
        }
    }

    private static void paintGoal(GraphicsContext gc, Settings settings){
        gc.setFill(Color.BEIGE);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(Font.font("Arial", textSize));

        gc.fillText("goal: " + settings.getGoal(), width - textHelp, height - textHelp);
    }

    private static void paintScore(GraphicsContext gc, Snake snake){
        gc.setFill(Color.BEIGE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(Font.font("Arial", textSize));
        gc.fillText("score: " + snake.getPoints().size(), textHelp, height - textHelp);
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * Grid.SIZE, point.getY() * Grid.SIZE, Grid.SIZE, Grid.SIZE);
    }

    public static void paintFinalGoal(GraphicsContext gc, Settings settings, Grid grid){
        gc.setFill(Grid.COLOR);
        gc.fillRect(0, grid.getHeight(), width, height);
        paintScore(gc, grid.getSnake());
        paintLine(gc, grid);

        if (grid.getSnake().getLength() >= settings.getGoal())
            gc.setFill(Color.DARKGREEN);
        else
            gc.setFill(Color.DARKRED);

        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(Font.font("Arial", textSize));
        gc.fillText("goal: " + settings.getGoal(), width - textHelp, height - textHelp);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font("Arial", textSize));
        int wCenter = width / 2;
        int hCenter = height / 2;
        gc.fillText("Hit 'return' to reset", wCenter, hCenter);
    }
}
