/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.janroslan.loginux.devices.enums.OrionKey;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbException;

/**
 *
 * @author jackjan
 */
public abstract class OrionKeyboard extends LogitechDevice {

    private final byte[] kbdProtocol;
    private final byte[] logoBaseAddresses;

    public OrionKeyboard(UsbDevice device, byte[] keyboardProtocol, byte[] logoBaseAddresses) {
        super(device);
        this.kbdProtocol = keyboardProtocol;
        this.logoBaseAddresses = logoBaseAddresses;
    }

    /**
     * Colors all keys on the keyboard
     *
     * @param red
     * @param green
     * @param blue
     * @throws UsbException
     */
    public void setAllKeys(byte red, byte green, byte blue) throws UsbException {
        openInterface();
        try {
            for (OrionKey k : OrionKey.values()) {
                setSingleKey(k, red, green, blue);
            }
            commit();
        } finally {
            closeInterface();
        }
    }

    /**
     * Colors a single key on the keyboard
     *
     * @param key
     * @param red
     * @param green
     * @param blue
     * @throws UsbException
     */
    public void setKey(OrionKey key, byte red, byte green, byte blue) throws UsbException {
        openInterface();
        try {
            setSingleKey(key, red, green, blue);
            commit();
        } finally {
            closeInterface();
        }

    }

    /**
     * Sends a single key color request to the keyboard. Can only be executed if
     * the interface 1 was claimed by the application.
     *
     * @param key
     * @param red
     * @param green
     * @param blue
     * @throws UsbException
     */
    private void setSingleKey(OrionKey key, byte red, byte green, byte blue) throws UsbException {

        byte[] data;

        // The logo keys have special 20 byte packets
        if (key.equals(OrionKey.logo) || key.equals(OrionKey.logo2)) {
            data = new byte[20];

            // Key Adress group
            data[0] = logoBaseAddresses[0];  // Base address
            data[1] = logoBaseAddresses[1];  // Base address
            data[2] = logoBaseAddresses[2];  // Base address
            data[3] = logoBaseAddresses[3];  // Base address
            data[4] = logoBaseAddresses[4];  // Base address
            data[5] = logoBaseAddresses[5];  // Base address
            data[6] = logoBaseAddresses[6];  // Base address
            data[7] = logoBaseAddresses[7];
        } else {
            data = new byte[64];

            // Key Adress group
            data[0] = 0x12;  // Base address
            data[1] = (byte) 0xff;  // Base address
            data[2] = 0x0f;  // Base address
            data[3] = 0x3d;  // Base address
            data[4] = 0x00;  // Base address
            data[5] = 0x01;  // Base address
            data[6] = 0x00;  // Base address
            data[7] = 0x0e;
        }

        data[8] = (byte) key.getValue();
        data[9] = red;
        data[10] = green;
        data[11] = blue;

        for (int i = 12; i < data.length; i++) {
            data[i] = 0x00;
        }

        UsbControlIrp irp;

        // The logo keys have special packages
        if (data.length > 20) {
            irp = getDevice().createUsbControlIrp((byte) 0x21, (byte) 0x09, (short) 0x0212, (short) 1);
        } else {
            irp = getDevice().createUsbControlIrp((byte) 0x21, (byte) 0x09, (short) 0x0211, (short) 1);
        }
        irp.setData(data);
        getDevice().syncSubmit(irp);

    }

    /**
     * Adds a commit package to the keyboard so changes will be saved
     */
    private void commit() throws UsbException {
        // Commit
        byte[] commitData = new byte[20];
        commitData[0] = kbdProtocol[0];
        commitData[1] = kbdProtocol[1];
        commitData[2] = kbdProtocol[2];
        commitData[3] = kbdProtocol[3];

        for (int i = 4; i < 20; i++) {
            commitData[i] = 0x00;
        }

        UsbControlIrp i = getDevice().createUsbControlIrp((byte) 0x21, (byte) 0x09, (short) 0x0211, (short) 1);
        i.setData(commitData);
        getDevice().syncSubmit(i);
    }
}
