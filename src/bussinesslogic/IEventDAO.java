package bussinesslogic;
import java.util.List;
import domain.Event;
import java.util.ArrayList;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */
public interface IEventDAO {
    public Event consultEvent(String tittle);
    public Event scheduleEvent(Event event);
    public void deleteEvent(Event event);
    public Event modifyEvent(Event newEvent, String tittle);
    public ArrayList<Event> searchEvent(String tittle);
    public ArrayList<Event> checkEventHistory();
}
