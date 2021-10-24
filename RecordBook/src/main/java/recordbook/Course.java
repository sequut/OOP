package recordbook;
public class Course {
    private String name;
    private Mark mark;

    public Course(String course){
        this.name = course;
        this.mark = Mark.UNDEFINED;
    }

    public Course(String course, int mark) throws Exception {
        this.name = course;
        this.mark = Mark.transformMark(mark);
    }

    public boolean hasMark(){
        return (mark != null && mark != Mark.UNDEFINED);
    }

    public Mark getMark(){
        if (hasMark())
            return mark;
        else
            throw new RuntimeException("There is no mark");
    }

    public void setMark(int mark) throws Exception {
        this.mark = Mark.transformMark(mark);
    }
}
