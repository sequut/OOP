public class FlagsController {
    private Matrix flags;
    public void setup(Coordinate coordinate){
        flags = new Matrix(Cell.CLOSED);
        this.openCell(coordinate);
    }

    public void openCell(Coordinate coordinate){
        if (flags.getCell(coordinate) == Cell.BOMB)
            return;
        flags.setCell(coordinate, Cell.OPENED);
    }

    public void changeFlagCell(Coordinate coordinate){
        switch (flags.getCell(coordinate)){
            case INFORM: closeCell(coordinate); break;
            case FLAGED: informCell(coordinate); break;
            case CLOSED: flagCell(coordinate); break;
        }
    }

    public void bombedCell(Coordinate coordinate){
        flags.setCell(coordinate, Cell.BOMBED);
    }

    public void openBombCell(Coordinate coordinate){
        if (flags.getCell(coordinate) == Cell.CLOSED)
            flags.setCell(coordinate, Cell.OPENED);
    }

    public void noBombWithFlagCell(Coordinate coordinate){
        if (flags.getCell(coordinate) == Cell.FLAGED)
            flags.setCell(coordinate, Cell.NOBOMB);
    }

    public int countFlags(){
        int totalFlags = 0;
        for (Coordinate coordinate1 : Square.getCoordinateArrayList())
            if (flags.getCell(coordinate1) == Cell.FLAGED)
                totalFlags++;
        return totalFlags;
    }

    public int countFlagedCellsAround(Coordinate coordinate){
        int totalFlags = 0;
        for (Coordinate coordinate1 : Square.coordinatesAround(coordinate))
            if (flags.getCell(coordinate1) == Cell.FLAGED)
                totalFlags++;
        return totalFlags;
    }

    public void bombsOpenAll(){
        for (Coordinate coordinate : Square.getCoordinateArrayList())
            if (flags.getCell(coordinate) == Cell.CLOSED ||
            flags.getCell(coordinate) == Cell.INFORM)
                flags.setCell(coordinate, Cell.FLAGED);
    }

    public Cell get(Coordinate coordinate){
        return flags.getCell(coordinate);
    }

    private void flagCell(Coordinate coordinate){
        flags.setCell(coordinate, Cell.FLAGED);
    }
    private void informCell(Coordinate coordinate){
        flags.setCell(coordinate, Cell.INFORM);
    }
    private void closeCell(Coordinate coordinate){
        flags.setCell(coordinate, Cell.CLOSED);
    }

    public boolean winner(int bombsCount){
        int help = 0;
        for (Coordinate coordinate : Square.getCoordinateArrayList())
            if (flags.getCell(coordinate) == Cell.OPENED)
                help++;
        boolean status = (help == flags.countOfCells() - bombsCount);
        return status;
    }
}
