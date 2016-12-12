/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.janroslan.loginux.usb.USBXUtils;
import javax.usb.UsbDevice;

/**
 *
 * @author jackjan
 */
public class G910 extends OrionKeyboard {

    public G910(UsbDevice device) {
        super(
                device,
                
                // The G910 Keyboard protocol
                new byte[]{0x11, (byte) 0xff, 0x0f, 0x5d},
                
                // Base address for logos
                new byte[]{0x11, (byte) 0xff, 0x0f, 0x3a, 0x00, 0x10, 0x00, 0x02}
        );

    }

    
    public static boolean isThisDevice(UsbDevice device) {
        try {
            if (USBXUtils.getDeviceName(device).contains("G910")) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

}
