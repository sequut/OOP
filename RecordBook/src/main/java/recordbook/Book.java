package recordbook;

import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private Mark graduationWork = Mark.UNDEFINED;
    private final int student_id;
    private Semester[] semesters;

    /*
    init book with semesters
     */
    public Book(int student_id, Semester[] semesters) {
        this.student_id = student_id;
        this.semesters = semesters;
    }

    /*
    init book without semesters
     */
    public Book(int student_id) {
        this.student_id = student_id;
    }

    /*
    get student ID
     */
    public int getID(){
        return student_id;
    }

    /*
    get all semesters
     */
    public Semester[] getSemesters(){
        return semesters;
    }

    /*
    get current semester
     */
    public Semester getSemester(int number){
        return semesters[number];
    }

    /*
    add new semester
     */
    public void addSemester(Semester semester){
        semesters = Arrays.copyOf(semesters, getSemesters().length + 1);
        semesters[getSemesters().length - 1] = semester;
    }

    /*
    get min mark
     */
    private int minMark(){
        return Arrays.stream(semesters).
                mapToInt(Semester::minMark).min().orElse(5);
    }

    /*
    get an all-time average mark
     */
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

    /*
    get mark of the graduation work
     */
    public Mark getGraduationWork(){
        return graduationWork;
    }

    /*
    set mark for graduation work
    */
    public void setGraduationWork(Mark mark){
        this.graduationWork = mark;
    }

    /*
    get course by name
     */
    public ArrayList<Course> getCoursesByName(String name){
        ArrayList<Course> courses = new ArrayList<>();
        for (Semester sem1 : semesters)
            for (Course course1 : sem1.getCourses())
                if (course1.getName().equals(name))
                    courses.add(course1);
        return courses;
    }

    /*
    get a percentage of courses with excellent marks
     */
    public double excMarkPercent(){
        int count = 0;
        int total = 0;


        Semester sem_help = new Semester();
        for (Semester semester : semesters){
            Course[] aa = semester.getCourses().toArray(new Course[0]);
            for (Course help : aa)
                sem_help.addCourse(help);
        }

        Course[] courses = sem_help.getCourses().toArray(new Course[0]);
        for (int i = courses.length - 1; i > 0; i--){
            String current = courses[i].getName();
            if (current.equals("CHECKED"))
                continue;
            for (int j = i - 1; j > 0; j--){
                if (courses[j].getName().equals(current))
                    courses[j].setName("CHECKED");
            }
        }

        for (Course course : courses){
            if (course.getName().equals("CHECKED"))
                continue;
            total += 1;
            if (course.getMark() == Mark.EXC)
                count += 1;
        }

        return (double) count / total;
    }

    /*
    check if a student can get a red diploma
     */
    public boolean getRedDiploma(){
        return ((minMark() > 3) && (excMarkPercent() > 0.75)
                && (graduationWork == Mark.EXC) || graduationWork == Mark.UNDEFINED);
    }
}