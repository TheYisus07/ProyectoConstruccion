/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import bussinesslogic.ConstancyDAO;
import domain.Constancy;
import domain.Event;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Antonio de Jesús Dominguez García
 */
public class ControllerGenerateConstancy implements Initializable {
    private final ConstancyDAO constancyDAO = new ConstancyDAO();
    private Event eventObject;
    
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
    
   public void closeWindow(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLConsultEvent.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ControllerConsultEventHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public void getEventForThisConstancy(Event event){
       eventObject = event;
       
   }
    
   @FXML
    void GeneratePDF(ActionEvent event) {
        String recognitionType = RecognitionTypeText.getText();
        String description = DescriptionText.getText();
        String InstitutionalMailReceivers = InstitutionalMailReceiversText.getText();
        String InstitutionalMailValidator = InstitutionalMailValidatorText.getText();
        String InstitutionalMailRedPient = InstitutionalMailRedPientText.getText();
        String regulatoryNote = RegulatoryNoteText.getText();
        
        String eventRegistred = "Hackaton ";
        
         try{
            PDDocument documento = new PDDocument();
            PDPage pagina = new PDPage(PDRectangle.A6);
            documento.addPage(pagina);
            try (PDPageContentStream contenido = new PDPageContentStream(documento, pagina)) {
                contenido.beginText();
                contenido.setFont(PDType1Font.HELVETICA, 11);
                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52);
                contenido.showText(recognitionType);
                contenido.setFont(PDType1Font.COURIER, 11);
                contenido.newLineAtOffset(00, -20);
                contenido.showText(description);
                contenido.newLineAtOffset(00, -20);
                contenido.showText(InstitutionalMailReceivers);
                contenido.newLineAtOffset(00, -20);
                contenido.showText(InstitutionalMailValidator);
                contenido.newLineAtOffset(00, -20);
                contenido.showText(InstitutionalMailRedPient);
                contenido.newLineAtOffset(00, -20);
                contenido.showText(regulatoryNote);
                contenido.newLineAtOffset(00, -20);
            }
            documento.save("C:\\Users\\INNOVA TEC\\Documents\\PDFPrueba\\" + recognitionType + ".pdf");
        }catch(IOException x){
            System.out.println("Error: "+x.getMessage());
        } 
    }
    
    

    @FXML
    void addConstancyOnAction(ActionEvent event) {
        try{
            String recognitionType = RecognitionTypeText.getText();
            String description = DescriptionText.getText();
            String InstitutionalMailReceivers = InstitutionalMailReceiversText.getText();
            String InstitutionalMailValidator = InstitutionalMailValidatorText.getText();
            String InstitutionalMailRedPient = InstitutionalMailRedPientText.getText();
            String regulatoryNote = RegulatoryNoteText.getText();
            System.out.println(eventObject);
            String eventRegistred = eventObject.getTittle();
            //GeneratePDF();
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
                alert.setHeaderText("Se ha generado la constancia");
                alert.setTitle("Confirmacion");
                alert.setContentText(null);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La constancia ya se encuentra registrada en la base de datos");
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
        constancyList = FXCollections.observableArrayList();

    }    
    
}
