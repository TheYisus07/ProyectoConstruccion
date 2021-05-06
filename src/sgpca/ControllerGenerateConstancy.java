/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca;

import bussinesslogic.ConstancyDAO;
import domain.Constancy;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author INNOVA TEC
 */
public class ControllerGenerateConstancy implements Initializable {
    private final ConstancyDAO constancyDAO = new ConstancyDAO();
    
    @FXML
    private TextField RecognitionTypeText;

    @FXML
    private TextField InstitutionalMailReceiversText;

    @FXML
    private TextField InstitutionalMailValidatorText;

    @FXML
    private TextField InstitutionalMailRedPientText;

    @FXML
    private TextArea DescriptionText;

    @FXML
    private TextArea RegulatoryNoteText;

    @FXML
    private Button ExitGenerateConstancyButton;

    @FXML
    private Button GenerateConstancyButton;
    
    ObservableList<Constancy> constancyList;

    @FXML
    void addConstancyOnAction(ActionEvent event) {
        String recognitionType = RecognitionTypeText.getText();
        String description = DescriptionText.getText();
        String InstitutionalMailReceivers = InstitutionalMailReceiversText.getText();
        String InstitutionalMailValidator = InstitutionalMailValidatorText.getText();
        String InstitutionalMailRedPient = InstitutionalMailRedPientText.getText();
        String regulatoryNote = RegulatoryNoteText.getText();
        String eventRegistred = "hackaton2021";
        
        Constancy constancyObject = new Constancy(recognitionType, description, InstitutionalMailReceivers, InstitutionalMailValidator, InstitutionalMailRedPient, regulatoryNote, eventRegistred);
        ConstancyDAO constancyAUX = new ConstancyDAO();
        Constancy constancyConsult;
        constancyConsult = constancyAUX.checkConstancy(recognitionType);        
        String titleEventConsulted;
        titleEventConsulted = constancyConsult.getRecognitionType();
        if(!recognitionType.equals(titleEventConsulted)){
            this.constancyList.add(constancyObject);
            constancyDAO.generateConstancy(constancyObject);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("El Evento ha sido guardado exitosamente");
            alert.setTitle("Confirmacion");
            alert.setContentText(null);
            alert.showAndWait();
            }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El Evento existe");
            alert.showAndWait();
        }
        
    }
    
    void getOutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("¿Deseas cancelar el registro?");
        alert.setTitle("Salir");
        alert.setContentText("El evento no se registrará");
        Optional<ButtonType> okCancel = alert.showAndWait();
        if (okCancel.get() == ButtonType.OK){
            Stage stage = (Stage) ExitGenerateConstancyButton.getScene().getWindow();
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
        constancyList = FXCollections.observableArrayList();

    }    
    
}
