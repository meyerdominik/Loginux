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
import de.janroslan.loginux.devices.LogitechDevice;
import de.janroslan.loginux.devices.OrionKeyboard;
import de.timetoerror.jputils.jfx.AdvancedController;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML
    private JFXColorPicker colorPickerAll;

    private OrionKeyboard keyboard;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Init keyboard
        keyboard = (OrionKeyboard) LogitechDevice.getAsLogiDevice(MainViewController.device);
    }

    @Override
    public void onInitFinished() {

        // Populate list with keys
        for (Entry<String, Byte> k : keyboard.getKeys().entrySet()) {
            listKeys.getItems().add(k.getKey());
        }

        // Populate list with extra keys
        for (HashMap<String, Byte> h : keyboard.getExtraKeys()) {
            for (Entry<String, Byte> k : h.entrySet()) {
                listKeys.getItems().add(k.getKey());
            }
        }

    }

    @Override
    public void onUnload() {

    }

    @FXML
    public void onBtnSetColorAllClicked(ActionEvent evt)
    {
        try {
            keyboard.setAllKeys((byte) Math.round(colorPickerAll.getValue().getRed() * 255.0), (byte) Math.round(colorPickerAll.getValue().getGreen() * 255.0), (byte) Math.round(colorPickerAll.getValue().getBlue() * 255.0));
        } catch (UsbException ex) {
            
        }
    }
    
    
    @FXML
    public void onColorChanged(ActionEvent evt) {

    }

    @FXML
    public void onBtnSetColorClicked(ActionEvent evt) throws UsbException {

        keyboard.setKey((byte) Math.round(colorPickerRGB.getValue().getRed() * 255.0), (byte) Math.round(colorPickerRGB.getValue().getGreen() * 255.0), (byte) Math.round(colorPickerRGB.getValue().getBlue() * 255.0), listKeys.getSelectionModel().getSelectedItem().toString());

    }

    @FXML
    public void ontxtSearchPressed(ActionEvent evt) {

    }

}
