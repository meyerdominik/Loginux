/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.janroslan.loginux.usb.USBXUtils;
import java.util.HashMap;
import javax.usb.UsbDevice;
import javax.usb.UsbException;

/**
 *
 * @author jackjan
 */
public final class G910 extends OrionKeyboard {

    private final static String config = "g910.cfg";

    public G910(UsbDevice device) {
        super(config,
                device,
                // The G910 Keyboard protocol for commit
                new byte[]{0x11, (byte) 0xff, 0x0f, 0x5d},
                // Base address for logos
                new byte[]{0x11, (byte) 0xff, 0x0f, 0x3a, 0x00, 0x10, 0x00, 0x02},
                // Base Address
                null
        );

        addExtraKeys();

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

    @Override
    public void resetDevice() {
        try {
            setAllKeys((byte) 0, (byte) 128, (byte) 128);
        } catch (UsbException ex) {

        }
    }

    public final void addExtraKeys() {
        HashMap<String, Byte> extra1 = new HashMap<String, Byte>() {
            {
                put("logo", (byte) 0x01);
                put("logo2", (byte) 0x02);
            }
        };

        HashMap<String, Byte> extra2 = new HashMap<String, Byte>() {
            {
                put("G1", (byte) 0x01);
                put("G2", (byte) 0x02);
                put("G3", (byte) 0x03);
                put("G4", (byte) 0x04);
                put("G5", (byte) 0x05);
                put("G6", (byte) 0x06);
                put("G7", (byte) 0x07);
                put("G8", (byte) 0x08);
                put("G9", (byte) 0x09);
            }
        };

        extraKeys.add(extra1);
        extraKeys.add(extra2);
    }

}
