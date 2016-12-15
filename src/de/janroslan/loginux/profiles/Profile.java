/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.profiles;

import de.janroslan.loginux.devices.LogitechDevice;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jackjan
 */
public class Profile {

    private Path path;
    private String name;
    private ArrayList<DeviceProfile> devices;

    public Profile(Path p, String name) {
        this.path = p;
        this.name = name;
        devices = new ArrayList<>();
        
        try {
            Files.createDirectories(p);
        } catch (IOException ex) {
        }

        // Load device profiles
        try {
            Files.list(p).forEach(x -> {
                if (x.toString().endsWith(".cfg")) {
                    devices.add(new DeviceProfile(x));
                }
            });
        } catch (IOException ex) {

        }
    }

    public void addDeviceProfile(LogitechDevice device) {
        
        devices.add(new DeviceProfile(Paths.get(path + File.separator + device.getConfigName())));
    }

    public DeviceProfile getDeviceProfile(String cfgName) {
        for (DeviceProfile p : devices) {
            if (p.getFile().toString().endsWith(cfgName)) {
                return p;
            }
        }
        return null;
    }

    public Path getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
