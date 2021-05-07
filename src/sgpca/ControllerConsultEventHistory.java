/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca;

import bussinesslogic.EventDAO;
import domain.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author INNOVA TEC
 */
public class ControllerConsultEventHistory implements Initializable {
    private final EventDAO eventDAO = new EventDAO();
    
    @FXML
    private TableView<Event> tableViewEvents;

    @FXML
    private TableColumn tableColumnEventTypes;
    
    @FXML
    private TableColumn tableColumnEventTitles;

    @FXML
    private TableColumn tableColumnEventDates;
    
    @FXML
    private Button ExitEventHistoryButton;

    @FXML
    private Button ScheduleEventButton;
    
    

    
    public void showEventHistory() { 
        ObservableList<Event> eventHistoryList = FXCollections.observableArrayList();
        for (int i = 0; i < eventDAO.checkEventHistory().size(); i++) {
            eventHistoryList.add(eventDAO.checkEventHistory().get(i));
        }
        eventHistoryList = FXCollections.observableArrayList();
        this.tableColumnEventTypes.setCellValueFactory(new PropertyValueFactory("Type"));
        this.tableColumnEventTitles.setCellValueFactory(new PropertyValueFactory("Title"));
        this.tableColumnEventDates.setCellValueFactory(new PropertyValueFactory("eventDate"));
        tableViewEvents.setItems(eventHistoryList);
    }

    @FXML
    void OpenScheduleEventGUI(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLScheduleEvent.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ControllerConsultEventHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void getOutOfEventHistory(ActionEvent event) {

    }
        

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEventHistory();
        
    }    
    
}
