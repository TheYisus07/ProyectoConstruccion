package sgpca;

import bussinesslogic.EventDAO;
import bussinesslogic.IEventDAO;
import dataaccess.Conection;
import domain.Event;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Antonio de Jesús Dominguez García
 */
public class sgpca {
    public static void main(String[] args) throws SQLException{
        
        Conection conexion = new Conection();
        conexion.connect();
       
        Date registrationDate = new Date((2021-1900), (3-1), 27);
        Date eventDate = new Date((2021-1900), (4-1), 03);
        IEventDAO eventDAO = new EventDAO();
        Event event;
        event = new Event("evento2", "evento", registrationDate, "xalapa", eventDate, "si", "Jaime");
     
        eventDAO.scheduleEvent(event);
        
        //System.out.println(eventDAO.consultEvent(event));
        
        /*EventDAO eventhistory = new EventDAO();
        ArrayList<Event> arrayListEvents = new ArrayList<>();
        arrayListEvents = eventhistory.checkEventHistory();
        
        System.out.println(arrayListEvents);*/
        
        EventDAO eventConsult = new EventDAO();
        Event evento;
        evento = eventConsult.consultEvent("evento1");
        
        System.out.println(evento);
        
        
    }    
}
