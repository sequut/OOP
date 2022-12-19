public enum Cell {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    INFORM,
    BOMBED,
    NOBOMB;

    public Object pic;

    public int getCellNumber(){
        return this.ordinal();
    }
    public Cell getNextNumber(){
        return Cell.values()[this.ordinal() + 1];
    }
}
