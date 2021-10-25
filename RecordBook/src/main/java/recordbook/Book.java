package recordbook;

import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private Mark graduationWork = Mark.UNDEFINED;
    private final int student_id;
    private Semester[] semesters;

    public Book(int student_id, Semester[] semesters) {
        this.student_id = student_id;
        this.semesters = semesters;
    }

    public Book(int student_id) {
        this.student_id = student_id;
    }

    public int getID(){
        return student_id;
    }

    public Semester[] getSemesters(){
        return semesters;
    }

    public Semester getSemester(int number){
        return semesters[number];
    }

    public void addSemester(Semester semester){
        semesters = Arrays.copyOf(semesters, getSemesters().length + 1);
        semesters[getSemesters().length - 1] = semester;
    }

    private int minMark(){
        return Arrays.stream(semesters).
                mapToInt(Semester::minMark).min().orElse(5);
    }

    public double allTimeAverageMark(){
        int count = 0;
        int sum = 0;

        for (Semester sem1 : semesters){
            if (sem1 == null)
                continue;

            int semsum = sem1.markedCoursesSum();
            int semcount = sem1.coursesWithMark();

            if (semcount > 0) {
                sum += semsum;
                count += semcount;
            }
        }
        return count > 0 ? (double) sum / count : 0;
    }

    public Mark getGraduationWork(){
        return graduationWork;
    }

    public void setGraduationWork(Mark mark){
        this.graduationWork = mark;
    }

    public ArrayList<Course> getCoursesByName(String name){
        ArrayList<Course> courses = new ArrayList<>();
        for (Semester sem1 : semesters)
            for (Course course1 : sem1.getCourses())
                if (course1.getName().equals(name))
                    courses.add(course1);
        return courses;
    }

    public double excMarkPercent(){ //сделать только для последних оценок
        int count = 0;
        int total = 0;

        for (Semester sem1 : semesters){
            if (sem1 == null)
                continue;

            int semexc = sem1.excellentCourses();
            int semtotal = sem1.coursesWithMark();

            if (semtotal > 0) {
                count += semexc;
                total += semtotal;
            }
        }
        return (double) count / total;
    }

    public boolean getRedDiploma(){
        return ((minMark() > 3) && (excMarkPercent() > 0.75)
                && (graduationWork == Mark.EXC) || graduationWork == Mark.UNDEFINED);
    }
}