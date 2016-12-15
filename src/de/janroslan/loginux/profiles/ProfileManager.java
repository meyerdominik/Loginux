/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.profiles;

import de.janroslan.loginux.devices.LogitechDevice;
import de.timetoerror.jputils.CommonUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author jackjan
 */
public final class ProfileManager {

    private static ProfileManager man;
    private Profile current;

    private final Path profileDir;

    private ProfileManager() {
        profileDir = Paths.get(CommonUtils.getClassPathAsFile(ProfileManager.class).getParent() + File.separator + "profiles" + File.separator);
        try {
            Files.createDirectories(profileDir);
        } catch (IOException ex) {

        }
        
        current = null;
    }

    public static ProfileManager getInstance() {
        if (man == null) {
            man = new ProfileManager();
        }
        return man;
    }

    public Profile newProfile(String name, ArrayList<LogitechDevice> currentDevs) {
        Profile prof = new Profile(Paths.get(profileDir + File.separator + name), name);

        for(LogitechDevice d : currentDevs)
        {
            prof.addDeviceProfile(d);
        }
        
        current = prof;
        return prof;
    }

    /**
     * Lists all profiles available in the profiles folder
     *
     * @return
     */
    public ArrayList<Profile> listProfiles() {
        ArrayList<Profile> result = new ArrayList<>();

        try {
            Files.list(profileDir).forEach(x -> {
               if (Files.isDirectory(x))
               {
                   result.add(new Profile(Paths.get(profileDir + File.separator + x.getFileName()),x.getFileName().toString()));
               }
            });
        } catch (IOException ex) {
        }
        
        return result;
    }

    public Profile getActive() {
        return current;
    }

    public void deleteProfile(String name) {

    }

    public Profile loadProfile(String name) {
        Profile result = null;

        result = new Profile(Paths.get(profileDir + File.separator + name), name);
        
        current = result;
        return result;
    }

}
