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
public abstract class Inhouse extends Part {
    private String companyName;
    
    public String getCompanyName(){
        return this.companyName;
    }
    
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
}
