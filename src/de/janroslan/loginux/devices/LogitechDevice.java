/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.devices;

import de.janroslan.loginux.profiles.DeviceProfile;
import de.janroslan.loginux.profiles.Profile;
import de.janroslan.loginux.profiles.ProfileManager;
import de.janroslan.loginux.usb.USBXUtils;
import de.timetoerror.jputils.conf.ConfigurationFile;
import java.util.ArrayList;
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

    private final String configName;

    /**
     *
     * @param config - The name of the config + extension
     * @param device
     */
    public LogitechDevice(String config, UsbDevice device) {
        this.configName = config;
        this.device = device;
        iface = null;
    }

    protected UsbDevice getDevice() {
        return device;
    }

    protected void openInterface() throws UsbException {
        if (iface != null) {
            throw new UsbClaimException("Interface already opened");

        }

        // Open interface to device
        UsbConfiguration configuration = getDevice().getActiveUsbConfiguration();
        iface = configuration.getUsbInterface((byte) 1);
        iface.claim((UsbInterface usbInterface) -> true);
    }

    /**
     *
     */
    public abstract void resetDevice();

    /**
     *
     * @param file
     */
    public abstract void applyConfig(ConfigurationFile file);

    /**
     * Apply an action on this device in its config
     *
     * @param key - The key on which this action should be saved in
     * @param value - The value for the key
     */
    public void applyActionInConfig(String key, String value) {
        Profile active = ProfileManager.getInstance().getActive();

        if (active != null) {
            DeviceProfile prof = active.getDeviceProfile(configName);
            if (prof != null) {
                prof.getConfiguration().setKey(key, value);
            }
        }
    }

    protected void closeInterface() throws UsbException {
        if (iface != null) {
            iface.release();
            iface = null;
        }
    }

    public static LogitechDevice getAsLogiDevice(UsbDevice dev) {
        LogitechDevice result = null;

        if (G910.isThisDevice(dev)) {
            result = new G910(dev);
        }

        return result;
    }

    public static ArrayList<LogitechDevice> listDevices() {
        ArrayList<LogitechDevice> result = new ArrayList<>();
        for (UsbDevice d : USBXUtils.listDevices()) {
            LogitechDevice ld = getAsLogiDevice(d);
            if (ld != null) {
                result.add(ld);
            }
        }

        return result;
    }

    public String getConfigName() {
        return configName;
    }

}
