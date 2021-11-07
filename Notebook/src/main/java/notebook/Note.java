package notebook;

import java.util.Date;

public class Note {
    private String title;
    private String note;
    private Date time;

    Note(String title, String note, Date time){
        this.title = title;
        this.note = note;
        this.time = time;
    }

    Note(String title, Date time){
        this.title = title;
        this.time = time;
    }

    Note(String title){
        this.title = title;
    }

    public void changeTime(Date time){
        this.time = time;
    }
    public void changeTitle(String title){
        this.title = title;
    }
    public void changeNote(String note){
        this.note = note;
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
