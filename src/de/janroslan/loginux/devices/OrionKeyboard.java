/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.timetoerror.jputils.conf.ConfigurationFile;
import de.timetoerror.jputils.img.ColorUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbException;

/**
 * This class represents Logitech keyboard from the orion series (with per-key
 * RGB lighting) Current devices: G910 G810 G410 Spectrum
 *
 * @author jackjan
 */
public abstract class OrionKeyboard extends LEDLogitechKeyboard {

    // Keyboard protocol for the first 8 bytes in commit with default value
    private byte[] kbdProtocol = new byte[]{0x11, (byte) 0xff, 0x0c, 0x5a};

    private byte[] logoBaseAddresses = new byte[]{0x11, (byte) 0xff, 0x0c, 0x3a, 0x00, 0x10, 0x00, 0x01};

    private byte[] baseAddresses = new byte[]{0x12, (byte) 0xff, 0x0f, 0x3d, 0x00, 0x01, 0x00, 0x0e};

    private byte[] gKeyBaseAddresses = new byte[]{0x12, (byte) 0xff, 0x0f, 0x3b, 0x00, 0x04, 0x00, 0x09};

    public OrionKeyboard(String config, UsbDevice device, byte[] keyboardProtocol, byte[] logoBaseAddresses, byte[] baseAddresses) {
        super(config, device);

        if (keyboardProtocol != null) {
            this.kbdProtocol = keyboardProtocol;
        }
        if (logoBaseAddresses != null) {
            this.logoBaseAddresses = logoBaseAddresses;
        }
        if (baseAddresses != null) {
            this.baseAddresses = baseAddresses;
        }

        initKeys();
    }

    /**
     *
     */
    public void initKeys() {
        keys = new HashMap<String, Byte>() {
            {
                // put("logo", (byte) 0x01);
                // put("logo2", (byte) 0x02);
                // put("backlight", (byte) 0x01);
                // put("game", (byte) 0x02);

                //put("caps", (byte) 0x03);
                //put("scroll", (byte) 0x04);
                //put("num", (byte) 0x05);
                // put("next", (byte) 0xb5);
                // put("prev", (byte) 0xb6);
                // put("stop", (byte) 0xb7);
                // put("play", (byte) 0xcd);
                // put("mute", (byte) 0xe2);
                put("A", (byte) 0x04);
                put("B", (byte) 0x05);
                put("C", (byte) 0x06);
                put("D", (byte) 0x07);
                put("E", (byte) 0x08);
                put("F", (byte) 0x09);
                put("G", (byte) 0x0a);
                put("H", (byte) 0x0b);
                put("I", (byte) 0x0c);
                put("J", (byte) 0x0d);
                put("K", (byte) 0x0e);
                put("L", (byte) 0x0f);
                put("M", (byte) 0x10);
                put("N", (byte) 0x11);
                put("O", (byte) 0x12);
                put("P", (byte) 0x13);
                put("Q", (byte) 0x14);
                put("R", (byte) 0x15);
                put("S", (byte) 0x16);
                put("T", (byte) 0x17);
                put("U", (byte) 0x18);
                put("V", (byte) 0x19);
                put("W", (byte) 0x1a);
                put("X", (byte) 0x1b);
                put("Z", (byte) 0x1c);
                put("Y", (byte) 0x1d);
                put("1", (byte) 0x1e);
                put("2", (byte) 0x1f);
                put("3", (byte) 0x20);
                put("4", (byte) 0x21);
                put("5", (byte) 0x22);
                put("6", (byte) 0x23);
                put("7", (byte) 0x24);
                put("8", (byte) 0x25);
                put("9", (byte) 0x26);
                put("0", (byte) 0x27);
                put("Enter", (byte) 0x28);
                put("Esc", (byte) 0x29);
                put("Backspace", (byte) 0x2a);
                put("Tab", (byte) 0x2b);
                put("Space", (byte) 0x2c);
                put("Minus", (byte) 0x2d);
                put("equal", (byte) 0x2e);
                put("open_bracket", (byte) 0x2f);
                put("close_bracket", (byte) 0x30);
                put("backslash", (byte) 0x31);
                put("dollar", (byte) 0x32);
                put("semicolon", (byte) 0x33);
                put("quote", (byte) 0x34);
                put("tilde", (byte) 0x35);
                put("Comma", (byte) 0x36);
                put("Period", (byte) 0x37);
                put("slash", (byte) 0x38);
                put("Caps Lock", (byte) 0x39);
                put("F1", (byte) 0x3a);
                put("F2", (byte) 0x3b);
                put("F3", (byte) 0x3c);
                put("F4", (byte) 0x3d);
                put("F5", (byte) 0x3e);
                put("F6", (byte) 0x3f);
                put("F7", (byte) 0x40);
                put("F8", (byte) 0x41);
                put("F9", (byte) 0x42);
                put("F10", (byte) 0x43);
                put("F11", (byte) 0x44);
                put("F12", (byte) 0x45);
                put("print_screen", (byte) 0x46);
                put("Scroll Lock", (byte) 0x47);
                put("Pause", (byte) 0x48);
                put("Insert", (byte) 0x49);
                put("Home", (byte) 0x4a);
                put("Page Up", (byte) 0x4b);
                put("Delete", (byte) 0x4c);
                put("End", (byte) 0x4d);
                put("Page Down", (byte) 0x4e);
                put("Right", (byte) 0x4f);
                put("Left", (byte) 0x50);
                put("Down", (byte) 0x51);
                put("Up", (byte) 0x52);
                put("Num Lock", (byte) 0x53);
                put("Divide", (byte) 0x54);
                put("Multiply", (byte) 0x55);
                put("Subtract", (byte) 0x56);
                put("Add", (byte) 0x57);
                put("Num_Enter", (byte) 0x58);
                put("Numpad 1", (byte) 0x59);
                put("Numpad 2", (byte) 0x5a);
                put("Numpad 3", (byte) 0x5b);
                put("Numpad 4", (byte) 0x5c);
                put("Numpad 5", (byte) 0x5d);
                put("Numpad 6", (byte) 0x5e);
                put("Numpad 7", (byte) 0x5f);
                put("Numpad 8", (byte) 0x60);
                put("Numpad 9", (byte) 0x61);
                put("Numpad 0", (byte) 0x62);
                put("Separator", (byte) 0x63);
                put("intl_backslash", (byte) 0x64);
                put("menu", (byte) 0x65);
                put("Ctrl", (byte) 0xe0);
                put("Shift", (byte) 0xe1);
                put("Alt", (byte) 0xe2);
                put("Windows", (byte) 0xe3);
                put("ctrl_right", (byte) 0xe4);
                put("shift_right", (byte) 0xe5);
                put("alt_right", (byte) 0xe6);
                put("win_right", (byte) 0xe7);
            }
        };

    }

