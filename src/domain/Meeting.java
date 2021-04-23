package domain;

import java.util.Date;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */
public class Meeting {
    private int idMeeting;
    private Date date;
    private String titleOfProyect, place, affair, reponsableName, meberFullName;
    public Meeting(int idMeeting, Date date, String titleOfProyect, String place, String affair, String reponsableName, String meberFullName) {
        this.idMeeting = idMeeting;
        this.date = date;
        this.titleOfProyect = titleOfProyect;
        this.place = place;
        this.affair = affair;
        this.reponsableName = reponsableName;
        this.meberFullName = meberFullName;
    }

    public int getIdMeeting() {
        return idMeeting;
    }

    public Date getDate() {
        return date;
    }

    public String getTitleOfProyect() {
        return titleOfProyect;
    }

    public String getPlace() {
        return place;
    }

    public String getAffair() {
        return affair;
    }

    public String getReponsableName() {
        return reponsableName;
    }

    public String getMeberFullName() {
        return meberFullName;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitleOfProyect(String titleOfProyect) {
        this.titleOfProyect = titleOfProyect;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setAffair(String affair) {
        this.affair = affair;
    }

    public void setReponsableName(String reponsableName) {
        this.reponsableName = reponsableName;
    }

    public void setMeberFullName(String meberFullName) {
        this.meberFullName = meberFullName;
    }
    
}
