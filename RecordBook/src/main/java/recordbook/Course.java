package recordbook;
public class Course {
    private String name;
    private Mark mark;

    public void addCourse(String course){
        this.name = course;
        this.mark = Mark.UNDEFINED;
    }

    public void addCourse(String course, int mark) throws Exception {
        this.name = course;
        this.mark = Mark.transformMark(mark);
    }

    public Mark getMark(){
        if (mark != null && mark != Mark.UNDEFINED) {
            return mark;
        } else {
            throw new RuntimeException("There is no mark");
        }
    }

    public void setMark(int mark) throws Exception {
        this.mark = Mark.transformMark(mark);
    }
}
