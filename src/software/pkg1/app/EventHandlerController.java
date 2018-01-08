/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @FXML
    private TextField txt_PARTSEARCH;
    
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
   private Button btn_SaveModifyPart;
   @FXML
   private Button btn_CancelModifyPart;
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
   public void deleteSelectedPart(){
       
       int idIndex = tbl_PARTS.getSelectionModel().getSelectedItem().partID;
       int idToSearch = -1;
       int machId = -1;
       int outPartCounter = 0; 
       String companyName = "";
       
       for(int i = 0; i <= Software1APP.getInPart().size() - 1; i++){
           System.out.println("InPart Part ID " + Software1APP.getInPart().get(i).partID);
           System.out.println("idIndex value" + idIndex);
           if(Software1APP.getInPart().get(i).partID == idIndex){
               idToSearch = Software1APP.getInPart().get(i).partID - 1;
               System.out.println("idToSearch Value " + idToSearch);
               for (int n = 0; n <= Software1APP.getInPart().size() - 1; n++)
                    {
                        System.out.println(n);
                        if(Software1APP.getInPart().get(n).partID == idToSearch + 1)
                        {
                            System.out.println(n);
                            machId = Software1APP.getMachID(n);
                            idToSearch = n;
                            break;
                        }
                    }
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
                    
                    for (int n = 0; n <= Software1APP.getOutPart().size() - 1; n++)
                    {
                        
                        if(Software1APP.getOutPart().get(n).partID == idToSearch + 1)
                        {
                            companyName = Software1APP.getCompanyName(n);
                            idToSearch = n;
                            break;
                        }
                    }
                    break;
                }
                
            }
        }
       
       
       for(int p = 0; p <= Software1APP.getParts().size() - 1; p++){
        
        if(Software1APP.getParts().get(p).partID == idIndex){
            
            Software1APP.deletePart(p);
            break;
            
        }   
           
       }
       
       System.out.println("Current id to search " + idToSearch);
       System.out.println(machId);
       
       if(machId == -1){
           Software1APP.deleteOutPart(idToSearch);
       }
       else{
           Software1APP.deleteInPart(idToSearch);
       }
   }
   
   @FXML
   public void findPart(){
       String partToSearch = txt_PARTSEARCH.getText();
       ArrayList<Part> partsFound = new ArrayList<Part>();
    
       Pattern p = Pattern.compile(partToSearch);
       
       for(int i = 0; i <= Software1APP.getParts().size() - 1; i++){
           
           Matcher m = p.matcher(Software1APP.getParts().get(i).name);
           
           if(m.find()){
               partsFound.add(Software1APP.getParts().get(i));
           }
       }
       
    tbl_PARTS.refresh();
     
    ObservableList list = FXCollections.observableArrayList(partsFound);
    
     coln_PARTID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_PARTNAME.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_INVENTORYLEVELPARTS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_PRICECOSTPERUNIT.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
    tbl_PARTS.setItems(list);
       
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
           System.out.println("idIndex value" + idIndex);
           if(Software1APP.getInPart().get(i).partID == idIndex){
               idToSearch = Software1APP.getInPart().get(i).partID - 1;
               System.out.println("idToSearch Value " + idToSearch);
               for (int n = 0; n <= Software1APP.getInPart().size() - 1; n++)
                    {
                        System.out.println(n);
                        if(Software1APP.getInPart().get(n).partID == idToSearch + 1)
                        {
                            System.out.println(n);
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
                    
                    for (int n = 0; n <= Software1APP.getOutPart().size() - 1; n++)
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
    
    tbl_PARTS.refresh();
     
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
           id = Software1APP.getParts().get(Software1APP.getParts().size() - 1).partID + 1;
       
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
   
   @FXML
   public void modifyPart(){
       
       int searchIndex = Software1APP.getSearchIndex();
       int outPartSearchIndex = 0;
       int inPartSearchIndex = 0; 
       int partId = Software1APP.getParts().get(searchIndex).partID;
       
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
       
       Stage stage = (Stage) btn_CancelModifyPart.getScene().getWindow();
       stage.close();
   }
   
}
