/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import software.pkg1.app.*;

/**
 *
 * @author rodrigmi
 */
public class Software1APP extends Application {
    
    public static Inventory myStock = new Inventory();
    private static ArrayList<Inhouse> machIDList = new ArrayList<>();
    private static ArrayList<Outsourced> compNameList = new ArrayList<>();
    public static ArrayList<Part> partsToBeAssociated = new ArrayList<>();
    private static int searchIndex = 0;
    private static boolean compNameVisible = false;
    private static boolean machIdVisible = false; 
    private static String currentCompName = ""; 
    private static int currentMachId = -1; 
    private static int currentIdToSearch = -1; 
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();       
    }
    
    public static void addProduct(Product newProduct){
        myStock.addProduct(newProduct);
    }
    
    public static void addPart(Part newPart){
        myStock.addPart(newPart);
    }
    
    public static ArrayList<Part> getParts(){
        return myStock.getAllParts();
    }
    
    public static ArrayList<Product> getProducts(){
        return myStock.getAllProducts();
    }
    
    public static void deletePart(int index){
        myStock.getAllParts().remove(index);
    }
    
    public static void addInPart(Inhouse newPart){
        machIDList.add(newPart);
    }
    
    public static void deleteOutPart(int index){
        compNameList.remove(index);
    }
    
    public static void addOutPart(Outsourced newPart){
        compNameList.add(newPart);
    }
    
    public static void deleteInPart(int index){
        machIDList.remove(index);
    }
    
    public static ArrayList<Inhouse> getInPart(){
        return machIDList;
    }
    
    public static int getMachIdToModify(){
        return currentMachId;
    }
    
    public static String getCompNameToModify(){
        return currentCompName;
    }
    
    public static int getIdToSearch(){
        return currentIdToSearch;
    }
    
    public static ArrayList<Outsourced> getOutPart(){
        return compNameList;
    }
    
    public static int getMachIDFromList(int index){
        return machIDList.get(index).getMachineID();
    }
    
    public static String getCompanyNameFromList(int index){
        return compNameList.get(index).getComapnyName();
    }
    
    public static void setSearchIndex(int index){
        searchIndex = index;
    }
    
    public static int getSearchIndex(){
        return searchIndex;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void setMachIDVisibleCompNameHide(int machId){
        machIdVisible = true;
        compNameVisible = false;
        currentMachId = machId;
    }
    
    public static void setCompNameVisibleMachIdHide(int machId){
        machIdVisible = false;
        compNameVisible = true;
        currentMachId = machId;
    }
    
    public static void setCompanyNameToModify(String companyName){
        currentCompName = companyName;
    }
    
    public static void setIdToSearch(int id){
        currentIdToSearch = id;
    }
    
    public static void setCurrentMachIdToModify(int mId){
        currentMachId = mId;
    }
    
    public static void main(String[] args) {
        launch(args);
          
    }
    
}
