public class Controller {
    private final Bomb bomb;
    private final Flag flag;
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    public Controller(int cols, int rows, int bombsCount){
        Field.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombsCount);
        flag = new Flag();
    }

    public void start(Coord coord){
        bomb.init(coord);
        flag.init(coord);
        gameState = GameState.PLAYING;
    }

    public int getCurrentFlagsNumber(){
        return flag.currentFlagsNumber();
    }

    public Cell getCell(Coord coord){
        if (flag.get(coord) == Cell.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    public void leftButton(Coord coord){
        if (gameOver()){
            start(coord);
            return;
        }
        openCell(coord);
        if (bomb.get(coord) == Cell.BOMB){
            openBombs(coord);
            gameState = GameState.LOSE;
            return;
        }

        flag.setOpenedCell(coord);
        checkResult();
    }

    private void openCell(Coord coord){
        switch (flag.get(coord)){
            case OPENED : setOpenedToCloseCellsAroundNumber(coord); break;
            case FLAGGED: checkResult(); break;
            case CLOSED :
                switch (bomb.get(coord)) {
                    case ZERO : openCellAround(coord); break;
                    case BOMB : openBombs(coord); break;
                    default : flag.setOpenedCell(coord);
                }
        }
    }

    public void setOpenedToCloseCellsAroundNumber(Coord coord){
        if (bomb.get(coord) != Cell.BOMB)
            if (flag.getCountOfFlaggedCellsAround(coord) == bomb.get(coord).getNumber())
                for (Coord around : Field.getCoordsAround(coord))
                    if (flag.get(around) == Cell.CLOSED)
                        openCell(around);
    }

    private void openBombs(Coord coord){
        gameState = GameState.LOSE;
        flag.setBombedToCell(coord);
        for (Coord coord1 : Field.getAllCoords()){
            if (bomb.get(coord1) == Cell.BOMB)
                flag.setOpenedToClosedBombCell(coord1);
            else
                flag.setNoBombToFlaggedCell(coord1);
        }
    }

    private void checkResult(){
        if (gameState == GameState.PLAYING){
            if (flag.win(bomb.getTotalBombs())){
                gameState = GameState.WINNER;
                flag.openBombs();
            }
        }
    }

    private void openCellAround(Coord coord){
        flag.setOpenedCell(coord);
        for (Coord around : Field.getCoordsAround(coord))
            openCell(around);
    }

    public void rightButton(Coord coord){
        if (gameOver()){
            start(coord);
            return;
        }
        flag.toggleFlagToCell(coord);
    }

    public boolean gameOver(){
        return gameState != GameState.PLAYING;
    }
}
