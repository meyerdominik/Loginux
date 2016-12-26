/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.usb;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;

/**
 *
 * @author jackjan
 */
public class USBXUtils {

    /**
     * Lists all available devices connected to the machine (UsbHostManager)
     *
     * @return
     */
    public static ArrayList<UsbDevice> listDevices() {
        ArrayList<UsbDevice> result = new ArrayList<>();

        try {
            UsbServices serv = UsbHostManager.getUsbServices();

            processDevice(serv.getRootUsbHub(), result);
        } catch (Exception ex) {
            System.out.println("test");
            ex.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @param name
     * @return
     */
    public static UsbDevice getDeviceByName(String name) throws UsbException, UnsupportedEncodingException {
        UsbDevice result = null;

        for (UsbDevice d : listDevices()) {
            if (getDeviceName(d).equals(name)) {
                result = d;
                break;
            }
        }

        return result;
    }

    private static void processDevice(UsbDevice device, ArrayList<UsbDevice> result) {
        // When device is a hub then process all child devices
        if (device.isUsbHub()) {
            final UsbHub hub = (UsbHub) device;
            for (UsbDevice child : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
                processDevice(child, result);
            }
        } // When device is not a hub then add it as result
        else {
            result.add(device);
        }
    }

    /**
     * Returns the full name (vendor + device name) of a UsbDevice
     *
     * @param device
     * @return
     * @throws UnsupportedEncodingException
     * @throws UsbException
     */
    public static String getDeviceName(UsbDevice device) throws UsbException, UnsupportedEncodingException {

        if (device != null) {
            // Read the string descriptor indices from the device descriptor.
            final UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            final byte iManufacturer = desc.iManufacturer();
            final byte iProduct = desc.iProduct();

            // If they are missing then ignore the device.
            if (iManufacturer == 0 || iProduct == 0) {
                return null;
            }

            // Return the device name
            return device.getString(iManufacturer) + " " + device.getString(iProduct);
        } else {

            return null;
        }
    }
}
