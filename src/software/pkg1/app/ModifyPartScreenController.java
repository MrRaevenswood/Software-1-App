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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
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
public class ModifyPartScreenController implements Initializable {
    
    //ModifyPartScreeen Variables
   @FXML
   private RadioButton rb_InHouseModifyPart;
   @FXML
   private RadioButton rb_OutsourcedModifyPart;
   @FXML
   private TextField txt_PartNameModifyPart;
   @FXML 
   private TextField txt_InvModifyPart;
   @FXML
   private TextField txt_PriceCostModifyPart;
   @FXML
   private TextField txt_MaxModifyPart;
   @FXML
   private TextField txt_MinModifyPart;
   @FXML
   private TextField txt_CompanyNameModifyPart;
   @FXML
   private TextField txt_IdModifyPart;
   @FXML
   private TextField txt_NameModifyPart;
   @FXML
   private Button btn_SaveModifyPart;
   @FXML
   private Button btn_CancelModifyPart;
   @FXML
   private TextField txt_MachIdModifyPart;
   @FXML
   private Label lbl_CompNameModifyPart;
   @FXML
   private Label lbl_MachIdModifyPart;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
       
       int idIndex = Software1APP.getSearchIndex() + 1;
       int idToSearch = -1;
       int machId = -1;
       
       for(int i = 0; i <= Software1APP.getInPart().size() - 1; i++){

           if(Software1APP.getInPart().get(i).partID == idIndex){
               idToSearch = Software1APP.getInPart().get(i).partID - 1;
              
               for (int n = 0; n <= Software1APP.getInPart().size() - 1; n++)
                    {
                        System.out.println(n);
                        if(Software1APP.getInPart().get(n).partID == idToSearch + 1)
                        {
                            System.out.println(n);
                            machId = Software1APP.getMachIDFromList(n);
                            Software1APP.setCurrentMachIdToModify(machId);
                            break;
                        }
                    }
               
               Software1APP.setMachIDVisibleCompNameHide(machId);
               break;
           }
       }
       if(idToSearch == -1){
           for(int i = 0; i <= Software1APP.getOutPart().size() - 1; i++){
                if(Software1APP.getOutPart().get(i).partID == idIndex){
                    idToSearch = Software1APP.getOutPart().get(i).partID - 1;
                    
                    for (int n = 0; n <= Software1APP.getOutPart().size() - 1; n++)
                    {
                        
                        if(Software1APP.getOutPart().get(n).partID == idToSearch + 1)
                        {
                            Software1APP.setCompanyNameToModify(Software1APP.getCompanyNameFromList(n));
                            break;
                        }
                    }
                    Software1APP.setCompNameVisibleMachIdHide(machId);
                    break;
                }
                
            }
        }
        
        
       machId = Software1APP.getMachIdToModify(); 
       String companyName = Software1APP.getCompNameToModify();
       
       if(machId == -1){
           lbl_MachIdModifyPart.setVisible(false);
           lbl_CompNameModifyPart.setVisible(true);
           txt_MachIdModifyPart.setVisible(false);
           txt_CompanyNameModifyPart.setVisible(true);
           txt_CompanyNameModifyPart.setText(companyName);
           rb_OutsourcedModifyPart.setSelected(true);
           rb_InHouseModifyPart.setDisable(true);
       }
       else{
           lbl_MachIdModifyPart.setVisible(true);
           lbl_CompNameModifyPart.setVisible(false);
           txt_MachIdModifyPart.setVisible(true);
           txt_CompanyNameModifyPart.setVisible(false);
           txt_MachIdModifyPart.setText(Integer.toString(machId));
           rb_InHouseModifyPart.setSelected(true);
           rb_OutsourcedModifyPart.setDisable(true);
       }
       
