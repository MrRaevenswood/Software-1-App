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
public class ModifyProductScreenController implements Initializable {

   //Modify Product Fields
   @FXML
   private TextField txt_NameModifyProduct;
   @FXML
   private TextField txt_InvModifyProduct;
   @FXML
   private TextField txt_PriceModifyProduct;
   @FXML
   private TextField txt_MaxModifyProduct;
   @FXML
   private TextField txt_MinModifyProduct;
   @FXML
   private TextField txt_SearchModifyProduct;
   @FXML
   private Button btn_SearchModifyProduct;
   @FXML
   private Button btn_AddModifyProduct;
   @FXML
   private Button btn_DeleteModifyProduct;
   @FXML
   private Button btn_SaveModifyProduct;
   @FXML
   private Button btn_CancelModifyProduct;
   @FXML
   private TableView<Part> tbl_SearchModifyProduct;
   @FXML
   private TableColumn<Part, Integer> coln_partIDModifyProduct;
   @FXML
   private TableColumn<Part, String> coln_PartNameModifyProduct;
   @FXML
   private TableColumn<Part, Integer> coln_InvModifyProduct;
   @FXML
   private TableColumn<Part, Double> coln_PricePerUnitModifyProduct;
   @FXML
   private TableView<Part> tbl_AddModifyProduct;
   @FXML
   private TableColumn<Part, Integer> coln_PartIDAddPartToProdinModProd;
   @FXML
   private TableColumn<Part, String> coln_PartNameAddPartToProdinModProd;
   @FXML
   private TableColumn<Part, Integer> coln_InvLevelAddPartToProdInModProd;
   @FXML
   private TableColumn<Part, Double> coln_PricePerUnitAddPartToProdInModProd;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int idIndex = Software1APP.getSearchIndex();
       System.out.println("Index: " + idIndex);
       Product prodToModify = new Product();
       
       for(Product p: Software1APP.getProducts()){
           if(p.getProductID() == Software1APP.getProducts().get(idIndex).getProductID()){
              prodToModify = p;
              break;
           }
       }
       
       Software1APP.partsToBeAssociated.clear();
       Software1APP.partsToBeAssociated.addAll(prodToModify.getAssociatedParts());
       
       txt_NameModifyProduct.setText(prodToModify.getName());
       txt_InvModifyProduct.setText(Integer.toString(prodToModify.getInStock()));
       txt_PriceModifyProduct.setText(Double.toString(prodToModify.getPrice()));
       txt_MaxModifyProduct.setText(Integer.toString(prodToModify.getMax()));
       txt_MinModifyProduct.setText(Integer.toString(prodToModify.getMin()));
       
       ObservableList listForAddModProdTable = FXCollections.observableArrayList(prodToModify.getAssociatedParts());
       
       coln_PartIDAddPartToProdinModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
       coln_PartNameAddPartToProdinModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
       coln_InvLevelAddPartToProdInModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
       coln_PricePerUnitAddPartToProdInModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
       
       tbl_AddModifyProduct.setItems(listForAddModProdTable);
       
       tbl_SearchModifyProduct.refresh();
     
       ObservableList listForSearchModProdTable = FXCollections.observableArrayList(Software1APP.getParts());

