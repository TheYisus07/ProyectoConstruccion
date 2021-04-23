package bussinesslogic;

import domain.Meeting;
import java.util.ArrayList;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */
public interface IMeetingDAO {
    public ArrayList<Meeting> checkMeetingHistory();
    public Meeting scheduleMeeting(Meeting meeting);
    public ArrayList<Meeting> searchEvent(String idMeeting);
}
