public class Flag {
    private Matrix flagMap;

    void init(Coord coord){
        flagMap = new Matrix(Cell.CLOSED);
        this.setOpenedCell(coord);
    }

    Cell get(Coord coord){
        return flagMap.getCell(coord);
    }

    public void setOpenedCell(Coord coord){
        if (flagMap.getCell(coord) == Cell.BOMB)
            return;
        flagMap.setCell(coord, Cell.OPENED);
    }

    private void setFlagToCell(Coord coord){
        flagMap.setCell(coord, Cell.FLAGGED);
    }

    private void setClosedToCell(Coord coord){
        flagMap.setCell(coord, Cell.CLOSED);
    }

    private void setInformToCell(Coord coord){
        flagMap.setCell(coord, Cell.INFORM);
    }

    public void toggleFlagToCell(Coord coord){
        switch (flagMap.getCell(coord)) {
            case FLAGGED : setInformToCell(coord); break;
            case INFORM : setClosedToCell(coord); break;
            case CLOSED : setFlagToCell(coord); break;
        }
    }

    public void setBombedToCell(Coord coord){
        flagMap.setCell(coord, Cell.BOMBED);
    }

    public void setOpenedToClosedBombCell(Coord coord){
        if (flagMap.getCell(coord) == Cell.CLOSED)
            flagMap.setCell(coord, Cell.OPENED);
    }

    public void setNoBombToFlaggedCell(Coord coord){
        if (flagMap.getCell(coord) == Cell.FLAGGED)
            flagMap.setCell(coord, Cell.NOBOMB);
    }

    public int getCountOfFlaggedCellsAround(Coord coord){
        int count = 0;
        for (Coord around : Field.getCoordsAround(coord))
            if (flagMap.getCell(around) == Cell.FLAGGED)
                count += 1;
        return count;
    }

    public void openBombs(){
        for (Coord coord : Field.getAllCoords())
            if (flagMap.getCell(coord) == Cell.CLOSED ||
            flagMap.getCell(coord) == Cell.INFORM)
                flagMap.setCell(coord, Cell.FLAGGED);
    }

    public boolean win(int countTotalBombs){
        int count = 0;
        for (Coord coord : Field.getAllCoords()){
            if (flagMap.getCell(coord) == Cell.OPENED)
                count += 1;
        }
        return (count == flagMap.totalCells() - countTotalBombs);
    }

    public int currentFlagsNumber(){
        int count = 0;
        for (Coord coord : Field.getAllCoords())
            if (flagMap.getCell(coord) == Cell.FLAGGED)
                count += 1;
        return count;
    }
}
