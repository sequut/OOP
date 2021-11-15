package notebook;

import com.google.gson.Gson;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Notebook {
    private final static Gson gson = new Gson();
    private final static String filename = "notes.json";

    private static List<Note> take(){
        try (Reader reader = new FileReader(filename)){
            Note[] notes = gson.fromJson(reader, Note[].class);
            return new ArrayList<>(Arrays.asList(notes));
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    private static void write(List<Note> notes){
        try (Writer writer = new FileWriter(filename)){
            gson.toJson(notes, writer);
        }
        catch (IOException e){
            System.err.println("Failed to save notes" + e.getMessage());
        }
    }

    private static void add(Note note){
        List<Note> notes = take();
        notes.add(note);
        write(notes);
    }

    private static void remove(String title){
        List<Note> notes = take();

        if (notes.removeIf(note -> note.getTitle().equals(title)))
            write(notes);
        else
            System.err.println("Failed to delete note");
    }

    private static void show(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<Note> notes = take();
        for (Note note : notes){
            System.out.println(dateFormat.format(note.getTime()));
            System.out.println(note.getTitle());
            System.out.println(note.getNote());
            System.out.println();
        }
    }

    private static void show(Date startTime, Date endTime){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<Note> notes = take();
        Note[] rightNotes = (Note[]) notes.stream().filter(note ->
                note.getTime().after(startTime) && note.getTime().before(endTime)).toArray();

        for (Note note : rightNotes){
            System.out.println(dateFormat.format(note.getTime()));
            System.out.println(note.getTitle());
            System.out.println(note.getNote());
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String action;

        while (input.hasNextLine()){
            try{
                //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ROOT);

                action = input.nextLine();
                if (action.equals("exit"))
                    break;

                String[] words = action.split(" ");
                if (words[0].equals("notebook")){
                    switch (words[1]){
                        case "-add":
                            add(new Note(words[2], words[3], new Date()));
                            break;
                        case "-rm":
                            remove(words[2]);
                            break;
                        case "-show":
                            if (words.length == 2)
                                show();
                            else
                                show(formatter.parse(words[2]), formatter.parse(words[3]));
                            break;
                        default:
                            System.err.println("no such command");
                    }
                }
                else
                    System.err.println("Wrong format");
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        input.close();
    }
}