         coln_partIDModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
         coln_PartNameModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
         coln_InvModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
         coln_PricePerUnitModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));

       tbl_SearchModifyProduct.setItems(listForSearchModProdTable);
    }
    
   @FXML
   public void populatePartsToAddToProduct(){   
    
    tbl_SearchModifyProduct.refresh();
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.getParts());
    
     coln_partIDModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_PartNameModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_InvModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_PricePerUnitModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
     tbl_SearchModifyProduct.setItems(list);
   }
   
   @FXML
   public void findPartsToBeAdded(){
       
       String partToSearch = txt_SearchModifyProduct.getText();
       ArrayList<Part> partsFound = new ArrayList<Part>();
    
       Pattern p = Pattern.compile(partToSearch);
       
       for(int i = 0; i <= Software1APP.getParts().size() - 1; i++){
           
           Matcher m = p.matcher(Software1APP.getParts().get(i).name);
           
           if(m.find()){
               partsFound.add(Software1APP.getParts().get(i));
           }
       }
       
    
     
    ObservableList list = FXCollections.observableArrayList(partsFound);
    
     coln_partIDModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_PartNameModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_InvModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_PricePerUnitModifyProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
     
    tbl_SearchModifyProduct.setItems(list);
    tbl_SearchModifyProduct.refresh();
    
     JOptionPane.showMessageDialog(null, "If part was found, it will be displayed in the table below. To refresh the table with all parts"
            + ", click on an empty spot on the table");
   }
   
   @FXML
   public void addPartToProduct(){
       int pID = tbl_SearchModifyProduct.getSelectionModel().getSelectedItem().getPartID();
       int prodId = 0;
       
       for(int i =0; i < Software1APP.getProducts().size(); i++){
           Product checkProd = Software1APP.getProducts().get(i);
           
           if(!checkProd.getAssociatedParts().isEmpty()){
               for (int p = 0; p < checkProd.getAssociatedParts().size(); p++){
                   if(checkProd.getAssociatedParts().get(p).getPartID() == pID){
                       prodId = checkProd.getProductID() - 1;
                   }
               }
           }
       }
       
       Software1APP.partsToBeAssociated.add(tbl_SearchModifyProduct.getSelectionModel().getSelectedItem());
       
       
     
        ObservableList list = FXCollections.observableArrayList(Software1APP.partsToBeAssociated);

         coln_PartIDAddPartToProdinModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
         coln_PartNameAddPartToProdinModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
         coln_InvLevelAddPartToProdInModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
         coln_PricePerUnitAddPartToProdInModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));

        tbl_AddModifyProduct.setItems(list);
        tbl_AddModifyProduct.refresh();
        

   }
   
   @FXML
   public void modifyProduct(){
       int id = Software1APP.getSearchIndex();
       
       int minStock = Integer.parseInt(txt_MinModifyProduct.getText());
       int maxStock = Integer.parseInt(txt_MaxModifyProduct.getText());
       int inventoryStock = Integer.parseInt(txt_InvModifyProduct.getText());
       
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
       
       
       Product productToAdd = Software1APP.getProducts().get(id);
       
       
       productToAdd.setName(txt_NameModifyProduct.getText());
       productToAdd.setInStock(Integer.parseInt(txt_InvModifyProduct.getText()));
       productToAdd.setPrice(Double.parseDouble(txt_PriceModifyProduct.getText()));
       productToAdd.setMax(Integer.parseInt(txt_MaxModifyProduct.getText()));
       productToAdd.setMin(Integer.parseInt(txt_MinModifyProduct.getText()));
       
       productToAdd.getAssociatedParts().clear();
       
       for(Part p : Software1APP.partsToBeAssociated){
          productToAdd.addAssociatedPart(p);
       }
           
       Software1APP.partsToBeAssociated.clear();
       
       Stage stage = (Stage) btn_SaveModifyProduct.getScene().getWindow();
       stage.close();
   }
   
   @FXML
   public void deleteAssociatedPartinModProd(){
       int id = tbl_AddModifyProduct.getSelectionModel().getSelectedItem().getPartID();
       
       int result = JOptionPane.showConfirmDialog(null, "Would you like to delete this associated Part?");
       if(result == JOptionPane.NO_OPTION){
           return;
       }
       
       for(Part p : Software1APP.getParts()){
           if(p.getPartID() == id){
               
               Software1APP.partsToBeAssociated.remove(p);
               break;
           }     
       }
       
    
     
    ObservableList list = FXCollections.observableArrayList(Software1APP.partsToBeAssociated);
    
     coln_PartIDAddPartToProdinModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getPartID()));
     coln_PartNameAddPartToProdinModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getName()));
     coln_InvLevelAddPartToProdInModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getInStock()));
     coln_PricePerUnitAddPartToProdInModProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getPrice()));
    
     tbl_AddModifyProduct.setItems(list);
     tbl_AddModifyProduct.refresh();
   }
   
   @FXML
   public void closeModifyProduct(){
       
       int result = JOptionPane.showConfirmDialog(null, "Would you like to stop trying to add a Product?");
       if(result == JOptionPane.YES_OPTION){
            Stage stage = (Stage) btn_CancelModifyProduct.getScene().getWindow();
            stage.close();
       }

   }

    
}
