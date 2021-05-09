
package sgpca;

import bussinesslogic.EventDAO;
import domain.Event;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author INNOVA TEC
 */
public class ControllerConsultEventHistory implements Initializable {
    private final EventDAO eventDAO = new EventDAO();
    private ControllerConsultEventHistory controllerConsultEventHistory;
    
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
        this.tableColumnEventTypes.setCellValueFactory(new PropertyValueFactory("Type"));
        this.tableColumnEventTitles.setCellValueFactory(new PropertyValueFactory("Tittle"));
        this.tableColumnEventDates.setCellValueFactory(new PropertyValueFactory("eventDate"));
        tableViewEvents.setItems(eventHistoryList);
    }
    
    public void showConsultEventGUI(){
        tableViewEvents.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Event> observable, Event oldEvent, Event newEvent) -> {
            try {
                String eventTittle = tableViewEvents.getSelectionModel().getSelectedItem().getTittle();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLConsultEvent.fxml"));
                Scene scene = new Scene(root);
                
                FXMLLoader fXMLLoader = new FXMLLoader();
                ControllerConsultEvent controllerConsultEvent = (ControllerConsultEvent) fXMLLoader.getController();
                System.out.println(eventTittle);
                controllerConsultEvent.getEventTitleSelected(controllerConsultEventHistory, eventTittle);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ControllerConsultEventHistory.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("¿Desea regresar a la pagina principal?");
        alert.setTitle("Salir");
        Optional<ButtonType> okCancel = alert.showAndWait();
        if (okCancel.get() == ButtonType.OK){
            Stage stage = (Stage) ExitEventHistoryButton.getScene().getWindow();
            stage.close();
        }
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
