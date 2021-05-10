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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author INNOVA TEC
 */
public class ControllerConsultEvent implements Initializable {
    private ControllerConsultEventHistory controllerConsultEventHistory;
    
    @FXML
    private Label TitleLabel;

    @FXML
    private Label RegistrationDateLabel;

    @FXML
    private Label EventDateLabel;

    @FXML
    private Label PlaceLabel;

    @FXML
    private Label PrivacityLabel;

    @FXML
    private Label TypeLabel;
    
    @FXML
    private Label ResponsableLabel;

    @FXML
    private Button ExitConsultEventButton;

    @FXML
    private Button ShowConstancyListGUIButton;
    
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
    
    public void getEventTitleSelected(ControllerConsultEventHistory controllerConsultEventHistory, String eventTitle){
        TitleLabel.setText(eventTitle + "");
        EventDAO eventDAO = new EventDAO();
        Event eventConsulted;
        eventConsulted = eventDAO.consultEvent(eventTitle);
        String responsable = eventConsulted.getResponsable();
        String type = eventConsulted.getType();
        String place = eventConsulted.getPlace();
        String privacy = eventConsulted.getPrivacy();
        Date registrationDateAUX = eventConsulted.getRegistrationDate();
        Date eventDateAUX = eventConsulted.getEventDate();
        String registrationDate = (new SimpleDateFormat("yyyy-MM-dd").format(registrationDateAUX));
        String eventDate = (new SimpleDateFormat("yyyy-MM-dd").format(eventDateAUX));
        
        RegistrationDateLabel.setText(registrationDate);
        EventDateLabel.setText(eventDate);
        PlaceLabel.setText(place + "");
        PrivacityLabel.setText(privacy + "");
        TypeLabel.setText(type + "");
        ResponsableLabel.setText(responsable + "");
        this.controllerConsultEventHistory = controllerConsultEventHistory;
        
    }
    
    @FXML
    void ShowConstancyListGUIOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLConsultConstancyList.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.hide();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ControllerConsultEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    void getOutOfConsultEventOnAction(ActionEvent event) {
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
        
    }    
    
}
