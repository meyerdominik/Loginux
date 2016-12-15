/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import java.util.HashMap;
import javax.usb.UsbDevice;

/**
 *
 * @author jackjan
 */
public abstract class LEDLogitechKeyboard extends LogitechDevice {

    protected HashMap<String, Byte> keys;

    public LEDLogitechKeyboard(String config, UsbDevice device) {
        super(config, device);

        initKeys();
    }

   

    public HashMap<String, Byte> getKeys()
    {
        return keys;
    }

    
    /**
     * 
     */
    public void initKeys() {
        keys = new HashMap<String, Byte>() {
            {
                put("logo", (byte) 0x01);
                put("logo2", (byte) 0x02);
                put("backlight", (byte) 0x01);
                put("game", (byte) 0x02);
                put("caps", (byte) 0x03);
                put("scroll", (byte) 0x04);
                put("num", (byte) 0x05);
                put("next", (byte) 0xb5);
                put("prev", (byte) 0xb6);
                put("stop", (byte) 0xb7);
                put("play", (byte) 0xcd);
                put("mute", (byte) 0xe2);
                put("g1", (byte) 0x01);
                put("g2", (byte) 0x02);
                put("g3", (byte) 0x03);
                put("g4", (byte) 0x04);
                put("g5", (byte) 0x05);
                put("g6", (byte) 0x06);
                put("g7", (byte) 0x07);
                put("g8", (byte) 0x08);
                put("g9", (byte) 0x09);
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
                put("n1", (byte) 0x1e);
                put("n2", (byte) 0x1f);
                put("n3", (byte) 0x20);
                put("n4", (byte) 0x21);
                put("n5", (byte) 0x22);
                put("n6", (byte) 0x23);
                put("n7", (byte) 0x24);
                put("n8", (byte) 0x25);
                put("n9", (byte) 0x26);
                put("n0", (byte) 0x27);
                put("enter", (byte) 0x28);
                put("esc", (byte) 0x29);
                put("backspace", (byte) 0x2a);
                put("tab", (byte) 0x2b);
                put("space", (byte) 0x2c);
                put("minus", (byte) 0x2d);
                put("equal", (byte) 0x2e);
                put("open_bracket", (byte) 0x2f);
                put("close_bracket", (byte) 0x30);
                put("backslash", (byte) 0x31);
                put("dollar", (byte) 0x32);
                put("semicolon", (byte) 0x33);
                put("quote", (byte) 0x34);
                put("tilde", (byte) 0x35);
                put("comma", (byte) 0x36);
                put("period", (byte) 0x37);
                put("slash", (byte) 0x38);
                put("caps_lock", (byte) 0x39);
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
                put("scroll_lock", (byte) 0x47);
                put("pause_break", (byte) 0x48);
                put("insert", (byte) 0x49);
                put("home", (byte) 0x4a);
                put("page_up", (byte) 0x4b);
                put("del", (byte) 0x4c);
                put("end", (byte) 0x4d);
                put("page_down", (byte) 0x4e);
                put("arrow_right", (byte) 0x4f);
                put("arrow_left", (byte) 0x50);
                put("arrow_bottom", (byte) 0x51);
                put("arrow_top", (byte) 0x52);
                put("num_lock", (byte) 0x53);
                put("num_slash", (byte) 0x54);
                put("num_asterisk", (byte) 0x55);
                put("num_minus", (byte) 0x56);
                put("num_plus", (byte) 0x57);
                put("num_enter", (byte) 0x58);
                put("num_1", (byte) 0x59);
                put("num_2", (byte) 0x5a);
                put("num_3", (byte) 0x5b);
                put("num_4", (byte) 0x5c);
                put("num_5", (byte) 0x5d);
                put("num_6", (byte) 0x5e);
                put("num_7", (byte) 0x5f);
                put("num_8", (byte) 0x60);
                put("num_9", (byte) 0x61);
                put("num_0", (byte) 0x62);
                put("num_dot", (byte) 0x63);
                put("intl_backslash", (byte) 0x64);
                put("menu", (byte) 0x65);
                put("ctrl_left", (byte) 0xe0);
                put("shift_left", (byte) 0xe1);
                put("alt_left", (byte) 0xe2);
                put("win_left", (byte) 0xe3);
                put("ctrl_right", (byte) 0xe4);
                put("shift_right", (byte) 0xe5);
                put("alt_right", (byte) 0xe6);
                put("win_right", (byte) 0xe7);
            }
        };

    }

}
