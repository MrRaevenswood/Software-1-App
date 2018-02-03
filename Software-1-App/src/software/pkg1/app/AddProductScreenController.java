/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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
public class AddProductScreenController implements Initializable {
    
    //Add Product Screen Variables
   @FXML
   private Button btn_DeleteAddProduct;
   @FXML
   private Button btn_CancelAddProduct;
   @FXML
   private Button btn_SaveAddProduct;
   @FXML
   private TextField txt_ProductNameAddProduct;
   @FXML
   private TextField txt_InvAddProduct;
   @FXML
   private TextField txt_PriceAddProduct;
   @FXML
   private TextField txt_MaxAddProduct;
   @FXML
   private TextField txt_MinAddProduct;
   @FXML
   private TextField txt_SearchAddProduct;
   @FXML
   private TableView<Part> tbl_SearchTableAddProduct;
   @FXML
   private TableColumn<Part, Integer> coln_SearchPartIDAddProduct;
   @FXML
   private TableColumn<Part, String> coln_SearchPartNameAddProduct;
   @FXML
   private TableColumn<Part, Integer> coln_SearchInvLvlAddProduct;
   @FXML
   private TableColumn<Part, Double> coln_SearchPricePerUnitAddProduct;
   @FXML
   private TableView<Part> tbl_CurrentContentsAddProduct;
   @FXML
   private TableColumn<Part, Integer> coln_CurrentContentsPartID;
   @FXML
   private TableColumn<Part, String> coln_CurrentContentsPartName;
   @FXML
   private TableColumn<Part, Integer> coln_CurrentContentInvLvl;
   @FXML
   private TableColumn<Part, Double> coln_CurrentContentPricePerUnit;
   
   //Add Product Screen Events
   @FXML
   public void populateToBeAssociatedPartsTable(){   
    
    tbl_SearchTableAddProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.getParts());
    
