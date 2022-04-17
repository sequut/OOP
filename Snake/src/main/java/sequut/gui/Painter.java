package sequut.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sequut.logic.*;

public class Painter {
    private static int width;
    private static int height;
    private static int textSize;

    public static void paint(Grid grid, GraphicsContext gc) {
        width = (int) grid.getWidth();
        textSize = Math.min(width, height) / 30;
        height = (int) grid.getHeight() + textSize;


        gc.setFill(Grid.COLOR);
        gc.fillRect(0, 0, grid.getWidth(), height);

        gc.setFill(Color.BEIGE);
        gc.fillRect(0, grid.getHeight(), grid.getWidth(), 1);

        for (int i = 0; i < grid.getFood().size(); i++){
            gc.setFill(grid.getFood().get(i).getCOLOR());
            paintPoint((grid.getFood().get(i).getPoint()), gc);
        }

        Snake snake = grid.getSnake();
        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        if (!snake.isAlive()) {
            gc.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), gc);
        }

        gc.setFill(Color.BEIGE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(Font.font("Arial", textSize));
        gc.fillText("score : " + snake.getPoints().size(), textSize/4, height - textSize/4);
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * Grid.SIZE, point.getY() * Grid.SIZE, Grid.SIZE, Grid.SIZE);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font("Arial", textSize));
        gc.fillText("Hit 'return' to reset", width/2, height/2);
    }
}
