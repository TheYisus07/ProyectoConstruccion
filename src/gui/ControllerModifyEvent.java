/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bussinesslogic.EventDAO;
import bussinesslogic.MemberDAO;
import domain.Event;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Antonio de Jesús Dominguez García
 */
public class ControllerModifyEvent implements Initializable {
    private final EventDAO eventDAO = new EventDAO();
    private final MemberDAO memberDAO = new MemberDAO();
    private ControllerConsultEvent controllerConsultEvent;
    
    @FXML
    private TextField TitleText;

    @FXML
    private TextField PlaceText;

    @FXML
    private ComboBox<String> ResponsableComboBox;

    @FXML
    private ComboBox<String> TypeComboBox;

    @FXML
    private ComboBox<String> PrivacyComboBox;

    @FXML
    private DatePicker RegistrationDateDatePicker;

    @FXML
    private DatePicker EventDateDatePicker;

    @FXML
    private Button ExitModifyEventButton;

    @FXML
    private Button UpdateEventButton;
    
    
    public void closeWindow(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLConsultEventHistory.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ControllerConsultEventHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fillComboBoxWithMember(){
        ObservableList<String> responsableList = FXCollections.observableArrayList();
        ResponsableComboBox.setItems(responsableList);
        for (int index = 0; index < memberDAO.consultMemberList().size(); index++){
            String memberName = memberDAO.consultMemberList().get(index).getFullName();
            responsableList.add(memberName);
        }
    }
    
    
    
    public void fillModifyEventDates(ControllerConsultEvent controllerConsultEvent, String eventTitle){
        TitleText.setPromptText(eventTitle + "");
        TitleText.setText(eventTitle + "");
        
        
        Event eventConsulted;
        eventConsulted = eventDAO.consultEvent(eventTitle);
        String responsable = eventConsulted.getResponsable();
        String type = eventConsulted.getType();
        String place = eventConsulted.getPlace();
        String privacy = eventConsulted.getPrivacy();
        Date registrationDateAUX = eventConsulted.getRegistrationDate();
        Date eventDateAUX = eventConsulted.getEventDate();
        String registrationDate = (new SimpleDateFormat("yyyy-MM-dd").format(registrationDateAUX));
        LocalDate registrationDateForValue = LocalDate.parse(registrationDate);
        String eventDate = (new SimpleDateFormat("yyyy-MM-dd").format(eventDateAUX));
        
        
        RegistrationDateDatePicker.setValue(registrationDateForValue);
        EventDateDatePicker.setPromptText(eventDate);
        PlaceText.setText(place + "");
        PrivacyComboBox.setPromptText(privacy + "");
        TypeComboBox.setPromptText(type + "");
        ResponsableComboBox.setPromptText(responsable + "");
        this.controllerConsultEvent = controllerConsultEvent;
        
    }

    @FXML
    void UpdateEventOnAction(ActionEvent event) {
        try{
            String titleOfTheOldEvent = TitleText.getPromptText();
            String title = this.TitleText.getText();
            String textResponsableComboBox = ResponsableComboBox.getSelectionModel().getSelectedItem();
            String place = this.PlaceText.getText();
            int registrationDay = RegistrationDateDatePicker.getValue().getDayOfMonth();
            int registrationMonth = RegistrationDateDatePicker.getValue().getMonthValue();
            int registrationYear = RegistrationDateDatePicker.getValue().getYear();
            java.sql.Date registrationDate  = new java.sql.Date((registrationYear-1900), (registrationMonth-1), registrationDay);
            int eventDay = EventDateDatePicker.getValue().getDayOfMonth();
            int eventMonth = EventDateDatePicker.getValue().getMonthValue();
            int eventYear = EventDateDatePicker.getValue().getYear();
            java.sql.Date eventDate  = new java.sql.Date((eventYear-1900), (eventMonth-1), eventDay);
            String textTypeComboBox = TypeComboBox.getSelectionModel().getSelectedItem();
            String textPrivacyComboBox = PrivacyComboBox.getSelectionModel().getSelectedItem();
            Event eventObject = new Event(title, textTypeComboBox, registrationDate, place, eventDate, textPrivacyComboBox, textResponsableComboBox);
             eventDAO.modifyEvent(eventObject,titleOfTheOldEvent);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("El Evento ha sido guardado exitosamente");
            alert.setTitle("Confirmacion");
            alert.showAndWait();
            closeWindow(event);
            
        }catch(NullPointerException nullPointerException){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Valores nulos, verifique los campos");
            alert.showAndWait();
            Logger.getLogger(ControllerModifyEvent.class.getName()).log(Level.SEVERE, null, nullPointerException);
        }

    }

    @FXML
    void getOutOfModifyEventOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("¿Deseas regresar al historial?");
        alert.setTitle("Salir");
        Optional<ButtonType> okCancel = alert.showAndWait();
        if (okCancel.get() == ButtonType.OK){
            closeWindow(event);
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
        fillComboBoxWithMember(); 
    }    
    
}
