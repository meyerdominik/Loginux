/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.profiles;

import de.timetoerror.jputils.conf.ConfigurationFile;
import java.nio.file.Path;

/**
 *
 * @author jackjan
 */
public class DeviceProfile {
    
    private Path file;
    private ConfigurationFile conf;
    
    public DeviceProfile(Path file)
    {
        this.file = file;
        conf = new ConfigurationFile(file,"");
    }

    
    
    
    
    public ConfigurationFile getConfiguration()
    {
        return conf;
    }

    public Path getFile() {
        return file;
    }
    
    
}
