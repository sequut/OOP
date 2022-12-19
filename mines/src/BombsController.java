public class BombsController {
    private int bombsCount;
    private Matrix bombs;
    public BombsController(int bombsCount){
        checkAvailableBombs();
        this.bombsCount = bombsCount;
    }

    private void checkAvailableBombs(){
        int maxBombsCount = Square.getRate().getX() * Square.randomCoordinate().getY() / 2;
        bombsCount = Math.min(bombsCount, maxBombsCount);
    }

    public void setupBombs(Coordinate coordinate){
        bombs = new Matrix(Cell.ZERO);
        for (int i = 0; i < bombsCount; i++){
            while (true){
                Coordinate bomb = Square.randomCoordinate();
                if (bombs.getCell(bomb) == Cell.BOMB || coordinate == bomb)
                    continue;
                bombs.setCell(bomb, Cell.BOMB);
                numberAroundBomb(bomb);
                break;
            }
        }
    }

    private void numberAroundBomb(Coordinate coordinate){

        for (Coordinate coordinate1 : Square.coordinatesAround(coordinate))
            if (bombs.getCell(coordinate1) != Cell.BOMB)
                bombs.setCell(coordinate1, bombs.getCell(coordinate1).getNextNumber());
    }

    public Cell get(Coordinate coordinate){
        return bombs.getCell(coordinate);
    }

    public int getBombsCount(){
        return bombsCount;
    }
}
