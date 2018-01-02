/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

import java.util.ArrayList;
import software.pkg1.app.Product;
import software.pkg1.app.Part;
/**
 *
 * @author rodrigmi
 */
public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Part> allparts;

    public Inventory() {
        this.products = new ArrayList();
        this.allparts = new ArrayList();
    }
 
    public void addProduct(Product product)
    {
        products.add(product);
    }
    
    public boolean removeProduct(int indexToRemove)
    {
        if(products.size() - 1 < indexToRemove) {
            return false;
        } else {
            products.remove(indexToRemove);
            return true;
        }
    }
    
    public Product lookupProduct(int indexToSearch)
    {
      if(products.size() - 1 < indexToSearch){
          return null;
      }else{
          return products.get(indexToSearch);
      }
    }
    
    public void updateProduct(int indexToUpdate){
        
    }
    
    public void addPart(Part newPart)
    {
        allparts.add(newPart);
    }
    
    public boolean deletePart(Part partToDelete)
    {
        if(allparts.contains(partToDelete)){
            allparts.remove(partToDelete);
            return true;
        }else{
            return false;
        }   
    }
    
    public Part lookupPart(int indexToLookUp)
    {
        if(allparts.size() - 1 < indexToLookUp)
            return null;
        else{
            return allparts.get(indexToLookUp);
        }
    }
    
    public void updatePart(int indexToUpdate)
    {
        
    }
    
    public ArrayList<Part> getAllParts(){
        return allparts;
    }
}
