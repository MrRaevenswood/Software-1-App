/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigmi
 */
public class MainScreenController implements Initializable{
    //Main Screen Variables for Part Section
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
    
    //Main Screen Variables for Product Section
    @FXML
    private TableView<Product> tbl_PRODUCTS;
    @FXML
    private TableColumn<Product,Integer> coln_PRODUCTID;
    @FXML
    private TableColumn<Product, String> coln_PRODUCTNAME;
    @FXML
    private TableColumn<Product, Integer> coln_INVENTORYLEVELPRODUCTS;
    @FXML
    private TableColumn<Product, Double> coln_PRICEPERUNIT;
    @FXML
    private TextField txt_PRODUCTSEARCH;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    
     //Main Screen Event Handler Methods
    @FXML
    public void openAddPartScreen() throws IOException{
        Parent addPartScreen = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(addPartScreen));
        stage.showAndWait();
        
        populatePartsTable();
    }
    
   @FXML
   public void openAddProductScreen() throws IOException{
       Parent addProductScreen = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
       Stage stage = new Stage();
       stage.setScene(new Scene(addProductScreen));
       stage.showAndWait();
       
       populateProductsTable();
   }
   
  @FXML
   public void openModifyPartScreen() throws IOException{
       Software1APP.setSearchIndex(tbl_PARTS.getSelectionModel().getSelectedItem().getPartID() - 1);
       
       Parent modifyPartScreen = FXMLLoader.load(getClass().getResource("ModifyPartScreen.fxml"));
       Stage stage = new Stage();
       stage.setScene(new Scene(modifyPartScreen));
       stage.showAndWait();
       
       populatePartsTable();

   }
   
   @FXML
   public void deleteProduct(){
       int idIndex = tbl_PRODUCTS.getSelectionModel().getSelectedItem().getProductID();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Warning");
           alert.setHeaderText("Deletion Request");
           alert.setContentText("Would you like to delete this Product?");
           
       Optional<ButtonType> result = alert.showAndWait();
       
       if(result.get() == ButtonType.OK){
           for(Product p : Software1APP.getProducts()){
           if(p.getProductID() == idIndex){
               
               if(!p.getAssociatedParts().isEmpty()){
                  Alert delAlert = new Alert(Alert.AlertType.ERROR);
                  delAlert.setTitle("Error");
                  delAlert.setHeaderText("Error in App");
                  delAlert.setContentText("Delete all associated parts first before deleting this Product.");
                  delAlert.showAndWait();
                    return;
               }
               Software1APP.getMyStock().getAllProducts().remove(p);
               break;
                }
           }
       
       }else {
           alert.close();
       }
       
       populateProductsTable();
   }
   
   @FXML
   public void deleteSelectedPart(){
       
       int idIndex = tbl_PARTS.getSelectionModel().getSelectedItem().getPartID();
       int idToSearch = -1;
       int machId = -1;
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Would you like to delete this Part?");
       
       Optional<ButtonType> result = alert.showAndWait();
       
       if(result.get() == ButtonType.CANCEL){
           return;
       }else if (result.get() == ButtonType.OK){
       
       for(int i = 0; i <= Software1APP.getInPart().size() - 1; i++){

           if(Software1APP.getInPart().get(i).getPartID() == idIndex){
               idToSearch = Software1APP.getInPart().get(i).getPartID() - 1;

               for (int n = 0; n <= Software1APP.getInPart().size() - 1; n++)
                    {
                        
                        if(Software1APP.getInPart().get(n).getPartID() == idToSearch + 1)
                        {
                           
                            machId = Software1APP.getMachIDFromList(n);
                            idToSearch = n;
                            break;
                        }
                    }
               break;
           }
       }

       if(idToSearch == -1){
           for(int i = 0; i <= Software1APP.getOutPart().size() - 1; i++){

                if(Software1APP.getOutPart().get(i).getPartID() == idIndex){
                    idToSearch = Software1APP.getOutPart().get(i).getPartID() - 1;
                    
                    for (int n = 0; n <= Software1APP.getOutPart().size() - 1; n++)
                    {
                        
                        if(Software1APP.getOutPart().get(n).getPartID() == idToSearch + 1)
                        {
                            Software1APP.setCompanyNameToModify(Software1APP.getCompanyNameFromList(n));
                            idToSearch = n;
                            break;
                        }
                    }
                    break;
                }
                
            }
        }
       
       
       for(int p = 0; p <= Software1APP.getParts().size() - 1; p++){
        
        if(Software1APP.getParts().get(p).getPartID() == idIndex){
            
            Software1APP.deletePart(p);
            break;
            
        }   
           
       }
       
       if(machId == -1){
           Software1APP.deleteOutPart(idToSearch);
       }
       else{
           Software1APP.deleteInPart(idToSearch);
       }
   }else
           alert.close(); 
   
   populatePartsTable();
       
}  
   @FXML
   public void findPart(){
       String partToSearch = txt_PARTSEARCH.getText();
       ArrayList<Part> partsFound = new ArrayList<>();
    
       Pattern p = Pattern.compile(partToSearch);
       
       for(int i = 0; i <= Software1APP.getParts().size() - 1; i++){
           
           Matcher m = p.matcher(Software1APP.getParts().get(i).getName());
           
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
   public void findProduct(){
       String productToSearch = txt_PRODUCTSEARCH.getText();
       ArrayList<Product> productsFound = new ArrayList<>();
    
       Pattern p = Pattern.compile(productToSearch);
       
       for(int i = 0; i <= Software1APP.getProducts().size() - 1; i++){
           
           Matcher m = p.matcher(Software1APP.getProducts().get(i).getName());
           
           if(m.find()){
               productsFound.add(Software1APP.getProducts().get(i));
           }
       }
       
    tbl_PRODUCTS.refresh();
     
    ObservableList list = FXCollections.observableArrayList(productsFound);
    
     coln_PRODUCTID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getProductID()));
     coln_PRODUCTNAME.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_INVENTORYLEVELPRODUCTS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_PRICEPERUNIT.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
    tbl_PRODUCTS.setItems(list);
    
       
   }
    
   @FXML
   public void openModifyProductScreen() throws IOException{
       Software1APP.setSearchIndex(tbl_PRODUCTS.getSelectionModel().getSelectedItem().getProductID() - 1);
       
       Parent modifyProductScreen = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));
       Stage stage = new Stage();
       stage.setScene(new Scene(modifyProductScreen));
       stage.showAndWait();
       
       populateProductsTable();
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
   public void populateProductsTable(){
     
    tbl_PRODUCTS.refresh();
    
    ObservableList list = FXCollections.observableArrayList(Software1APP.getProducts());
    
    coln_PRODUCTID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getProductID()));
    coln_PRODUCTNAME.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
    coln_INVENTORYLEVELPRODUCTS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
    coln_PRICEPERUNIT.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
    
    tbl_PRODUCTS.setItems(list);
   }
   
   @FXML
   public void closeProgram() throws IOException{
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Warning");
           alert.setHeaderText("Cancellation Request");
           alert.setContentText("Would you like to close the Program?");
       
       Optional<ButtonType> result = alert.showAndWait();
       
       if(result.get() == ButtonType.OK){
           System.exit(0);
       }else
           alert.close();
       
   }    
}
