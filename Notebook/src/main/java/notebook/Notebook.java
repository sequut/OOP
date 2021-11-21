package notebook;

import com.google.gson.Gson;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Notebook {
    private final String filename;
    private final static Gson gson = new Gson();

    public Notebook(){
        this.filename = "notes.json";
    }

    public Notebook(String name) {
        this.filename = name;
    }

    public List<Note> take(){
        try (Reader reader = new FileReader(filename)){
            Note[] notes = gson.fromJson(reader, Note[].class);
            return new ArrayList<>(Arrays.asList(notes));
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    public void write(List<Note> notes){
        try (Writer writer = new FileWriter(filename)){
            gson.toJson(notes, writer);
        }
        catch (IOException e){
            System.err.println("Failed to save notes" + e.getMessage());
        }
    }

    public void add(Note note){
        List<Note> notes = take();
        notes.add(note);
        write(notes);
    }

    public void remove(String title){
        List<Note> notes = take();

        if (notes.removeIf(note -> note.getTitle().equals(title)))
            write(notes);
        else
            System.err.println("Failed to delete note");
    }

    public void show(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<Note> notes = take();
        for (Note note : notes){
            System.out.println(dateFormat.format(note.getTime()));
            System.out.println(note.getTitle());
            System.out.println(note.getNote());
            System.out.println();
        }
    }

    public void show(Date startTime, Date endTime, String[] words){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<Note> notes = take();
        Note[] rightNotes = (Note[]) notes.stream().filter(note ->
                note.getTime().after(startTime) && note.getTime().before(endTime)).toArray();

        int flag = 1;
        for (Note note: rightNotes) {
            for (String word: words) {
                if (!note.getTitle().contains(word)){
                    flag = 0;
                    break;
                }
            }
            if (flag == 1){
                System.out.println(dateFormat.format(note.getTime()));
                System.out.println(note.getTitle());
                System.out.println(note.getNote());
                System.out.println();
            }
        }
    }

    public void deleteNotebook(){
        File file = new File(filename);
        if (file.delete())
            System.out.println("Deleted the file: " + file.getName());
        else
            System.out.println("Failed to delete the file.");
    }
}