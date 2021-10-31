package recordbook;
public class Course {
    private String name;
    private Mark mark;

    /*
    constructor to create a course, without mark
     */
    public Course(String course){
        this.name = course;
        this.mark = Mark.UNDEFINED;
    }

    /*
    constructor for creating a course, with a mark
     */
    public Course(String course, int mark) throws Exception {
        this.name = course;
        this.mark = Mark.transformMark(mark);
    }

    /*
    get course name
     */
    public String getName(){
        return name;
    }

    /*
    check if the course is marked
     */
    public boolean hasMark(){
        return (mark != null && mark != Mark.UNDEFINED);
    }

    /*
    get mark of the course
     */
    public Mark getMark(){
        if (hasMark())
            return mark;
        else
            throw new RuntimeException("There is no mark");
    }

    /*
    change or set mark for course
     */
    public void setMark(int mark) throws Exception {
        this.mark = Mark.transformMark(mark);
    }

    /*
    change or name for course
    */
    public void setName(String name) {
        this.name = name;
    }
}
