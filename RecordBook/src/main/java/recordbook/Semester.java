package recordbook;

import java.util.ArrayList;

public class Semester {
    private final ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course course){
        courses.add(course);
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public int coursesWithMark(){
        return (int) courses.stream().filter(Course::hasMark).count();
    }

    public int minMark(){
        return courses.stream().filter(Course::hasMark).
                mapToInt(course -> course.getMark().ordinal()).min().orElse(5);
    }

    public int excellentCourses(){
        return (int) courses.stream().filter(course -> course.getMark() == Mark.EXC).count();
    }

    public int markedCoursesSum(){
        return courses.stream().filter(Course::hasMark).
                mapToInt(course -> course.getMark().ordinal()).sum();
    }

    public boolean appliesIncreaseStipend(){
        return ((double) (markedCoursesSum() / coursesWithMark())) == 5.0;
    }
}