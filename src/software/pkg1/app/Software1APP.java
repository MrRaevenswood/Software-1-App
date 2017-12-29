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
    
    public static void addInPart(Inhouse newPart){
        machIDList.add(newPart);
    }
    
    public static void addOutPart(Outsourced newPart){
        compNameList.add(newPart);
    }
    
    public static int getMachID(int index){
        return machIDList.get(index).getMachineID();
    }
    
    public static String getCompanyName(int index){
        return compNameList.get(index).getComapnyName();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
          
    }
    
}
