/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import java.util.ArrayList;
import java.util.HashMap;
import javax.usb.UsbDevice;

/**
 *
 * @author jackjan
 */
public abstract class LEDLogitechKeyboard extends LogitechDevice {

    
    protected HashMap<String, Byte> keys;
    protected ArrayList<HashMap<String, Byte>> extraKeys;

    
    public LEDLogitechKeyboard(String config, UsbDevice device) {
        super(config, device);


        extraKeys = new ArrayList<>();
    }

   

    public HashMap<String, Byte> getKeys()
    {
        return keys;
    }

    
    

    
    public ArrayList<HashMap<String, Byte>> getExtraKeys() {
        return extraKeys;
    }
    
    

    
}
