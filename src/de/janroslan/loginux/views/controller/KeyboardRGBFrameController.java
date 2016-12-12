/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import de.janroslan.loginux.devices.G910;
import de.janroslan.loginux.devices.enums.OrionKey;
import de.timetoerror.jputils.jfx.AdvancedController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javax.usb.UsbException;

/**
 * FXML Controller class
 *
 * @author jackjan
 */
public class KeyboardRGBFrameController implements Initializable, AdvancedController {

    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXListView listKeys;
    @FXML
    private JFXColorPicker colorPickerRGB;
    @FXML
    private JFXButton btnSetColor;
    
    private G910 g910;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void onInitFinished() {
        
        // Populate list with keysSce
        for(OrionKey k : OrionKey.values())
        {
            listKeys.getItems().add(k.name());
        }
        
        // Init keyboard
       g910 = new G910(MainViewController.device);
    }

    @Override
    public void onUnload() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @FXML
    public void onColorChanged(ActionEvent evt)
    {
        
    }
    
    @FXML
    public void onBtnSetColorClicked(ActionEvent evt)
    {
        try {
            g910.setKey(OrionKey.valueOf(listKeys.getSelectionModel().getSelectedItem().toString()), (byte)Math.round(colorPickerRGB.getValue().getRed() * 255.0), (byte)Math.round(colorPickerRGB.getValue().getGreen() * 255.0), (byte)Math.round(colorPickerRGB.getValue().getBlue() * 255.0));
        } catch (UsbException ex) {
           
        }
    }
    
    
    @FXML
    public void ontxtSearchPressed(ActionEvent evt)
    {
        
    }
    
}
