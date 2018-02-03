/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;
import java.util.ArrayList;
import software.pkg1.app.Part;

/**
 *
 * @author rodrigmi
 */
public class Product extends Inventory {
    
    private ArrayList<Part> associatedParts;
    private int productID;
    private String name; 
    private double price;
    private int inStock;
    private int min;
    private int max; 
    
    public Product()
    {
        this.associatedParts = new ArrayList();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void setInStock(int amtInStock){
        this.inStock = amtInStock;
    }
    
    public int getInStock(){
        return this.inStock;
    }
    
    public void setMin(int min){
        this.min = min;
    }
    
    public int getMin(){
        return this.min;
    }
    
    public void setMax(int max){
        this.max = max;
    }
    
    public int getMax(){
        return this.max;
    }
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    public boolean removeAssociatedPart(int indexToRemove){
        if(associatedParts.size() - 1 < indexToRemove)
            return false;
        else{
            associatedParts.remove(indexToRemove);
            return true;
        }
    }
    
    public Part lookupAssociatedPart(int indexToSearch){
        if(associatedParts.size() - 1 < indexToSearch){
            return null;
        }else{
           return associatedParts.get(indexToSearch);
        }    
    }
    
    public void setProductID(int productId){
        this.productID = productId;
    }
    
    public int getProductID(){
        return this.productID;
    }
    
    public ArrayList<Part> getAssociatedParts(){
        return associatedParts;
    }
}
