/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.janroslan.loginux.devices.G910;
import de.janroslan.loginux.devices.LogitechDevice;
import de.janroslan.loginux.profiles.DeviceProfile;
import de.janroslan.loginux.profiles.Profile;
import de.janroslan.loginux.profiles.ProfileManager;
import de.janroslan.loginux.usb.USBXUtils;
import de.timetoerror.jputils.jfx.AdvancedController;
import de.timetoerror.jputils.jfx.FXUtils;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.usb.UsbDevice;
import javax.usb.UsbException;

/**
 *
 * @author jackjan
 */
public class MainViewController implements Initializable, AdvancedController {

    @FXML
    private Label label;

    @FXML
    private JFXComboBox cmbBoxDevices;

    @FXML
    private HBox hBoxContent;

    @FXML
    private JFXComboBox cmbBoxProfile;

    @FXML
    private JFXButton btnLoadProfile;

    @FXML
    private JFXButton btnDeleteProfile;

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

        // Populate Device chooser
        populateDevices();

        // Populate Profile chooser
        populateProfiles();
    }

    @FXML
    public void onBtnNewProfileClicked(ActionEvent evt) {

        TextInputDialog dia = new TextInputDialog();
        dia.setTitle("New profile");
        dia.setHeaderText("Choose a name for your new profile");
        Optional<String> result = dia.showAndWait();

        if (result.isPresent()) {
            Profile p = ProfileManager.getInstance().newProfile(result.get(), LogitechDevice.listDevices());
            
            cmbBoxProfile.getItems().add(result.get());
            cmbBoxProfile.getSelectionModel().selectLast();
        }
        
        

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

    public void populateProfiles() {

        ArrayList<Profile> profiles = ProfileManager.getInstance().listProfiles();

        for (Profile p : profiles) {
            cmbBoxProfile.getItems().add(p.getName());
        }
    }

    @FXML
    public void onDeviceChanged(ActionEvent evt) {
        try {
            device = USBXUtils.getDeviceByName(cmbBoxDevices.getSelectionModel().getSelectedItem().toString());

            if (device != null) {
                Pane p = null;
                if (G910.isThisDevice(device)) {
                    p = (Pane) FXUtils.loadFXML(getClass().getResource("/de/janroslan/loginux/views/frames/G910Frame.fxml"));

                }

                if (p != null) {
                    p.setPrefHeight(Integer.MAX_VALUE);
                    p.setPrefWidth(Integer.MAX_VALUE);
                    hBoxContent.getChildren().add(p);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error hier");
        }

    }

    /**
     * Gets called when the user changes the selected profile in the ComboBox
     *
     * @param evt
     */
    @FXML
    public void onProfileChanged(ActionEvent evt) {

        Profile p = ProfileManager.getInstance().loadProfile((String) cmbBoxProfile.getSelectionModel().getSelectedItem());

        for (Iterator it = cmbBoxDevices.getItems().iterator(); it.hasNext();) {
            String s = it.next().toString();
            try {
                UsbDevice dev = USBXUtils.getDeviceByName(s);

                LogitechDevice logi = LogitechDevice.getAsLogiDevice(dev);
                if (logi != null) {
                    DeviceProfile dp = ProfileManager.getInstance().getActive().getDeviceProfile(logi.getConfigName());
                    if (dp != null) {
                        logi.applyConfig(dp.getConfiguration());
                    }
                }

            } catch (UsbException ex) {

            } catch (UnsupportedEncodingException ex) {

            }
        }
    }

}
