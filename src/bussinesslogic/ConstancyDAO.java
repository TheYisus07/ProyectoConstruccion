package bussinesslogic;

import dataaccess.Conection;
import domain.Constancy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */
public class ConstancyDAO implements IConstancyDAO {
    
    private final Conection connection = new Conection();

    @Override
    public ArrayList<Constancy> getAllConstancys() {
        ArrayList<Constancy> arrayListConstancys = new ArrayList<>();
        
        try {
            connection.connect();
            String query = "SELECT RecognitionType, Description, InstitutionalMailOfReceivers, IntitucionalMailValidator, InstitutionalNailRedpient, RegulatoryNotes, event_Title FROM constancy";
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListConstancys.add(new Constancy(resultSet.getString("RecognitionType"), resultSet.getString("Description"), resultSet.getString("InstitutionalMailOfReceivers"), resultSet.getString("IntitucionalMailValidator"), resultSet.getString("InstitutionalNailRedpient"), resultSet.getString("RegulatoryNotes"), resultSet.getString("event_Title"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Constancy.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListConstancys;
    }

    @Override
    public Constancy checkConstancy(String RecognitionType) {        
        
        Constancy constancyChecked = new Constancy();
        
        try {
            connection.connect();
            String query = "SELECT RecognitionType, Description, InstitutionalMailOfReceivers, IntitucionalMailValidator, InstitutionalNailRedpient, RegulatoryNotes, event_Title FROM constancy WHERE RecognitionType = ?";
            PreparedStatement  preparedStatement  = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, RecognitionType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                constancyChecked.setRecognitionType(resultSet.getString("RecognitionType"));
                constancyChecked.setDescription(resultSet.getString("Description"));
                constancyChecked.setInstitutionalMailReceivers(resultSet.getString("InstitutionalMailOfReceivers"));
                constancyChecked.setInstitutionalMailValidator(resultSet.getString("IntitucionalMailValidator"));
                constancyChecked.setInstitutionalMailRedPient(resultSet.getString("InstitutionalNailRedpient"));
                constancyChecked.setRegulatoryNote(resultSet.getString("RegulatoryNotes"));
                constancyChecked.setEventTitle(resultSet.getString("event_Title"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Constancy.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return constancyChecked;
    }

    @Override
    public Constancy generateConstancy(Constancy constancy) {        
        try {
            String query = "INSERT INTO constancy (RecognitionType, Description, InstitutionalMailOfReceivers, IntitucionalMailValidator, InstitutionalNailRedpient, RegulatoryNotes, event_Title) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, constancy.getRecognitionType());
            preparedStatement.setString(2, constancy.getDescription());
            preparedStatement.setString(3,  constancy.getInstitutionalMailReceivers());
            preparedStatement.setString(4, constancy.getInstitutionalMailValidator());
            preparedStatement.setString(5,  constancy.getInstitutionalMailRedPient());
            preparedStatement.setString(6, constancy.getRegulatoryNote());
            preparedStatement.setString(7, constancy.getEventTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException ex ) {
            Logger.getLogger(ConstancyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        
        return constancy;
    }

}
