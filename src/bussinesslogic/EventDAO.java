package bussinesslogic;

import dataaccess.Conection;
import domain.Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */

public class EventDAO implements IEventDAO{
    private final Conection connection = new Conection();
    
    
    

    @Override
    public Event consultEvent(String tittle) {
        Event eventConsulted = new Event();
        
        try {
            connection.connect();
            String query = "SELECT Title, Type, RegistrationDate, Place, EventDate, Privacy, member_FullName FROM event WHERE Title = ?";
            PreparedStatement  preparedStatement  = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, tittle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                eventConsulted.setTittle(resultSet.getString("Title"));
                eventConsulted.setResponsable(resultSet.getString("member_FullName"));
                eventConsulted.setType(resultSet.getString("Type"));
                eventConsulted.setPlace(resultSet.getString("Place"));
                eventConsulted.setPrivacy(resultSet.getString("Privacy"));
                eventConsulted.setRegistrationDate(resultSet.getDate("RegistrationDate"));
                eventConsulted.setEventDate(resultSet.getDate("EventDate"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return eventConsulted;
    }

    @Override
    public Event scheduleEvent(Event event){
        String registrationDate = (new SimpleDateFormat("yyyy-MM-dd").format(event.getRegistrationDate()));
        String eventDate = (new SimpleDateFormat("yyyy-MM-dd").format(event.getEventDate()));
        
        try {
            String query = "INSERT INTO event (Title, Type, RegistrationDate, Place, EventDate, Privacy, member_FullName) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, event.getTittle());
            preparedStatement.setString(2, event.getType());
            preparedStatement.setString(3,  registrationDate);
            preparedStatement.setString(4, event.getPlace());
            preparedStatement.setString(5,  eventDate);
            preparedStatement.setString(6, event.getPrivacy());
            preparedStatement.setString(7, event.getResponsable());
            preparedStatement.executeUpdate();
        } catch (SQLException ex ) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return event;
    }
    
    @Override
    public void deleteEvent(Event event){
        try {
            String query = "DELETE INTO event WHERE" ;
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
           
        } catch (SQLException ex ) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public Event modifyEvent(Event newEvent, String tittle) {
        //String title = tittle;
        String registrationDate = (new SimpleDateFormat("yyyy-MM-dd").format(newEvent.getRegistrationDate()));
        String eventDate = (new SimpleDateFormat("yyyy-MM-dd").format(newEvent.getEventDate()));
        try {
            String query = "UPDATE event SET Title = ?, Type = ?, RegistrationDate = ?, Place = ?, EventDate = ?, Privacy = ?, member_FullName = ? WHERE Title = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, newEvent.getTittle());
            preparedStatement.setString(2, newEvent.getType());
            preparedStatement.setString(3,  registrationDate);
            preparedStatement.setString(4, newEvent.getPlace());
            preparedStatement.setString(5,  eventDate);
            preparedStatement.setString(6, newEvent.getPrivacy());
            preparedStatement.setString(7, newEvent.getResponsable());
            preparedStatement.setString(8, tittle);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }  
        
        return newEvent;
    }

    @Override
    public ArrayList<Event> searchEvent(String tittle) {
        ArrayList<Event> arrayListEvents = new ArrayList<>();
        
        try {
            connection.connect();
            String query = "SELECT Title, Type, RegistrationDate, Place, EventDate, Privacy, member_FullName FROM event WHERE Title = ?";
            Statement statement = connection.getConnection().createStatement();
            PreparedStatement  preparedStatement  = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, tittle);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListEvents.add(new Event(resultSet.getString("Title"), resultSet.getString("Type"), resultSet.getDate("RegistrationDate"), resultSet.getString("Place"), resultSet.getDate("EventDate"), resultSet.getString("Privacy"), resultSet.getString("member_FullName"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListEvents;
    }
    
    @Override
    public ArrayList<Event> checkEventHistory() {
        ArrayList<Event> arrayListEvents = new ArrayList<>();
        
        try {
            connection.connect();
            String query = "SELECT Title, Type, RegistrationDate, Place, EventDate, Privacy, member_FullName FROM event";
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListEvents.add(new Event(resultSet.getString("Title"), resultSet.getString("Type"), resultSet.getDate("RegistrationDate"), resultSet.getString("Place"), resultSet.getDate("EventDate"), resultSet.getString("Privacy"), resultSet.getString("member_FullName"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListEvents;
    }
    
}
