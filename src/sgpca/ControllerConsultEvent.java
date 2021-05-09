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
public class ControllerConsultEvent implements Initializable {
    private ControllerConsultEventHistory controllerConsultEventHistory;
    
    @FXML
    private Label TitleEvent;
    
    public void getEventTitleSelected(ControllerConsultEventHistory controllerConsultEventHistory, String eventTitle){
        TitleEvent.setText(eventTitle + "");
        this.controllerConsultEventHistory = controllerConsultEventHistory;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
