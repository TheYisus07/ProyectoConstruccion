/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author INNOVA TEC
 */
public class ControllerConsultConstancy implements Initializable {
    private ControllerConsultConstancyList controllerConsultConstancyList;
    
    @FXML
    private Label TypeConstancy;
    
    public void getConstancyRecognitionTypeSelected(ControllerConsultConstancyList controllerConsultConstancyList, String constancyRecognitionType){
        TypeConstancy.setText(constancyRecognitionType + "");
        this.controllerConsultConstancyList = controllerConsultConstancyList;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
