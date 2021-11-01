package recordbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class BookTest {

    Book book;

    public BookTest() throws Exception{
        Semester first = new Semester();
        first.addCourse(new Course("Введение в алгебру и анализ", 5));
        first.addCourse(new Course("Введение в дискретную математику", 5));
        first.addCourse(new Course("Императивное программирование", 5));

        Semester second = new Semester();
        second.addCourse(new Course("Делкаративное программирование", 5));
        second.addCourse(new Course("Английский язык", 4));

        book = new Book(22, new Semester[]{first, second});
    }

    @Test
    public void testRedDiploma() {
        book.setGraduationWork(Mark.GOOD);
        assertFalse(book.getRedDiploma());

        book.setGraduationWork(Mark.EXC);
        assertTrue(book.getRedDiploma());
    }

    @Test
    public void testNumSemesters() throws Exception {
        assertEquals(2, book.getSemesters().length);

        Semester third = new Semester();
        third.addCourse(new Course("Английский язык", 5));
        book.addSemester(third);

        assertEquals(3, book.getSemesters().length);
    }

    @Test
    public void testIncreasedStipend_testAverage() throws Exception {
        assertEquals(4.833333333333333, book.allTimeAverageMark());

        assertFalse(book.getSemester(1).appliesIncreaseStipend());

        book.getSemester(1).getCourseByName("Английский язык").setMark(5);

        Throwable thrown_1 = assertThrows(Exception.class, () -> book.getSemester(1).getCourseByName("язык").setMark(5));
        assertEquals(thrown_1.getMessage(), "No such course");

        Throwable thrown_2 = assertThrows(Exception.class, () -> book.getSemester(50).getCourseByName("язык").setMark(5));
        assertEquals(thrown_2.getMessage(), "No such semester");

        assertTrue(book.getSemester(1).appliesIncreaseStipend());
        assertEquals(5, book.allTimeAverageMark());
    }
}