     coln_SearchPartIDAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_SearchPartNameAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_SearchInvLvlAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_SearchPricePerUnitAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
     tbl_SearchTableAddProduct.setItems(list);
   }
   
   
   @FXML
   public void populateAssociatedPartsTable(){
       
       int idIndex = tbl_SearchTableAddProduct.getSelectionModel().getSelectedItem().getPartID();
       
       for(int i = 0; i <= Software1APP.getParts().size() - 1; i++){
           if(idIndex == Software1APP.getParts().get(i).getPartID()){
               Software1APP.getPartsToBeAssociated().add(Software1APP.getParts().get(i));
           }
       }
       
    tbl_CurrentContentsAddProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.getPartsToBeAssociated());
    
     coln_CurrentContentsPartID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_CurrentContentsPartName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_CurrentContentInvLvl.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_CurrentContentPricePerUnit.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
    
     tbl_CurrentContentsAddProduct.setItems(list);
     
   }
   
   @FXML
   public void findPartsToBeAssociated(){
       String partToSearch = txt_SearchAddProduct.getText();
       ArrayList<Part> partsFound = new ArrayList<Part>();
    
       Pattern p = Pattern.compile(partToSearch);
       
       for(int i = 0; i <= Software1APP.getParts().size() - 1; i++){
           
           Matcher m = p.matcher(Software1APP.getParts().get(i).getName());
           
           if(m.find()){
               partsFound.add(Software1APP.getParts().get(i));
           }
       }
       
    tbl_SearchTableAddProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(partsFound);
    
     coln_SearchPartIDAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_SearchPartNameAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_SearchInvLvlAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_SearchPricePerUnitAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
    tbl_SearchTableAddProduct.setItems(list);
       
   }
   
   @FXML
   public void saveProduct(){
       
       double totalPrice = 0;
       int id;
       if(Software1APP.getProducts().isEmpty())
           id = 1;
       else
           id = Software1APP.getProducts().get(Software1APP.getProducts().size() - 1).getProductID() + 1;
       
       int minStock = Integer.parseInt(txt_MinAddProduct.getText());
       int maxStock = Integer.parseInt(txt_MaxAddProduct.getText());
    
       if(txt_InvAddProduct.getText().isEmpty() || Integer.parseInt(txt_InvAddProduct.getText()) < 0){
           
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Inventory value must be 0 or higher");
           alert.showAndWait();
           
           return;
           
       }
       int inventoryStock = Integer.parseInt(txt_InvAddProduct.getText());
       
       if(maxStock <= minStock){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("The minimum cannot be equal or greater than the max");
           alert.showAndWait();
           
           return;
       }else if(inventoryStock < minStock){
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
           alert.setContentText("Inventory cannot be greater than the maximum stock value");
           alert.showAndWait();
           
           return;
       }
       
       
       
       Product productToAdd = new Product();
       ObservableList<Part> associatedParts = tbl_CurrentContentsAddProduct.getItems();
       
       if(associatedParts.isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Please add a part to the product before saving it.");
           alert.showAndWait();
                
           return;
       }
       
       for(Part p : associatedParts){
           totalPrice += p.getPrice();
       }
       
       
       
       productToAdd.setProductID(id);
       productToAdd.setName(txt_ProductNameAddProduct.getText());
       
       if(!txt_InvAddProduct.getText().isEmpty())
            productToAdd.setInStock(Integer.parseInt(txt_InvAddProduct.getText()));
       
       if(!txt_PriceAddProduct.getText().isEmpty())
            productToAdd.setPrice(Double.parseDouble(txt_PriceAddProduct.getText()));
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Product must have a price");
           alert.showAndWait();
           
           return;
       }
           
       productToAdd.setMax(Integer.parseInt(txt_MaxAddProduct.getText()));
       productToAdd.setMin(Integer.parseInt(txt_MinAddProduct.getText()));
       
       for(Part p : associatedParts){
           productToAdd.addAssociatedPart(p);
       }
       
       if(totalPrice > productToAdd.getPrice()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("The Product Price can not be less than the combined price of all of its parts.");
           alert.showAndWait();
           return;
       }else if(productToAdd.getName().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Product must have a name");
           alert.showAndWait();
           
           return;
       }else if(txt_PriceAddProduct.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error in App");
           alert.setContentText("Product must have a price");
           alert.showAndWait();
           
           return;
       }
       
       Software1APP.getMyStock().addProduct(productToAdd);
       Software1APP.getPartsToBeAssociated().clear();
       
       Stage stage = (Stage) btn_SaveAddProduct.getScene().getWindow();
       stage.close();
       
   }
   
   @FXML
   public void cancelAddProduct(){
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Warning");
           alert.setHeaderText("Cancelation Request");
           alert.setContentText("Would you like to stop trying to add a Product?");
       
       Optional<ButtonType> result = alert.showAndWait();
       if(result.get() == ButtonType.OK){
            Stage stage = (Stage) btn_CancelAddProduct.getScene().getWindow();
            stage.close();
       }else
           alert.close();

   }
   
   @FXML
   public void deleteAssociatedPart(){
       int idIndex = tbl_CurrentContentsAddProduct.getSelectionModel().getSelectedItem().getPartID();
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Warning");
           alert.setHeaderText("Deletion Request");
           alert.setContentText("Would you like to delete this associated Part?");
       
       Optional<ButtonType> result = alert.showAndWait();

       if(result.get() == ButtonType.CANCEL){
           alert.close();
            return;
       }else if(result.get() == ButtonType.OK){
        
       for(Part p : Software1APP.getPartsToBeAssociated()){
           if(p.getPartID() == idIndex){
               Software1APP.getPartsToBeAssociated().remove(p);
               break;
           }      
       }
       
    tbl_CurrentContentsAddProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.getPartsToBeAssociated());
    
     coln_CurrentContentsPartID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_CurrentContentsPartName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_CurrentContentInvLvl.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_CurrentContentPricePerUnit.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
    
     tbl_CurrentContentsAddProduct.setItems(list);
     
   }else
           alert.close();
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
     tbl_SearchTableAddProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.getParts());
    
     coln_SearchPartIDAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_SearchPartNameAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_SearchInvLvlAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_SearchPricePerUnitAddProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
     tbl_SearchTableAddProduct.setItems(list);
        
    }
    
}