    @Override
    public void applyConfig(ConfigurationFile file) {
        if (file.getFileName().equals(getConfigName())) {

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

            for (HashMap<String, Byte> h : extraKeys) {
                for (Entry<String, Byte> k : h.entrySet()) {
                    setSingleKey(k.getKey(), k.getValue(), red, green, blue);

                }
                commit();
                closeInterface();
                openInterface();
            }

            for (Entry<String, Byte> k : keys.entrySet()) {
                setSingleKey(k.getKey(), k.getValue(), red, green, blue);
            }
            commit();

        } finally {
            closeInterface();
        }
    }

    /**
     *
     * @param red
     * @param green
     * @param blue
     * @param keyName
     * @throws UsbException
     */
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

                // Get Key from keys
                Byte s = keys.get(keyName[i]);

                // If no key found, check exraKeys
                if (s == null) {
                    for (HashMap<String, Byte> h : extraKeys) {
                        s = h.get(keyName[i]);
                        break;
                    }
                }

                if (s != null) {
                    setSingleKey(keyName[i], s, red[j], green[j], blue[j]);
                }

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

        if (key == 0 || keyName == null) {
            return;
        }

        byte[] data;
        byte[] currentAddresses = baseAddresses;

        // Logo keys
        if (keyName.equals("logo") || keyName.equals("logo2")) {
            data = new byte[20];
            currentAddresses = logoBaseAddresses;
        } else // GKeys
        if (keyName.matches("G\\d")) {
            data = new byte[64];
            currentAddresses = gKeyBaseAddresses;
        } else {
            data = new byte[64];
        }

        // Save this action in config (if one exist)
        applyActionInConfig(keyName, "" + ColorUtils.toIntColor(red, red, blue));

        // Apply base addresses
        for (int i = 0; i < currentAddresses.length; i++) {
            data[i] = currentAddresses[i];
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
