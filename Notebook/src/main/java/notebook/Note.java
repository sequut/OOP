package notebook;

import java.util.Date;

public class Note {
    private final String title;
    private final String note;
    private final Date time;

    Note(String title, String note, Date time){
        this.title = title;
        this.note = note;
        this.time = time;
    }

    public Date getTime(){
        return time;
    }
    public String getNote(){
        return note;
    }
    public String getTitle(){
        return title;
    }
}
