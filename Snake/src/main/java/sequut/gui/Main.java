package sequut.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sequut.logic.*;

public class Main extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;

    private Game game;
    private Grid grid;
    private GraphicsContext context;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if (game.isKeyPressed()) {
                return;
            }
            //System.out.println("x: " + snake.getHead().getX() + " | y: " + snake.getHead().getY());
            switch (e.getCode()) {
                case UP:
                    snake.goUp();
                    break;
                case DOWN:
                    snake.goDown();
                    break;
                case LEFT:
                    snake.goLeft();
                    break;
                case RIGHT:
                    snake.goRight();
                    break;
                case ENTER:
                    if (game.isPaused()) {
                        reset();
                        (new Thread(game)).start();
                    }
            }
        });

        reset();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(game)).start();
    }

    private void reset() {
        grid = new Grid(WIDTH, HEIGHT);
        game = new Game(grid, context);
        Painter.paint(grid, context);
    }
}
