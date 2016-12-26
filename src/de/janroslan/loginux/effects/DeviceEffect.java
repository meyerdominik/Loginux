/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.effects;



/**
 *
 * @author jackjan
 */
public abstract class DeviceEffect {
    
    private boolean running;
    private Thread thread;
    
    public DeviceEffect(Runnable r)
    {
        thread = new Thread(r);
        
    }
    
    
    public void start()
    {
        thread.start();
    }
    
    
    public void stop()
    {
        
    }
    
    
}
