public class Matrix {
    private final Cell[][] cells;
    public Matrix(Cell cell){
        cells = new Cell[Square.getRate().getX()][Square.getRate().getY()];
        for (Coordinate coordinate : Square.getCoordinateArrayList())
            cells[coordinate.getX()][coordinate.getY()] = cell;
    }

    public Cell getCell(Coordinate coordinate){
        if (Square.inSquare(coordinate))
            return cells[coordinate.getX()][coordinate.getY()];
        return null;
    }

    public void setCell(Coordinate coordinate, Cell cell){
        if (Square.inSquare(coordinate))
            cells[coordinate.getX()][coordinate.getY()] = cell;
    }

    public int countOfCells(){
        return Square.getRate().getY() * Square.getRate().getX();
    }
}
