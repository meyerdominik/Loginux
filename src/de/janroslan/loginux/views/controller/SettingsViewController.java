/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.janroslan.loginux.devices.LogitechDevice;
import de.janroslan.loginux.profiles.Profile;
import de.janroslan.loginux.profiles.ProfileManager;
import de.janroslan.loginux.usb.USBXUtils;
import de.timetoerror.jputils.conf.Configuration;
import de.timetoerror.jputils.jfx.AdvancedController;
import de.timetoerror.jputils.jfx.FXUtils;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.usb.UsbConfiguration;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;
import javax.usb.UsbInterface;
import javax.usb.UsbNotActiveException;
import javax.xml.bind.DatatypeConverter;

/**
 * FXML Controller class
 *
 * @author jackjan
 */
public class SettingsViewController implements Initializable, AdvancedController {

    @FXML
    private JFXCheckBox btnStartup;
    @FXML
    private JFXComboBox cmbBoxStartup;
    @FXML
    private JFXTextArea txtDebugMsg;
    @FXML
    private JFXComboBox cmbBoxDebugDevices;
    @FXML
    private JFXTextField txtWValue;

    private static final String KEY_STARTUP = "startupprofile";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void onInitFinished() {

        // Populate profiles
        populateProfiles();

        // Populate debug devices
        populateDevices();

        // 
        if (Configuration.getInstance(false).keyAvailable(KEY_STARTUP)) {
            btnStartup.setSelected(true);
            cmbBoxStartup.setDisable(false);
            cmbBoxStartup.getSelectionModel().select(Configuration.getInstance(false).getKey(KEY_STARTUP));
        }
    }

    @Override
    public void onUnload() {

    }

    public void populateProfiles() {
        ArrayList<Profile> profiles = ProfileManager.getInstance().listProfiles();

        for (Profile p : profiles) {
            cmbBoxStartup.getItems().add(p.getName());
        }
    }

    public void populateDevices() {
        for (UsbDevice d : USBXUtils.listDevices()) {
            try {
                if (USBXUtils.getDeviceName(d).contains("Logitech")) {
                    cmbBoxDebugDevices.getItems().add(USBXUtils.getDeviceName(d));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void onBtnDebugClicked() {
        txtDebugMsg.setDisable(false);
    }

    @FXML
    public void onBtnDebugSendClicked(ActionEvent evt) {
        UsbInterface iface = null;
        try {
            UsbDevice dev = USBXUtils.getDeviceByName(cmbBoxDebugDevices.getSelectionModel().getSelectedItem().toString());

            /////// Open interface 
            UsbConfiguration configuration = dev.getActiveUsbConfiguration();
            iface = configuration.getUsbInterface((byte) 1);
            iface.claim((UsbInterface usbInterface) -> true);

            /////// Parse bytes for controll request
            ArrayList<Byte> bytes = new ArrayList<>();
            String msg = txtDebugMsg.getText();
            String wValue = txtWValue.getText();
            if (msg != null && !msg.equals("")) {
                Pattern p = Pattern.compile("\\S\\S");
                Matcher m = p.matcher(msg);
                System.out.println("Start matching");
                while (m.find()) {
                    String s = m.group();
                    short sh = Short.parseShort(m.group(), 16);
                    bytes.add((byte) sh);
                    System.out.println((byte)sh);
                }
                UsbControlIrp irp = dev.createUsbControlIrp((byte) 0x21, (byte) 0x09, Short.parseShort(wValue,16), (short) 1);

                // Convert ArrayList<Byte> to byte[]
                byte[] byteArr = new byte[bytes.size()];
                for (int i = 0; i < bytes.size(); i++) {
                    byteArr[i] = bytes.get(i);
                }

                irp.setData(byteArr);
                dev.syncSubmit(irp);
            }

        } catch (UsbException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } finally {
            /////// Close interface
            if (iface != null) {
                try {
                    iface.release();
                } catch (UsbException | UsbNotActiveException | UsbDisconnectedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void onBtnBackClicked(MouseEvent evt) {
        FXUtils.openWindow(getClass().getResource("/de/janroslan/loginux/views/MainView.fxml"), null, "Loginux", (Stage) btnStartup.getScene().getWindow());
    }

    @FXML
    public void onStartupProfileChanged(ActionEvent evt) {
        Configuration.getInstance(false).setKey(KEY_STARTUP, cmbBoxStartup.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    public void onStartupProfileToggled(ActionEvent evt) {
        if (btnStartup.isSelected()) {
            cmbBoxStartup.setDisable(false);
        } else {
            cmbBoxStartup.setDisable(true);
        }
    }

}
