package recordbook;

public enum Mark {
    UNDEFINED, POOR, SATISFACTORY, GOOD, EXC;

    public static Mark transformMark(int mark) throws Exception {
        if (mark < 1 || mark > 5)
            throw new Exception("Invalid mark");
        return Mark.values()[mark];
    }
}
