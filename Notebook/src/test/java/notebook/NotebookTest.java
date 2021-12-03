package notebook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class NotebookTest {

    @Test
    public void getEmptyNotebook(){
        Notebook notebook = new Notebook("empty.json");
        Note[] notes = notebook.take().toArray(new Note[0]);
        Assertions.assertArrayEquals(notes, new Note[0]);
        notebook.deleteNotebook();
    }

    @Test
    public void getNonEmptyNotebook(){
        Notebook notebook = new Notebook("nonempty.json");
        notebook.add(new Note("Title", "note", new Date()));
        Note[] notes = notebook.take().toArray(new Note[0]);

        Assertions.assertEquals("Title", notes[0].getTitle());
        Assertions.assertEquals("note", notes[0].getNote());

        notebook.add(new Note("Title2", "note", new Date()));
        notes = notebook.take().toArray(new Note[0]);

        Assertions.assertEquals("Title2", notes[1].getTitle());
        Assertions.assertEquals("note", notes[1].getNote());
        Assertions.assertEquals(2, notes.length);

        notebook.remove("Title2");
        notes = notebook.take().toArray(new Note[0]);

        Assertions.assertEquals("Title", notes[0].getTitle());
        Assertions.assertEquals("note", notes[0].getNote());
        Assertions.assertEquals(1, notes.length);

        notebook.deleteNotebook();
    }
}
