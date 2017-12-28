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
    
    
    Inventory totalCurrentParts = new Inventory();
    
    for(Part p: Software1APP.getParts()){
        if(p.getClass() == Outsourced.class)
        {
            Outsourced newTempPart = new Outsourced();
            
            newTempPart.setPartID(p.getPartID());
            newTempPart.setName(p.getName());
            newTempPart.setInStock(p.getInStock());
            newTempPart.setPrice(p.getPrice());
            
            totalCurrentParts.addPart(newTempPart);
        }
        else{
            Inhouse newTempPart = new Inhouse();
            
            newTempPart.setPartID(p.getPartID());
            newTempPart.setName(p.getName());
            newTempPart.setInStock(p.getInStock());
            newTempPart.setPrice(p.getPrice());
            
            totalCurrentParts.addPart(newTempPart);
        }
     }
    
    ObservableList list = FXCollections.observableArrayList(totalCurrentParts.getAllParts());
    
    System.out.print(list.size());
    
     coln_PARTID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("coln_PARTID"));
     coln_PARTNAME.setCellValueFactory(new PropertyValueFactory<Part, String>("coln_PARTNAME"));
     coln_INVENTORYLEVELPARTS.setCellValueFactory(new PropertyValueFactory<Part, Integer>("coln_INVENTORYLEVELPARTS"));
     coln_PRICECOSTPERUNIT.setCellValueFactory(new PropertyValueFactory<Part, Double>("coln_PRICECOSTPERUNIT"));
     
     tbl_PARTS.setItems(list);
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
   @FXML
   private Button btn_AddPartCancelAddPart;
   
   
   //AddPartScreen events
   @FXML
   public void addPart(){
       
       if(rb_InHouseAddPart.isSelected())
       {
           Inhouse newInPart = new Inhouse();
           
           newInPart.setMachineID(Software1APP.getParts().size());
           newInPart.setName(txt_PartNameAddPart.getText());
           newInPart.setInStock(Integer.parseInt(txt_InvAddPart.getText()));
           newInPart.setPrice(Double.parseDouble(txt_PriceCostAddPart.getText()));
           newInPart.setMax(Integer.parseInt(txt_MaxAddPart.getText()));
           newInPart.setMin(Integer.parseInt(txt_MinAddPart.getText()));
           
           Software1APP.addPart(newInPart);
           
       } else if(rb_OutsourcedAddPart.isSelected()){
           
           Outsourced newOutPart = new Outsourced();
           
           
           newOutPart.setPartID(Software1APP.getParts().size());
           newOutPart.setName(txt_PartNameAddPart.getText());
           newOutPart.setInStock(Integer.parseInt(txt_InvAddPart.getText()));
           newOutPart.setPrice(Double.parseDouble(txt_PriceCostAddPart.getText()));
           newOutPart.setMax(Integer.parseInt(txt_MaxAddPart.getText()));
           newOutPart.setMin(Integer.parseInt(txt_MinAddPart.getText()));
           newOutPart.setCompanyName(txt_CompanyNameAddPart.getText());
           
           Software1APP.addPart(newOutPart);
        }
 
       Stage stage = (Stage) btn_AddPartSaveAddPart.getScene().getWindow();
       stage.close();
                   
            
   }
   
  @FXML
   public void cancelAddPart(){
       Stage stage = (Stage) btn_AddPartCancelAddPart.getScene().getWindow();
       stage.close();
   }
   
   
}
