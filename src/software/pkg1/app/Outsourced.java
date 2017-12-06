/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.pkg1.app;

/**
 *
 * @author rodrigmi
 */
public class Outsourced extends Part {
    private String companyName;
      
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    public String getComapnyName(){
        return companyName;
    }

    @Override
    void setName(String name) {
        this.name = name; 
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    void setPrice(double price) {
        this.price = price;
    }

    @Override
    double getPrice() {
        return this.price;
    }

    @Override
    void setInStock(int amtInStock) {
        this.inStock = amtInStock;
    }

    @Override
    int getInStock() {
        return this.inStock;
    }

    @Override
    void setMin(int min) {
        this.min = min;
    }

    @Override
    int getMin() {
        return this.min;
    }

    @Override
    void setMax(int max) {
        this.max = max;
    }

    @Override
    int getMax() {
        return this.max;
    }

    @Override
    void setPartID(int partId) {
        this.partID = partID;
    }

    @Override
    int getPartID() {
       return this.partID; 
    }
}
