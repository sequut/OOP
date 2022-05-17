package sequut.gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sequut.logic.*;
import sequut.settings.Settings;

public class Main extends Application {

    private Game game;
    private Grid grid;
    private GraphicsContext context;

    private Settings settings = new Settings();

    public static void main(String[] args) {
        launch();
    }

    Group root = new Group();

    Button button = new Button("Go!");
    HBox buttonBox = new HBox();
    TextField columns = new TextField("10");
    TextField rows = new TextField("10");
    TextField frameRate = new TextField("10");
    TextField foodCount = new TextField("2");
    TextField cellSize = new TextField("100");
    TextField snakes = new TextField("1");
    VBox strings = new VBox();

    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(strings);

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(new Text("S E T T I N G S"));

        strings.getChildren().add(new Text("columns:"));
        strings.getChildren().add(columns);
        strings.getChildren().add(new Text("rows:"));
        strings.getChildren().add(rows);
        strings.getChildren().add(new Text("framerate:"));
        strings.getChildren().add(frameRate);
        strings.getChildren().add(new Text("number of food:"));
        strings.getChildren().add(foodCount);
        strings.getChildren().add(new Text("cell size:"));
        strings.getChildren().add(cellSize);
        strings.getChildren().add(new Text("snakes number:"));
        strings.getChildren().add(snakes);

        strings.getChildren().add(buttonBox);

        buttonBox.setSpacing(100);
        buttonBox.getChildren().add(button);

        int WIDTH = 500;
        int HEIGHT = 600;
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(e -> {
            int cols = Integer.parseInt(columns.getText());
            int row = Integer.parseInt(rows.getText());
            int frame = Integer.parseInt(frameRate.getText());
            int food = Integer.parseInt(foodCount.getText());
            int cellsize = Integer.parseInt(cellSize.getText());
            int snakeCount = Integer.parseInt(snakes.getText());

            cols = cols <= 0 ? row : cols;
            row = row <= 0 ? cols : row;
            frame = frame <= 0 ? 7 : frame;

            food = food <= 0 ? (row * cols) / 2 : food;
            cellsize = cellsize <= 0 ? 50 : cellsize;
            snakeCount = snakeCount <= 0 ? 1 : snakeCount;


            try {
                settings = new Settings(cols, row, frame, food, cellsize, snakeCount);
            }
            catch (Exception exc){
                settings = new Settings();
            }



            if (settings.getWidth() > 2000 || settings.getHeight() > 2000){
                settings = new Settings();
            }


            StackPane root = new StackPane();
            Canvas canvas = new Canvas(settings.getWidth(), settings.getHeight() +
                    Math.min(settings.getHeight(), (settings.getWidth()) / 30));
            context = canvas.getGraphicsContext2D();

            canvas.setFocusTraversable(true);
            canvas.setOnKeyPressed(keyEvent -> {
                Snake snake = grid.getSnake();
                if (game.isKeyPressed()) {
                    return;
                }
                switch (keyEvent.getCode()) {
                    case UP, W:
                        snake.goUp();
                        break;
                    case DOWN, S:
                        snake.goDown();
                        break;
                    case LEFT, A:
                        snake.goLeft();
                        break;
                    case RIGHT, D:
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

            Scene scene1 = new Scene(root);

            primaryStage.setResizable(false);
            primaryStage.setTitle("Snake");
            primaryStage.setOnCloseRequest(f -> System.exit(0));
            primaryStage.setScene(scene1);
            primaryStage.show();

            (new Thread(game)).start();

        });
    }

    private void reset() {
        grid = new Grid(settings);
        game = new Game(grid, context, settings);
        settings.updateGoal();
        Painter.paint(grid, context, settings);
    }
}
