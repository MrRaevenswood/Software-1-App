/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
           id = Software1APP.getParts().get(Software1APP.getParts().size() - 1).partID + 1;
       
       if(inventoryStock < minStock){
           JOptionPane.showMessageDialog(null, "Your inventory cannot be lower than the minimum.");
           return;
       }else if(inventoryStock > maxStock){
           JOptionPane.showMessageDialog(null, "Inventory cannot be greater than the maximum stock value");
           return;
       }else if(maxStock <= minStock){
           JOptionPane.showMessageDialog(null, "The minimum cannot be equal or greater than the max");
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
       
       int result = JOptionPane.showConfirmDialog(null, "Would you like to stop trying to add a Part?");
       if(result == JOptionPane.YES_OPTION){
            Stage stage = (Stage) btn_AddPartCancelAddPart.getScene().getWindow();
            stage.close();
       }
       
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
