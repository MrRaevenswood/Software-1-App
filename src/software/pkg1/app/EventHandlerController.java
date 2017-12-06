/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.control.*;

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
    private TableColumn coln_PARTID;
    @FXML
    private TableColumn coln_PARTNAME;
    @FXML
    private TableColumn coln_INVENTORYLEVELPARTS;
    @FXML
    private TableColumn coln_PRICECOSTPERUNIT;
    
    
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
       for(Part p : Software1APP.getParts()){
           coln_PARTID.setText(String.valueOf(p.getPartID()));
       }
   }
   
   @FXML
   public void closeProgram() throws IOException{
       System.exit(0);
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
   
   
   //AddPartScreen events
   @FXML
   public void addPart(){
       
       if(rb_InHouseAddPart.isSelected())
       {
           Inhouse newInPart = new Inhouse();
           
           //newInPart.setMachineID(myStock.allParts.size());
           newInPart.setName(txt_PartNameAddPart.getText());
           //newInPart.setInStock(Integer.parseInt(txt_InvAddPart.getText()));
           newInPart.setPrice(Double.parseDouble(txt_PriceCostAddPart.getText()));
           newInPart.setMax(Integer.parseInt(txt_MaxAddPart.getText()));
           newInPart.setMin(Integer.parseInt(txt_MinAddPart.getText()));
           
           Software1APP.addPart(newInPart);
           
       } else if(rb_OutsourcedAddPart.isSelected()){
           
           Outsourced newOutPart = new Outsourced();
           
           newOutPart.setName(txt_PartNameAddPart.getText());
           newOutPart.setInStock(Integer.parseInt(txt_InvAddPart.getText()));
           newOutPart.setPrice(Double.parseDouble(txt_PriceCostAddPart.getText()));
           newOutPart.setMax(Integer.parseInt(txt_MaxAddPart.getText()));
           newOutPart.setMin(Integer.parseInt(txt_MinAddPart.getText()));
           newOutPart.setCompanyName(txt_CompanyNameAddPart.getText());
           
           Software1APP.addPart(newOutPart);
        }
      
       Platform.exit();
       
            
   }
  
   
}
