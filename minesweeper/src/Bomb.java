public class Bomb {
    private Matrix bombMap;
    private int bombsCount;
    public Bomb (int bombsCount){
        this.bombsCount = bombsCount;
        checkBombCount();
    }
    public void init(Coord coord){
        bombMap = new Matrix(Cell.ZERO);
        for (int i = 0; i < bombsCount; i++)
            placeBombs(coord);
    }

    private void checkBombCount(){
        int maxCount = Field.getSize().x * Field.getSize().y / 2;
        if (bombsCount > maxCount)
            bombsCount = maxCount;
    }
    private void placeBombs(Coord start){
        while (true){
            Coord coord = Field.getRandomCoord();
            if (bombMap.getCell(coord) == Cell.BOMB || start == coord)
                continue;
            bombMap.setCell(coord, Cell.BOMB);
            incNumberAroundBomb(coord);
            break;
        }


    }
    Cell get(Coord coord){
        return bombMap.getCell(coord);
    }

    public int getTotalBombs(){
        return bombsCount;
    }

    private void incNumberAroundBomb(Coord coord){
        for (Coord around : Field.getCoordsAround(coord))
            if (Cell.BOMB != bombMap.getCell(around))
                bombMap.setCell(around, bombMap.getCell(around).getNextNumber());
    }
}
