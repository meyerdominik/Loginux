/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.janroslan.loginux.usb.USBXUtils;
import de.timetoerror.jputils.conf.ConfigurationFile;
import javax.usb.UsbDevice;

/**
 * Logitech Keyboards from the orion series which use the generic protocol (G410 Atlas Spectrum, G810 Orion Spectrum)
 * @author jackjan
 */
public class GenericOrionKeyboard extends OrionKeyboard {
    
    
    private static String config = "g810.cfg";
    
    
    public GenericOrionKeyboard(UsbDevice device) {
        super(config,
                device, 
                
                // comit base address
                new byte[] { 0x11,(byte)0xff,0x0c,0x5a},
                
                // Base addres for the logos on the keyboard
                new byte[] { 0x11,(byte)0xff,0x0c,0x3a, 0x00, 0x10,0x00,0x01},
                
                // Base Addresses
                null
        );
    }

   
    public static boolean isThisDevice(UsbDevice device) {
        try {
            if (USBXUtils.getDeviceName(device).contains("G810")) {
                return true;
            }
        } finally {
            return false;
        }
    }

    
    @Override
    public void resetDevice() {
        
    }

    
    @Override
    public void applyConfig(ConfigurationFile file) {
        
    }
    
}
