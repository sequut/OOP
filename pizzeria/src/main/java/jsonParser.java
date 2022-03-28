import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;

public class jsonParser {
    private final String filename;

    public jsonParser(){
        this.filename = "/home/andrey/IdeaProjects/OOP/pizzeria/pizza.json";
    }

    public jsonParser(String path){
        this.filename = path;
    }

    public void getPath(){
        System.out.println(filename);
    }

    public PizzeriaData GetData() throws Exception {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filename)){
            return gson.fromJson(reader, PizzeriaData.class);
        }
        catch (Exception e){
            throw new Exception("couldn't get data");
        }
    }
}