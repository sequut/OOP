package sequut.logic;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    public static final Color COLOR = Color.CORNSILK;
    public static final Color DEAD = Color.FIREBRICK;

    private final Grid grid;
    private final List<Point> snake;
    private Point head;
    private int length;
    private boolean alive;
    private int xDelta;
    private int yDelta;
    private int moveValue = 0;
    private int ate = 0;

    public Snake(Grid grid, Point point) {
        length = 1;
        snake = new LinkedList<>();
        snake.add(point);
        head = point;
        this.grid = grid;
        xDelta = 0;
        alive = true;
        yDelta = 0;
    }

    public List<Point> getPoints() {
        return snake;
    }

    public Point getHead() {
        return head;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getLength() {
        return length;
    }

    public void growTo(Point point){
        length += 1;
        ate = 1;
    }

    public void extend() {
        if (alive) {
            growTo(head.deltaPoint(xDelta, yDelta));
        }
    }

    public void move() {
        if (alive) {
            shiftTo(head.deltaPoint(xDelta, yDelta));
        }
    }

    private void shiftTo(Point point) {
        checkAndAdd(point);
        if (alive){
            if (ate > 0)
                ate = 0;
            else
                snake.remove(0);
        }
    }

    public void updateMoveValue() {
        moveValue = 0;
    }

    private void checkAndAdd(Point point) {
        if ((point.getX() >= grid.getRows() || point.getY() >= grid.getCols() || point.getX() < 0 || point.getY() < 0) ||
        (snake.contains(point) && !point.equals(head))){
            alive = false;
            return;
        }

        snake.add(point);
        head = point;
    }

    public void goUp(){
        if ((yDelta == 1 && length > 1) || moveValue > 0)
            return;
        moveValue += 1;
        xDelta = 0;
        yDelta = -1;
    }

    public void goDown(){
        if ((yDelta == -1 && length > 1) || moveValue > 0)
            return;
        moveValue += 1;
        xDelta = 0;
        yDelta = 1;
    }

    public void goLeft(){
        if ((xDelta == 1 && length > 1) || moveValue > 0)
            return;
        moveValue += 1;
        xDelta = -1;
        yDelta = 0;
    }

    public void goRight(){
        if ((xDelta == -1 && length > 1) || moveValue > 0)
            return;
        moveValue += 1;
        xDelta = 1;
        yDelta = 0;
    }
}
