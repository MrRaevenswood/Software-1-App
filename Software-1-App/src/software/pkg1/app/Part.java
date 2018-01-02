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
public abstract class Part {
    protected int partID;
    protected String name; 
    protected double price;
    protected int inStock;
    protected int min;
    protected int max;
    
    abstract void setName(String name);
    abstract String getName();
    abstract void setPrice(double price);
    abstract double getPrice();
    abstract void setInStock(int amtInStock);
    abstract int getInStock();
    abstract void setMin(int min);
    abstract int getMin();
    abstract void setMax(int max);
    abstract int getMax();
    abstract void setPartID(int partId);
    abstract int getPartID();
}
