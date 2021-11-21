package notebook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import notebook.*;

public class Main{
    public static void main(String[] args){
        if (args.length <= 0){
            System.out.println("no input parameters");
            return;
        }

        //Notebook notebook = new Notebook("notes.json");
        Notebook notebook = new Notebook("notes1.json");
        //Notebook notebook = new Notebook();
/*
        switch (args[0]){
            case "-add": {
                break;
            }
            case "-show": {

            }
            case "": {

            }
            default: {
                break;
            }
        }
*/

        for (String str: args)
            System.out.println(str);

    }
}
