package recordbook;

import java.util.ArrayList;

public class Semester {
    private final ArrayList<Course> courses = new ArrayList<>();

    /*
    add new course
     */
    public void addCourse(Course course){
        courses.add(course);
    }

    /*
    take courses
     */
    public ArrayList<Course> getCourses(){
        return courses;
    }

    /*
    get course by name
     */
    public Course getCourseByName(String name) throws Exception {
        for (Course course1 : courses)
            if (course1.getName().equals(name))
                return course1;
        throw new Exception("No such course");
    }

    /*
    counting courses with mark
     */
    public int coursesWithMark(){
        return (int) courses.stream().filter(Course::hasMark).count();
    }

    /*
    get min mark of the semester
     */
    public int minMark(){
        return courses.stream().filter(Course::hasMark).
                mapToInt(course -> course.getMark().ordinal()).min().orElse(5);
    }

    /*
    counting courses marked as excellent
     */
    public int excellentCourses(){
        return (int) courses.stream().filter(course -> course.getMark() == Mark.EXC).count();
    }

    /*
    count the sum of marked courses
     */
    public int markedCoursesSum(){
        return courses.stream().filter(Course::hasMark).
                mapToInt(course -> course.getMark().ordinal()).sum();
    }

    /*
    check if student can get an increased stipend
     */
    public boolean appliesIncreaseStipend(){
        return ((double) (markedCoursesSum() / coursesWithMark())) == 5.0;
    }
}