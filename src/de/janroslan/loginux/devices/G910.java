/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.janroslan.loginux.usb.USBXUtils;
import de.timetoerror.jputils.conf.ConfigurationFile;
import de.timetoerror.jputils.img.ColorUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.usb.UsbDevice;
import javax.usb.UsbException;

/**
 *
 * @author jackjan
 */
public class G910 extends OrionKeyboard {

    private final static String config = "g910.cfg";

    public G910(UsbDevice device) {
        super(config,
                device,
                // The G910 Keyboard protocol for commit
                new byte[]{0x11, (byte) 0xff, 0x0f, 0x5d},
                // Base address for logos
                new byte[]{0x11, (byte) 0xff, 0x0f, 0x3a, 0x00, 0x10, 0x00, 0x02}
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

    /**
     *
     * @param file
     */
    @Override
    public void applyConfig(ConfigurationFile file) {

        ArrayList<String> keys = new ArrayList<>();

        int size = file.getInternalProperties().entrySet().size();

        byte[] red = new byte[size];
        byte[] green = new byte[size];
        byte[] blue = new byte[size];

        for (Entry<Object, Object> entry : file.getInternalProperties().entrySet()) {

            int value = Integer.parseInt((String) entry.getValue());

            for (int i = 0; i < size; i++) {
                keys.add((String) entry.getKey());

                red[i] = ColorUtils.red(value);
                green[i] = ColorUtils.green(value);
                blue[i] = ColorUtils.blue(value);
            }

        }

        try {
            setKey(red, green, blue, keys);
        } catch (UsbException ex) {

        }
    }

    public void addExtraKeys() {
        HashMap<String, Byte> extra = new HashMap<String, Byte>() {
            {
                put("logo", (byte) 0x01);
                put("logo2", (byte) 0x02);
            }
        };
        
        keys.putAll(extra);
    }

}
