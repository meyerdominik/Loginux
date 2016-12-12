/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import javax.usb.UsbClaimException;
import javax.usb.UsbConfiguration;
import javax.usb.UsbDevice;
import javax.usb.UsbException;
import javax.usb.UsbInterface;

/**
 *
 * @author jackjan
 */
public abstract class LogitechDevice {

    private final UsbDevice device;

    private UsbInterface iface;

    public LogitechDevice(UsbDevice device) {
        this.device = device;
        iface = null;
    }

    protected UsbDevice getDevice() {
        return device;
    }

    protected void openInterface() throws UsbException {

        if (iface != null) {
            throw new UsbClaimException("Interface already claimed");
        }
        
        
        UsbConfiguration configuration = getDevice().getActiveUsbConfiguration();
        iface = configuration.getUsbInterface((byte) 1);
        iface.claim((UsbInterface usbInterface) -> true);
    }

    protected void closeInterface() throws UsbException {
        if (iface != null) {
            iface.release();
            iface = null;
        }
    }
    
    
}