       txt_NameModifyPart.setText(Software1APP.getParts().get(idToSearch).name);
       txt_InvModifyPart.setText(Integer.toString(Software1APP.getParts().get(idToSearch).inStock));
       txt_PriceCostModifyPart.setText(Double.toString(Software1APP.getParts().get(idToSearch).price));
       txt_MaxModifyPart.setText(Integer.toString(Software1APP.getParts().get(idToSearch).max));
       txt_MinModifyPart.setText(Integer.toString(Software1APP.getParts().get(idToSearch).min));

    }
    
    //Modify Part Screen Events
   @FXML
   public void machineIDRevealCompanyNameHideModifyPart(){
        lbl_CompNameModifyPart.setVisible(false);    
        txt_CompanyNameModifyPart.setVisible(false);
        lbl_MachIdModifyPart.setVisible(true);
        txt_MachIdModifyPart.setVisible(true);  
   }
   
   @FXML
   public void companyNameReveal_machineIDHideModifyPart(){
       lbl_CompNameModifyPart.setVisible(true);    
       txt_CompanyNameModifyPart.setVisible(true);
       lbl_MachIdModifyPart.setVisible(false);
       txt_MachIdModifyPart.setVisible(false);
   }
   
   @FXML
   public void modifyPart(){
       
       int searchIndex = Software1APP.getSearchIndex();
       int outPartSearchIndex = 0;
       int inPartSearchIndex = 0; 
       int partId = Software1APP.getParts().get(searchIndex).partID;
       
       int minStock = Integer.parseInt(txt_MinModifyPart.getText());
       int maxStock = Integer.parseInt(txt_MaxModifyPart.getText());
       int inventoryStock = Integer.parseInt(txt_InvModifyPart.getText());
       
       if(inventoryStock < minStock){
           
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Your inventory cannot be lower than the minimum.");
           alert.showAndWait();
           
           return;
       }else if(inventoryStock > maxStock){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Inventory cannot be greater than the maximum stock value.");
           alert.showAndWait();
           
           return;
       }else if(maxStock <= minStock){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("The minimum cannot be equal or greater than the max.");
           alert.showAndWait();
           
           return;
       }
       
       
       if(rb_InHouseModifyPart.isSelected()){
           
           for(int i = 1; i <= Software1APP.getInPart().size() - 1; i++){
               if(Software1APP.getInPart().get(i).partID == partId){
                   inPartSearchIndex = i;
                   break;
               }
           }
           
           Inhouse inPartToModify = Software1APP.getInPart().get(inPartSearchIndex);
           
           inPartToModify.setName(txt_NameModifyPart.getText());
           inPartToModify.setInStock(Integer.parseInt(txt_InvModifyPart.getText()));
           inPartToModify.setPrice(Double.parseDouble(txt_PriceCostModifyPart.getText()));
           inPartToModify.setMax(Integer.parseInt(txt_MaxModifyPart.getText()));
           inPartToModify.setMin(Integer.parseInt(txt_MinModifyPart.getText()));
           inPartToModify.setMachineID(Integer.parseInt(txt_MachIdModifyPart.getText()));
           
           Software1APP.getParts().get(searchIndex).setName(txt_NameModifyPart.getText());
           Software1APP.getParts().get(searchIndex).setInStock(Integer.parseInt(txt_InvModifyPart.getText()));
           Software1APP.getParts().get(searchIndex).setPrice(Double.parseDouble(txt_PriceCostModifyPart.getText()));
           Software1APP.getParts().get(searchIndex).setMax(Integer.parseInt(txt_MaxModifyPart.getText()));
           Software1APP.getParts().get(searchIndex).setMin(Integer.parseInt(txt_MinModifyPart.getText()));
           
       }else if(rb_OutsourcedModifyPart.isSelected()){
           
           for(int i = 1; i <= Software1APP.getOutPart().size() - 1; i++){
               if(Software1APP.getOutPart().get(i).partID == partId){
                   outPartSearchIndex = i;
                   break;
               }
           }
           Outsourced outPartToModify = Software1APP.getOutPart().get(outPartSearchIndex);
           
           outPartToModify.setName(txt_NameModifyPart.getText());
           outPartToModify.setInStock(Integer.parseInt(txt_InvModifyPart.getText()));
           outPartToModify.setPrice(Double.parseDouble(txt_PriceCostModifyPart.getText()));
           outPartToModify.setMax(Integer.parseInt(txt_MaxModifyPart.getText()));
           outPartToModify.setMin(Integer.parseInt(txt_MinModifyPart.getText()));
           outPartToModify.setCompanyName(txt_CompanyNameModifyPart.getText());          
           
           Part partToModify = Software1APP.getParts().get(searchIndex);
           
           partToModify.setName(txt_NameModifyPart.getText());
           partToModify.setInStock(Integer.parseInt(txt_InvModifyPart.getText()));
           partToModify.setPrice(Double.parseDouble(txt_PriceCostModifyPart.getText()));
           partToModify.setMax(Integer.parseInt(txt_MaxModifyPart.getText()));
           partToModify.setMin(Integer.parseInt(txt_MinModifyPart.getText()));   
       }
       
       Stage stage = (Stage) btn_SaveModifyPart.getScene().getWindow();
       stage.close();
   }
   
   @FXML
   public void cancelModifyPart(){
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Would you like to stop trying to modify a Part?");
           
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
       
       Optional<ButtonType> result = alert.showAndWait();
       
       if(result.get() == yesButton){
            Stage stage = (Stage) btn_CancelModifyPart.getScene().getWindow();
            stage.close();
       }else
           alert.close();

   }
    
}
