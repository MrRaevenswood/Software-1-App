/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
       
       int idIndex = tbl_SearchTableAddProduct.getSelectionModel().getSelectedItem().partID;
       
       for(int i = 0; i <= Software1APP.getParts().size() - 1; i++){
           if(idIndex == Software1APP.getParts().get(i).partID){
               Software1APP.partsToBeAssociated.add(Software1APP.getParts().get(i));
           }
       }
       
    tbl_CurrentContentsAddProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.partsToBeAssociated);
    
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
           
           Matcher m = p.matcher(Software1APP.getParts().get(i).name);
           
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
    
    JOptionPane.showMessageDialog(null, "If part was found, it will be displayed in the table below. To refresh the table with all parts"
            + ", click on an empty spot on the table");
       
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
       int inventoryStock = Integer.parseInt(txt_InvAddProduct.getText());
       
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
       
       
       
       Product productToAdd = new Product();
       ObservableList<Part> associatedParts = tbl_CurrentContentsAddProduct.getItems();
       
       if(associatedParts.isEmpty()){
           JOptionPane.showMessageDialog(null, "Please add a part to the product before saving it.");
           return;
       }
       
       for(Part p : associatedParts){
           totalPrice += p.price;
       }
       
       
       
       productToAdd.setProductID(id);
       productToAdd.setName(txt_ProductNameAddProduct.getText());
       
       if(!txt_InvAddProduct.getText().isEmpty())
            productToAdd.setInStock(Integer.parseInt(txt_InvAddProduct.getText()));
       
       if(!txt_PriceAddProduct.getText().isEmpty())
            productToAdd.setPrice(Double.parseDouble(txt_PriceAddProduct.getText()));
       else{
           JOptionPane.showMessageDialog(null, "Product must have a price");
           return;
       }
           
       productToAdd.setMax(Integer.parseInt(txt_MaxAddProduct.getText()));
       productToAdd.setMin(Integer.parseInt(txt_MinAddProduct.getText()));
       
       for(Part p : associatedParts){
           productToAdd.addAssociatedPart(p);
       }
       
       if(totalPrice > productToAdd.getPrice()){
           JOptionPane.showMessageDialog(null, "The Product Price can not be less than the combined price of all of its parts.");
           return;
       }else if(productToAdd.getName().isEmpty()){
           JOptionPane.showMessageDialog(null, "Product must have a name");
           return;
       }else if(txt_PriceAddProduct.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Product must have a price");
           return;
       }else if(txt_InvAddProduct.getText().isEmpty() || Integer.parseInt(txt_InvAddProduct.getText()) < 0){
           JOptionPane.showMessageDialog(null, "Inventory value must be 0 or higher");
           return;
       }
       
       Software1APP.myStock.addProduct(productToAdd);
       Software1APP.partsToBeAssociated.clear();
       
       Stage stage = (Stage) btn_SaveAddProduct.getScene().getWindow();
       stage.close();
       
   }
   
   @FXML
   public void cancelAddProduct(){
       
       int result = JOptionPane.showConfirmDialog(null, "Would you like to stop trying to add a Product?");
       if(result == JOptionPane.YES_OPTION){
            Stage stage = (Stage) btn_CancelAddProduct.getScene().getWindow();
            stage.close();
       }

   }
   
   @FXML
   public void deleteAssociatedPart(){
       int idIndex = tbl_CurrentContentsAddProduct.getSelectionModel().getSelectedItem().getPartID();
       
       int result = JOptionPane.showConfirmDialog(null, "Would you like to delete this associated part?");
       if(result == JOptionPane.NO_OPTION){
           return; 
       }
       
       for(Part p : Software1APP.partsToBeAssociated){
           if(p.getPartID() == idIndex){
               Software1APP.partsToBeAssociated.remove(p);
               break;
           }      
       }
       
    tbl_CurrentContentsAddProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.partsToBeAssociated);
    
     coln_CurrentContentsPartID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_CurrentContentsPartName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_CurrentContentInvLvl.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_CurrentContentPricePerUnit.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
    
     tbl_CurrentContentsAddProduct.setItems(list);
     
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
