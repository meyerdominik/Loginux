/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.usb;

import java.util.ArrayList;
import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceHandle;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 *
 * @author jackjan
 */
public class USBUtils {

    /**
     *
     * @param vendorId
     * @param productId
     * @return
     */
    public Device findDevice(short vendorId, short productId) {

        // Read the USB device list
        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(null, list);

        if (result < 0) {
            throw new LibUsbException("Unable to get device list", result);

        }

        try {

            // Iterate over all devices and scan for the right one
            for (Device device : list) {
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);
                if (result != LibUsb.SUCCESS) {
                    throw new LibUsbException("Unable to read device descriptor", result);
                }
                if (descriptor.idVendor() == vendorId && descriptor.idProduct() == productId) {
                    return device;
                }
            }
        } finally {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }

        // Device not found
        return null;
    }

    
    /**
     * Gets the name of a device. TODO implementing
     * @param device
     * @return 
     */
    public String getDeviceName(Device device) {
        DeviceDescriptor descriptor = new DeviceDescriptor();
        int result = LibUsb.getDeviceDescriptor(device, descriptor);
        
        if (result != LibUsb.SUCCESS) {
                    throw new LibUsbException("Unable to read device descriptor", result);
                }
        
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Device> listDevices() {
        ArrayList<Device> result = new ArrayList<>();

        // Read the USB device list
        DeviceList list = new DeviceList();
        int res = LibUsb.getDeviceList(null, list);

        if (res < 0) {
            throw new LibUsbException("Unable to get device list", res);

        }

        try {

            // Iterate over all devices and scan for the right one
            for (Device device : list) {

                DeviceDescriptor descriptor = new DeviceDescriptor();
                res = LibUsb.getDeviceDescriptor(device, descriptor);
                if (res != LibUsb.SUCCESS) {
                    throw new LibUsbException("Unable to read device descriptor", res);
                }
                result.add(device);
            }
        } finally {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }

        return result;
    }
}
