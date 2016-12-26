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
 *
 * @author jackjan
 */
public final class G510 extends OrionKeyboard {

    
    private final static String config = "g510.cfg";
    
    
    public G510(UsbDevice device, byte[] keyboardProtocol, byte[] logoBaseAddresses) {
        super(config,
                device,
                keyboardProtocol,
                logoBaseAddresses, null
        );
    }

    
    @Override
    public void resetDevice() {
        
    }
    
    
    public static boolean isThisDevice(UsbDevice device) {
        try {
            if (USBXUtils.getDeviceName(device).contains("G510")) {
                return true;
            }
        } finally {
            return false;
        }
    }
}
