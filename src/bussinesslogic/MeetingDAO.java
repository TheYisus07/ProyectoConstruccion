package bussinesslogic;

import dataaccess.Conection;
import domain.Meeting;
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
public class MeetingDAO implements IMeetingDAO{
    
    private final Conection connection = new Conection();

    @Override
    public ArrayList<Meeting> checkMeetingHistory() {
        ArrayList<Meeting> arrayListMeetings = new ArrayList<>();
        
        try {
            connection.connect();
            String query = "SELECT idMeeting, Date, TitleOfProyect, Affair, Place, ResponsableName, member_FullName FROM event";
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListMeetings.add(new Meeting(resultSet.getInt("Title"), resultSet.getDate("Type"), resultSet.getString("RegistrationDate"), resultSet.getString("Place"), resultSet.getString("EventDate"), resultSet.getString("Privacy"), resultSet.getString("member_FullName"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListMeetings;
    }

    @Override
    public Meeting scheduleMeeting(Meeting meeting) {
        String date = (new SimpleDateFormat("yyyy-MM-dd").format(meeting.getDate()));
        
        try {
            String query = "INSERT INTO meeting (Date, TitleOfProyect, Place, Affair, ResponsableName, member_FullName) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, meeting.getTitleOfProyect());
            preparedStatement.setString(3, meeting.getPlace());
            preparedStatement.setString(4, meeting.getAffair());
            preparedStatement.setString(5, meeting.getReponsableName());
            preparedStatement.setString(6, meeting.getMeberFullName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex ) {
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return meeting;
    }

    @Override
    public ArrayList<Meeting> searchEvent(String idMeeting) {
        ArrayList<Meeting> arrayListMeetings = new ArrayList<>();
        
        try {
            connection.connect();
            String query = "SELECT idMeeting, Date, TitleOfProyect, Affair, Place, ResponsableName, member_FullName FROM event WHERE idMeeting = ?";
            Statement statement = connection.getConnection().createStatement();
            PreparedStatement  preparedStatement  = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, idMeeting);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListMeetings.add(new Meeting(resultSet.getInt("Title"), resultSet.getDate("Type"), resultSet.getString("RegistrationDate"), resultSet.getString("Place"), resultSet.getString("EventDate"), resultSet.getString("Privacy"), resultSet.getString("member_FullName"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListMeetings;

    }
    
    
}
