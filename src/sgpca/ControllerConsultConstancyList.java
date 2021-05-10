
package sgpca;

import bussinesslogic.ConstancyDAO;
import domain.Constancy;
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
public class ControllerConsultConstancyList implements Initializable {
    private final ConstancyDAO constancyDAO = new ConstancyDAO();
    private ControllerConsultConstancyList controllerConsultConstancyList;
    
    @FXML
    private TableView<Constancy> TableViewConstancy;

    @FXML
    private TableColumn<?, ?> tableColumnConstancyTypes;

    @FXML
    private TableColumn<?, ?> tableColumnConstancyNames;

    @FXML
    private TableColumn<?, ?> tableColumnConstancyPosition;

    @FXML
    private Button GenerateCostancyEvent;

    @FXML
    private Button ExitConstancyListButton;
    
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
    
    public void showConstancyList() { 
        ObservableList<Constancy> constancyList = FXCollections.observableArrayList();
        for (int i = 0; i < constancyDAO.consultConstancyList().size(); i++) {
            constancyList.add(constancyDAO.consultConstancyList().get(i));
        }
        this.tableColumnConstancyTypes.setCellValueFactory(new PropertyValueFactory("RecognitionType"));
        this.tableColumnConstancyNames.setCellValueFactory(new PropertyValueFactory("InstitutionalMailRedpient"));
        this.tableColumnConstancyPosition.setCellValueFactory(new PropertyValueFactory("InstitutionalMailRedpient"));
        TableViewConstancy.setItems(constancyList);
   }
    
    public void showConsultEventGUI(){
        TableViewConstancy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Constancy>(){
            
            @Override
            public void changed(ObservableValue<? extends Constancy> observable, Constancy oldValue, Constancy newValue) {
                try {
                    Stage stage =  (Stage) TableViewConstancy.getScene().getWindow();
                    
                    stage.hide();
                    FXMLLoader fXMLLoader = new FXMLLoader();
                    fXMLLoader.setLocation(getClass().getResource("FXMLConsultConstancy.fxml"));
                    fXMLLoader.load();
                    ControllerConsultConstancy controllerConsultConstancy = fXMLLoader.getController();
                    String constancyRecognitionType = observable.getValue().getRecognitionType();
                    controllerConsultConstancy.getConstancyRecognitionTypeSelected(controllerConsultConstancyList, constancyRecognitionType);
                    Parent root = fXMLLoader.getRoot();
                    
                    Stage stageConsultConstancy = new Stage();
                    Scene scene = new Scene(root);
                    stageConsultConstancy.initStyle(StageStyle.DECORATED);
                    stageConsultConstancy.setScene(scene);
                    stageConsultConstancy.show();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerConsultConstancyList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        });
    }

    @FXML
    void OpenGenerateConstancyGUI(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLGenerateConstancy.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ControllerConsultConstancyList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void getOutOfConstancyList(ActionEvent event) {
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
        showConstancyList();
        showConsultEventGUI();
    }    
    
}
