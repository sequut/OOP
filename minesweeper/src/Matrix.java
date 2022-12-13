public class Matrix {
    private final Cell [] [] matrix;
    public Matrix(Cell basicCell){
        matrix = new Cell[Field.getSize().x][Field.getSize().y];
        for (Coord coord : Field.getAllCoords())
            matrix [coord.x] [coord.y] = basicCell;
    }

    public Cell getCell(Coord coord){
        if (Field.inField(coord))
            return matrix [coord.x] [coord.y];
        return null;
    }

    public int totalCells(){
        return Field.getSize().x * Field.getSize().y;
    }

    public void setCell(Coord coord, Cell cell){
        if (Field.inField(coord))
            matrix [coord.x] [coord.y] = cell;
    }
}
