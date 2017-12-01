/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.Stage;
/**
 *
 * @author rodrigmi
 */
public class MainScreenController {
    
    Stage stage = new Stage();
    
    @FXML
    private Button btn_PARTADD;
    
    @FXML
    protected void handlePARTADDAction(ActionEvent event) throws IOException{
        Window owner = btn_PARTADD.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();  
    }
    
    
}
