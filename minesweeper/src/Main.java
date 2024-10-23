import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Timer;

public class Main extends JFrame {
    private JPanel jPanel;
    private JLabel jLabel;
    private JLabel timerLabel;
    private final int BOMBSCOUNT = 30;
    private final int IMAGESIZE = 50;
    private final int TIMERSIZE = 36;
    private final Controller controller;
    private boolean timerGoing = false;
    private Timer timer;
    private boolean gamestart = false;

    public static void main(String[] args) {
        new Main();
    }

    private Main () {
        int COLS = 15;
        int ROWS = 15;
        controller = new Controller(COLS, ROWS, BOMBSCOUNT);
        setImages();
        initJlabel();
        initJpanel();
        setupFrame();
    }

    private void initJpanel(){
        jPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics graphics){
                super.paintComponent(graphics);
                if (gamestart){
                    for (Coord coord : Field.getAllCoords())
                        graphics.drawImage((Image)controller.getCell(coord).image,
                                coord.x * IMAGESIZE, coord.y * IMAGESIZE, this);
                }
                else{
                    for (Coord coord : Field.getAllCoords())
                        graphics.drawImage((Image) Cell.CLOSED.image,
                                coord.x * IMAGESIZE, coord.y * IMAGESIZE, this);
                }
            }
        };
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGESIZE;
                int y = e.getY() / IMAGESIZE;
                Coord coord = new Coord(x, y);
                if (!gamestart || controller.gameOver()){
                    startTimer();
                    controller.start(coord);
                    gamestart = true;
                }

                if (e.getButton() == MouseEvent.BUTTON1)
                    controller.leftButton(coord);

                if (e.getButton() == MouseEvent.BUTTON3)
                    controller.rightButton(coord);

                if (e.getButton() == MouseEvent.BUTTON2){
                    controller.start(coord);
                    remove(jLabel);
                    stopTimer();
                    initJlabel();
                }
                jLabel.setText(getMessage());
                if (controller.gameOver())
                    stopTimer();

                jPanel.repaint();
            }
        });
        jPanel.setPreferredSize(new Dimension(Field.getSize().x * IMAGESIZE, Field.getSize().y * IMAGESIZE + TIMERSIZE + 10));
        add(jPanel);
    }

    private void startTimer(){
        if (!timerGoing) {
            try {
                remove(timerLabel);
            }
            catch (Exception ignored){
            }
            initTimerLabel();
            timerGoing = true;
        }
    }
    private void stopTimer(){
        timer.cancel();
        timerGoing = false;
    }


    private String getMessage(){
        String message;
        switch (controller.getGameState()) {
            case PLAYING : message = "playing(count of bombs: " + BOMBSCOUNT + ", current flags: "
                    + controller.getCurrentFlagsNumber() + ")"; break;
            case WINNER : message = "YOU WON"; break;
            case LOSE : message = "YOU LOSE((("; break;
            default: message = "??";
        }
        return message;
    }

    private void setupFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("minesweeper");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void setImages(){
        for (Cell cell : Cell.values()){
            cell.image = getImage(cell.name().toLowerCase());
        }
    }

    private Image getImage(String filename){
        //System.out.println(filename);
        //System.out.println(getClass().getResource("images/" + filename + ".png"));
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/" + filename + ".png")));
        //this.getClass().getResource("images/filename.png")
        return imageIcon.getImage();
    }

    private void initTimerLabel(){
        timer = new Timer();
        timerLabel = new TimerLabel(timer);
        timerLabel.setFont(new Font(timerLabel.getFont().getFontName(), timerLabel.getFont().getStyle(), TIMERSIZE));
        add(timerLabel, BorderLayout.SOUTH);
    }

    private void initJlabel(){
        jLabel = new JLabel("Hi, count of bombs: " + BOMBSCOUNT);
        int LABELSIZE = 20;
        jLabel.setFont(new Font(jLabel.getFont().getFontName(), jLabel.getFont().getStyle(), LABELSIZE));
        add(jLabel, BorderLayout.NORTH);
    }
}