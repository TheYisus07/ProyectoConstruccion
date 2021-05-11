package gui;

import bussinesslogic.EventDAO;
import bussinesslogic.MemberDAO;
import domain.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class ControllerScheduleEvent implements Initializable {
    private final EventDAO eventDAO = new EventDAO();
    private final MemberDAO memberDAO = new MemberDAO();
    @FXML
    private TextField TitleTXT;
    @FXML
    private TextField PlaceTXT;
    @FXML
    private DatePicker RegistrationDateComboBox1;
    @FXML
    private ComboBox<String> TypeComboBox;
    @FXML
    private ComboBox<String> ResponsableComboBox;
    @FXML
    private ComboBox<String> PrivacyComboBox;
    @FXML
    private DatePicker EventDateComboBox;
    @FXML
    private Button ScheduleButton;
    @FXML
    private Button ExitScheduleButton;
    
    ObservableList<Event> eventList;
    
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
    
    public void getActualDate(){
        LocalDate actualDate = LocalDate.now();
        RegistrationDateComboBox1.setValue(actualDate);
    }
    
    @FXML
    public void addEventOnAction(ActionEvent event) throws ParseException {
        try{
            String title = this.TitleTXT.getText();
            String textResponsableComboBox = ResponsableComboBox.getSelectionModel().getSelectedItem();
            String responsable;
            responsable = textResponsableComboBox;
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
            EventDAO eventAUX = new EventDAO();
            Event eventConsult;
            eventConsult = eventAUX.consultEvent(title);        
            String titleEventConsulted;
            titleEventConsulted = eventConsult.getTittle();
            if(!title.equals(titleEventConsulted)){
                this.eventList.add(eventObject);
                eventDAO.scheduleEvent(eventObject);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("El Evento ha sido guardado exitosamente");
                alert.setTitle("Confirmacion");
                alert.setContentText(null);
                alert.showAndWait();
                closeWindow(event);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El Evento existe");
                alert.showAndWait();
            }
        }catch(NullPointerException nullPointerException){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Valores nulos, verifique los campos");
            alert.showAndWait();
            Logger.getLogger(ControllerScheduleEvent.class.getName()).log(Level.SEVERE, null, nullPointerException);
        }
        
    }
    @FXML
    void getOutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("¿Deseas cancelar el registro?");
        alert.setTitle("Salir");
        alert.setContentText("El evento no se registrará");
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
        eventList = FXCollections.observableArrayList();
        fillComboBoxWithMember();
        getActualDate();
    }
}
