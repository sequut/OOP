public class MainController {
    private State state;
    private final FlagsController flagsController;
    private final BombsController bombsController;

    public MainController(int columns, int rows, int bombsNumber){
        Square.setRate(new Coordinate(columns, rows));
        bombsController = new BombsController(bombsNumber);
        flagsController = new FlagsController();
    }

    public State getState() {
        return state;
    }

    public void start(Coordinate coordinate){
        bombsController.setupBombs(coordinate);
        flagsController.setup(coordinate);
        state = State.PLAY;
    }

    public boolean gameFinished(){
        return state != State.PLAY;
    }

    public Cell getCell(Coordinate coordinate){
        if (flagsController.get(coordinate) == Cell.OPENED)
            return bombsController.get(coordinate);
        else
            return flagsController.get(coordinate);
    }

    public void rightClick(Coordinate coordinate){
        if (gameFinished())
            start(coordinate);
        else
            flagsController.changeFlagCell(coordinate);
    }

    public void leftClick(Coordinate coordinate){
        if (gameFinished())
            start(coordinate);
        else{
            openCell(coordinate);
            if (bombsController.get(coordinate) == Cell.BOMB){
                openAllBombs(coordinate);
                state = State.LOSE;
            }
            else {
                flagsController.openCell(coordinate);
                result();
            }
        }
    }

    private void openCell(Coordinate coordinate){
        switch (flagsController.get(coordinate)){
            case FLAGED: result(); break;
            case OPENED: openCloseCellsAroundNumber(coordinate); break;
            case CLOSED:
                switch (bombsController.get(coordinate)){
                    case BOMB: openAllBombs(coordinate); break;
                    case ZERO: openAroundCells(coordinate); break;
                    default: flagsController.openCell(coordinate);
                }
        }
    }

    public int currentFlagsNumber(){
        return flagsController.countFlags();
    }

    private void openAroundCells(Coordinate coordinate){
        flagsController.openCell(coordinate);
        for (Coordinate coordinate1 : Square.coordinatesAround(coordinate))
            openCell(coordinate1);
    }

    private void result(){
        if (state == State.PLAY){
            if (flagsController.winner(bombsController.getBombsCount())){
                state = State.WIN;
                flagsController.bombsOpenAll();
            }
        }
    }

    public void openCloseCellsAroundNumber(Coordinate coordinate){
        if (bombsController.get(coordinate) != Cell.BOMB)
            if (flagsController.countFlagedCellsAround(coordinate) ==
            bombsController.get(coordinate).getCellNumber())
                for (Coordinate coordinate1 : Square.coordinatesAround(coordinate))
                    if (flagsController.get(coordinate1) == Cell.CLOSED)
                        openCell(coordinate1);
    }

    public void openAllBombs(Coordinate coordinate){
        flagsController.bombedCell(coordinate);
        state = State.LOSE;
        for (Coordinate coordinate1 : Square.getCoordinateArrayList()){
            if (bombsController.get(coordinate1) == Cell.BOMB)
                flagsController.openBombCell(coordinate1);
            else
                flagsController.noBombWithFlagCell(coordinate1);
        }
    }
}
