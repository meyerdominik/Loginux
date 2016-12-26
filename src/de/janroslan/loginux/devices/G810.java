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
public final class G810 extends OrionKeyboard{

    private final static String config = "g810.cfg";
    
    public G810(UsbDevice device, byte[] keyboardProtocol, byte[] logoBaseAddresses) {
        super(config,
                device,
                keyboardProtocol,
                logoBaseAddresses,
                null);
    }

    @Override
    public void resetDevice() {
        
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
    
}
