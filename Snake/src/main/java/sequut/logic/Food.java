package sequut.logic;

import javafx.scene.paint.Color;

public class Food {
    private static final Color[] COLORS = {
            Color.CORAL,
            Color.DARKORANGE,
            Color.color(0.67, 0.8, 0.93, 0.8),
            Color.color(0.8, 0.8, 1, 0.9),
            Color.color(0.8471, 0.749, 0.8471, 0.8),
            Color.color(0.7412, 0.1, 0.4039, 0.5),
            Color.color(0.7412, 0.7176, 0.41, 0.58)
    };

    private final Color COLOR;
    private Point point;

    public Food(Point point, int random){
        this.point = point;
        this.COLOR = COLORS[random];
    }

    public Color getCOLOR() {
        return this.COLOR;
    }

    public static int getNumberOfColors(){
        return COLORS.length;
    }

    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point){
        this.point = point;
    }
}
