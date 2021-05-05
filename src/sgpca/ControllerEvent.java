/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca;

import bussinesslogic.EventDAO;
import domain.Event;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Antonio de Jesús Dominguez García
 */
public class ControllerEvent implements Initializable {
    private final EventDAO eventDAO = new EventDAO();

    
    
    @FXML
    private TextField TitleTXT;

    @FXML
    private TextField PlaceTXT;

    @FXML
    private DatePicker RegistrationDateComboBox1;

    @FXML
    private TextField ResponsableTXT;

    @FXML
    private ComboBox<String> TypeComboBox;
    
    @FXML
    private ComboBox<String> PrivacyComboBox;

    @FXML
    private DatePicker EventDateComboBox;

    @FXML
    private Button ScheduleButton;
    
    ObservableList<Event> eventList;
    
    @FXML
    public void addEventOnAction(ActionEvent event) throws ParseException {
        
        String title = this.TitleTXT.getText();
        String responsable = this.ResponsableTXT.getText();
        String place = this.PlaceTXT.getText();
        int registrationDay = RegistrationDateComboBox1.getValue().getDayOfMonth();
        int registrationMonth = RegistrationDateComboBox1.getValue().getMonthValue();
        int registrationYear = RegistrationDateComboBox1.getValue().getYear();
        Date registrationDate  = new Date((registrationYear-1900), (registrationMonth-1), registrationDay);
        int eventDay = EventDateComboBox.getValue().getDayOfMonth();
        int eventMonth = EventDateComboBox.getValue().getMonthValue();
        int eventYear = EventDateComboBox.getValue().getYear();
        Date eventDate  = new Date((eventYear-1900), (eventMonth-1), eventDay);
        
        String textTypeComboBox = TypeComboBox.getSelectionModel().getSelectedItem();
        String type;
        type = textTypeComboBox;
        
        String textPrivacyComboBox = PrivacyComboBox.getSelectionModel().getSelectedItem();
        String privacy;
        privacy = textPrivacyComboBox;
        
        Event eventObject = new Event(title, type, registrationDate, place, eventDate, privacy, responsable);
        
        if(!this.eventList.contains(eventObject)){
            this.eventList.add(eventObject);
            eventDAO.scheduleEvent(eventObject);
            }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El Evento existe");
            alert.showAndWait();
        }

    }

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> typeList = FXCollections.observableArrayList("seminario", "hackaton", "presentación", "conisof");
        TypeComboBox.setItems(typeList);
        ObservableList<String> privacyList = FXCollections.observableArrayList("alumnos", "docentes", "alumnos y docentes", "cuerpo academico");
        PrivacyComboBox.setItems(privacyList);
        eventList = FXCollections.observableArrayList();
    }    
    
}
