import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Timer;

public class Main extends JFrame{
    private final int picSize = 50;
    private int columns = 12;
    private int rows = 12;
    private int countOfBombs = 12;
    private JLabel label;
    private JPanel panel;
    private JLabel labelTime;
    private final MainController mainController;
    private boolean gameGoing = false;
    private boolean timerGoing = false;
    private boolean timeLabelInit = false;
    private Timer gameTimer;

    public static void main(String[] args) {
        new Main();
    }

    private Main(){
        mainController = new MainController(columns, rows, countOfBombs);
        setupPictures();
        createPanel();
        createLabel();
        setupFrame();
    }

    private void setupFrame(){
        setIconImage(getPic("img/icon.png"));
        setTitle("MineSweeper");
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupPictures(){
        for (Cell cell : Cell.values())
            cell.pic = getPic("img/" + cell.name().toLowerCase() + ".png");
    }

    private Image getPic(String file){
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(
                getClass().getResource(file)));
        return icon.getImage();
    }

    private void createLabel(){
        label = new JLabel("MineSweeper, bombs setup: "
                + countOfBombs);
        label.setFont(new Font(label.getFont().getFontName(),
                label.getFont().getStyle(), 14));
        add(label, BorderLayout.SOUTH);
    }

    private void timerSetGoing(){
        if (!timerGoing){
            if (timeLabelInit)
                remove(labelTime);
        }

        createLabelTime();
        timeLabelInit = true;
        timerGoing = true;
    }

    private void createLabelTime(){
        gameTimer = new Timer();
        labelTime = new MineTimer(gameTimer);
        labelTime.setFont(new Font(labelTime.getFont().getFontName(),
                labelTime.getFont().getStyle(), 15));
        add(labelTime, BorderLayout.NORTH);
    }

    private void createPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics graphics){
                super.paintComponent(graphics);
                if (gameGoing){
                    for (Coordinate coordinate : Square.getCoordinateArrayList()){
                        graphics.drawImage((Image)mainController.getCell(coordinate).pic,
                                coordinate.getX() * picSize, coordinate.getY() * picSize, this);
                    }
                }
                else
                    for (Coordinate coordinate : Square.getCoordinateArrayList())
                        graphics.drawImage((Image) Cell.CLOSED.pic,
                                coordinate.getX() * picSize, coordinate.getY() * picSize, this);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                x /= picSize;
                y /= picSize;
                Coordinate coordinate = new Coordinate(x, y);
                if (!gameGoing || mainController.gameFinished()){
                    if (!timerGoing){
                        if (e.getButton() != MouseEvent.BUTTON3){
                            timerSetGoing();
                            mainController.start(coordinate);
                            gameGoing = true;
                        }
                    }
                }

                if (e.getButton() == MouseEvent.BUTTON1){
                    if (timerGoing)
                        mainController.leftClick(coordinate);
                }


                if (e.getButton() ==  MouseEvent.BUTTON3){
                    if (timerGoing)
                        mainController.rightClick(coordinate);
                }


                String text;
                if (mainController.getState() != null){
                    switch (mainController.getState()){
                        case WIN: text = "congratulations!!"; break;
                        case LOSE: text = "you lost this game."; break;
                        case PLAY: text = "you placed: " + mainController.currentFlagsNumber()
                                + " flags, total bombs: " + countOfBombs; break;
                        default: text = "use left mouse button to start"; break;
                    }
                }
                else {
                    text = "use left mouse button to start";
                }
                label.setText(text);

                if (gameTimer != null){
                    if (mainController.gameFinished()){
                        gameTimer.cancel();
                        timerGoing = false;
                    }
                }

                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Square.getRate().getX() * picSize,
                Square.getRate().getY() * picSize + 20));
        add(panel);
    }
}