/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigmi
 */
public class AddPartScreenController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    //AddPartScreeen Variables
   @FXML
   private RadioButton rb_InHouseAddPart;
   @FXML
   private RadioButton rb_OutsourcedAddPart;
   @FXML
   private TextField txt_PartNameAddPart;
   @FXML 
   private TextField txt_InvAddPart;
   @FXML
   private TextField txt_PriceCostAddPart;
   @FXML
   private TextField txt_MaxAddPart;
   @FXML
   private TextField txt_MinAddPart;
   @FXML
   private TextField txt_CompanyNameAddPart;
   @FXML
   private TextField txt_IdAddPart;
   @FXML
   private Button btn_AddPartSaveAddPart;
   @FXML
   private Button btn_AddPartCancelAddPart;
   @FXML
   private TextField txt_MachIdAddPart;
   @FXML
   private Label lbl_CompNameAddPart;
   @FXML
   private Label lbl_MachIdAddPart;
   
   
   //AddPartScreen events
   
   @FXML
   public void addPart(){
       
       int id;
       int minStock = Integer.parseInt(txt_MinAddPart.getText());
       int maxStock = Integer.parseInt(txt_MaxAddPart.getText());
       int inventoryStock = Integer.parseInt(txt_InvAddPart.getText());
       
       if(Software1APP.getParts().isEmpty())
           id = 1;
       else
           id = Software1APP.getParts().get(Software1APP.getParts().size() - 1).getPartID() + 1;
       
       if(maxStock <= minStock){
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("The minimum cannot be equal or greater than the max");
           alert.showAndWait(); 

           return;
       }else if(inventoryStock < minStock){
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Your inventory cannot be lower than the minimum.");
           alert.showAndWait(); 
            
           return;
       }else if(inventoryStock > maxStock){
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Inventory cannot be greater than the maximum stock value.");
           alert.showAndWait(); 
           
           return;
       }else if(txt_PartNameAddPart.getText().isEmpty()){
          Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Part must have a name. Please enter one.");
           alert.showAndWait(); 
           
           return; 
       } 
       
       
       
       if(rb_InHouseAddPart.isSelected())
       {
           Inhouse newInPart = new Inhouse();
           
           newInPart.setMachineID(Integer.parseInt(txt_MachIdAddPart.getText()));
           newInPart.setPartID(id);
           newInPart.setName(txt_PartNameAddPart.getText());
           newInPart.setInStock(Integer.parseInt(txt_InvAddPart.getText()));
           newInPart.setPrice(Double.parseDouble(txt_PriceCostAddPart.getText()));
           newInPart.setMax(Integer.parseInt(txt_MaxAddPart.getText()));
           newInPart.setMin(Integer.parseInt(txt_MinAddPart.getText()));
           
           Software1APP.addPart(newInPart);
           Software1APP.addInPart(newInPart);
           
       } else if(rb_OutsourcedAddPart.isSelected()){
           
           Outsourced newOutPart = new Outsourced();
           
           
           newOutPart.setPartID(id);
           newOutPart.setName(txt_PartNameAddPart.getText());
           newOutPart.setInStock(Integer.parseInt(txt_InvAddPart.getText()));
           newOutPart.setPrice(Double.parseDouble(txt_PriceCostAddPart.getText()));
           newOutPart.setMax(Integer.parseInt(txt_MaxAddPart.getText()));
           newOutPart.setMin(Integer.parseInt(txt_MinAddPart.getText()));
           newOutPart.setCompanyName(txt_CompanyNameAddPart.getText());
           
           Software1APP.addPart(newOutPart);
           Software1APP.addOutPart(newOutPart);
        }
 
       Stage stage = (Stage) btn_AddPartSaveAddPart.getScene().getWindow();
       stage.close();
                   
            
   }
   
  @FXML
   public void cancelAddPart(){
      
       Alert alert = new Alert(AlertType.CONFIRMATION);
       alert.setTitle("Warning");
       alert.setHeaderText("Cancellation Request");
       alert.setContentText("Would you like to stop trying to add a Part?");
       
       Optional<ButtonType> result = alert.showAndWait();
       
       if(result.get() == ButtonType.OK){
            Stage stage = (Stage) btn_AddPartCancelAddPart.getScene().getWindow();
            stage.close();
       }else
           alert.close();
   }
   
   @FXML
   public void machineIDReveal_CompanyNameHide(){
        lbl_CompNameAddPart.setVisible(false);    
        txt_CompanyNameAddPart.setVisible(false);
        lbl_MachIdAddPart.setVisible(true);
        txt_MachIdAddPart.setVisible(true);         
   }
   
   @FXML
   public void companyNameReveal_machineIDHide(){
        lbl_CompNameAddPart.setVisible(true);    
        txt_CompanyNameAddPart.setVisible(true);
        lbl_MachIdAddPart.setVisible(false);
        txt_MachIdAddPart.setVisible(false); 
   }
 
}
