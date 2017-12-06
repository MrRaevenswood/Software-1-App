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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
          
    }
    
}
