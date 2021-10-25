package recordbook;

/*
make for your marks, mark empty means nothing, so i.e. you can't get this
 */
public enum Mark {
    UNDEFINED, EMPTY, FAIL, SATISFACTORY, GOOD, EXC;

    public static Mark transformMark(int mark) throws Exception {
        if (mark < 2 || mark > 5)
            throw new Exception("Invalid mark");
        return Mark.values()[mark];
    }
}
