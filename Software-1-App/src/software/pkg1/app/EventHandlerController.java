/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import software.pkg1.app.Software1APP;

/**
 *
 * @author rodrigmi
 */
public class EventHandlerController {
    
    //Main Screen Variables
    @FXML
    private TableColumn coln_PRODUCT_ID;
    @FXML
    private TableColumn coln_PRODUCTNAME;
    @FXML
    private TableColumn coln_INVENTORYLEVELPRODUCTS;
    @FXML
    private TableColumn coln_PRICEPERUNIT;
    @FXML
    private TableColumn<Part, Integer> coln_PARTID;
    @FXML
    private TableColumn<Part, String> coln_PARTNAME;
    @FXML
    private TableColumn<Part, Integer> coln_INVENTORYLEVELPARTS;
    @FXML
    private TableColumn<Part, Double>coln_PRICECOSTPERUNIT;
    @FXML
    private TableView<Part> tbl_PARTS;
    
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
   private Button btn_ModifyPartSaveModifyPart;
   @FXML
   private Button btn_ModifyPartCancelModifyPart;
   @FXML
   private TextField txt_MachIdModifyPart;
   @FXML
   private Label lbl_CompNameModifyPart;
   @FXML
   private Label lbl_MachIdModifyPart;
   
    
    //Main Screen Event Handler Methods
    @FXML
    public void openAddPartScreen() throws IOException{
        Parent addPartScreen = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(addPartScreen));
        stage.show();
    }
    
   @FXML
   public void openAddProductScreen() throws IOException{
       Parent addProductScreen = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
       Stage stage = new Stage();
       stage.setScene(new Scene(addProductScreen));
       stage.show();
   }
   
   @FXML
   public void openModifyPartScreen() throws IOException{
       Parent modifyPartScreen = FXMLLoader.load(getClass().getResource("ModifyPartScreen.fxml"));
       Stage stage = new Stage();
       stage.setScene(new Scene(modifyPartScreen));
       stage.show();
       
       Software1APP.setSearchIndex(tbl_PARTS.getSelectionModel().getSelectedItem().partID - 1);

   }
   
   @FXML
   public void populateSelectedPartToModify(){
       
       int idIndex = Software1APP.getSearchIndex() + 1;
       int idToSearch = -1;
       int machId = -1;
       int outPartCounter = 0; 
       String companyName = "";
       
       for(int i = 0; i <= Software1APP.getInPart().size() - 1; i++){
           System.out.println("InPart Part ID " + Software1APP.getInPart().get(i).partID);
           if(Software1APP.getInPart().get(i).partID == idIndex){
               idToSearch = Software1APP.getInPart().get(i).partID - 1;
               
               for (int n = 0; n <= Software1APP.getInPart().size() - 1; i++)
                    {
                        
                        if(Software1APP.getInPart().get(n).partID == idToSearch + 1)
                        {
                            machId = Software1APP.getMachID(n);
                            break;
                        }
                    }
               
               this.machineIDRevealCompanyNameHideModifyPart();
               break;
           }
       }
       System.out.println("id Index " + idIndex);
       System.out.println("Current id to search " + idToSearch);
       if(idToSearch == -1){
           for(int i = 0; i <= Software1APP.getOutPart().size() - 1; i++){
               System.out.println("OutPart Part ID " + Software1APP.getOutPart().get(i).partID);
                if(Software1APP.getOutPart().get(i).partID == idIndex){
                    idToSearch = Software1APP.getOutPart().get(i).partID - 1;
                    
                    for (int n = 0; n <= Software1APP.getOutPart().size() - 1; i++)
                    {
                        
                        if(Software1APP.getOutPart().get(n).partID == idToSearch + 1)
                        {
                            companyName = Software1APP.getCompanyName(n);
                            break;
                        }
                    }
                    this.companyNameReveal_machineIDHideModifyPart();
                    break;
                }
                
            }
        }
       System.out.println("OutPart size " + Software1APP.getOutPart().size());
       System.out.println("InPart size " + Software1APP.getInPart().size());
       System.out.println("id to search " + idToSearch);
       System.out.println("id Index " + idIndex);
       if(machId == -1){
           txt_CompanyNameModifyPart.setText(companyName);
           rb_OutsourcedModifyPart.setSelected(true);
           rb_InHouseModifyPart.setDisable(true);
       }
       else{
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
   
   
   @FXML
   public void openModifyProductScreen() throws IOException{
       Parent modifyProductScreen = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));
       Stage stage = new Stage();
       stage.setScene(new Scene(modifyProductScreen));
       stage.show();
   }
   
   @FXML
   public void populatePartsTable(){
    
    ObservableList list = FXCollections.observableArrayList(Software1APP.getParts());
    
     coln_PARTID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_PARTNAME.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_INVENTORYLEVELPARTS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_PRICECOSTPERUNIT.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
     tbl_PARTS.setItems(list);
   }
   
   @FXML
   public void closeProgram() throws IOException{
       System.exit(0);
   }
   
   
   
   
   //AddPartScreen events
   @FXML
   public void addPart(){
       
       int id;
       if(Software1APP.getParts().isEmpty())
           id = 1;
       else
           id = Software1APP.getParts().size() + 1;
       
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
       Stage stage = (Stage) btn_AddPartCancelAddPart.getScene().getWindow();
       stage.close();
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
   
   
}
