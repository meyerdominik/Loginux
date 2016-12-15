/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.timetoerror.jputils.img.ColorUtils;
import java.util.ArrayList;
import java.util.Map.Entry;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbException;

/**
 *
 * @author jackjan
 */
public abstract class OrionKeyboard extends LEDLogitechKeyboard {

    private final byte[] kbdProtocol;
    private final byte[] logoBaseAddresses;

    public OrionKeyboard(String config, UsbDevice device, byte[] keyboardProtocol, byte[] logoBaseAddresses) {
        super(config, device);
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
            for (Entry<String, Byte> k : keys.entrySet()) {
                setSingleKey(k.getKey(), k.getValue(), red, green, blue);
            }
            commit();
        } finally {
            closeInterface();
        }
    }

    public void setKey(byte red, byte green, byte blue, String... keyName) throws UsbException {

        setKey(new byte[]{red}, new byte[]{green}, new byte[]{blue}, keyName);
    }

    /**
     * Colors a single key on the keyboard
     *
     * @param red
     * @param green
     * @param keyName
     * @param blue
     * @throws UsbException
     */
    public void setKey(byte[] red, byte[] green, byte[] blue, String... keyName) throws UsbException {
        openInterface();
        try {
            for (int i = 0, j = 0; i < keyName.length; i++) {
                setSingleKey(keyName[i], keys.get(keyName[i]), red[j], green[j], blue[j]);

                if (j != red.length - 1) {
                    j++;
                }
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
     * @param keyName
     * @param blue
     * @throws UsbException
     */
    public void setKey(byte red, byte green, byte blue, ArrayList<String> keyName) throws UsbException {

        setKey(new byte[]{red}, new byte[]{green}, new byte[]{blue}, keyName.toArray(new String[keyName.size()]));
    }

    /**
     * Colors a single key on the keyboard
     *
     * @param key
     * @param red
     * @param green
     * @param keyName
     * @param blue
     * @throws UsbException
     */
    public void setKey(byte[] red, byte[] green, byte[] blue, ArrayList<String> keyName) throws UsbException {

        setKey(red, green, blue, keyName.toArray(new String[keyName.size()]));
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
    private void setSingleKey(String keyName, byte key, byte red, byte green, byte blue) throws UsbException {

        byte[] data;

        // Save this action in config (if one exist)
        applyActionInConfig(keyName, "" + ColorUtils.toIntColor(red, red, blue));

        // The logo keys have special 20 byte packets
        if (keyName.equals("logo") || keyName == "logo2") {
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

        data[8] = key;
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
