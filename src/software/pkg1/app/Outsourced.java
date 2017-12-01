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
public abstract class Outsourced extends Part {
    private int machineID;
    
    public void setMachineID(int machineID)
    {
        this.machineID = machineID;
    }
    
    public int getMachineID(){
        return machineID;
    }
}
