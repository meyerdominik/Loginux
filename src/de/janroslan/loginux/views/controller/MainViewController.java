/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import de.janroslan.loginux.devices.G910;
import de.janroslan.loginux.usb.USBXUtils;
import de.timetoerror.jputils.jfx.AdvancedController;
import de.timetoerror.jputils.jfx.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javax.usb.UsbDevice;

/**
 *
 * @author jackjan
 */
public class MainViewController implements Initializable, AdvancedController {

    @FXML
    private Label label;

    @FXML
    private ComboBox cmbBoxDevices;
    
    @FXML
    private HBox hBoxContent;
    
    public static UsbDevice device;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void onInitFinished() {

        /// Populate Device chooser
        populateDevices();

    }

    private void populateDevices() {
        for (UsbDevice d : USBXUtils.listDevices()) {
            try {
                if (USBXUtils.getDeviceName(d).contains("Logitech")) {
                    cmbBoxDevices.getItems().add(USBXUtils.getDeviceName(d));
                }
            } catch (Exception ex) {

            }
        }
    }

    
    @Override
    public void onUnload() {

    }
    
    @FXML
    public void onDeviceChanged(ActionEvent evt)
    {
        try {
            device = USBXUtils.getDeviceByName(cmbBoxDevices.getSelectionModel().getSelectedItem().toString());
            
            if (device != null && G910.isThisDevice(device))
            {
                
                hBoxContent.getChildren().add(FXUtils.loadFXML(getClass().getResource("/de/janroslan/loginux/views/frames/G910Frame.fxml")));
                
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Error hier");
        }
        
    }

}
