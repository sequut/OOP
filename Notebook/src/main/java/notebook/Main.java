package notebook;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        if (args.length < 1) {
            System.out.println("no input parameters");
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Notebook notebook = new Notebook(args[0] + ".json");

        switch (args[1]) {
            case ("-add"): {
                if (args.length > 3)
                    notebook.add(new Note(args[2], args[3], new Date()));
                else if (args.length == 3)
                    notebook.add(new Note(args[2], "", new Date()));
                else
                    System.err.println("invalid input format");
                break;
            }
            case ("-rm"): {
                if (args.length > 2)
                    notebook.remove(args[2]);
                else
                    System.err.println("invalid input format");
                break;
            }
            case ("-show"): {
                if (args.length == 2)
                    notebook.show();
                else if (args.length >= 5){
                    String[] words = Arrays.copyOfRange(args, 4, args.length);
                    notebook.show(dateFormat.parse(args[2]), dateFormat.parse(args[3]), words);
                }
                else
                    System.err.println("invalid input format");
                break;
            }
            default:
                System.err.println("invalid command format");
        }
    }
}