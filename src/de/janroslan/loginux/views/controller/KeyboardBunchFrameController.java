/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import de.janroslan.loginux.devices.G910;
import de.janroslan.loginux.devices.LogitechDevice;
import de.janroslan.loginux.devices.OrionKeyboard;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author jackjan
 */
public class KeyboardBunchFrameController implements Initializable {

    private boolean record;

    @FXML
    private JFXButton btnRecord;

    @FXML
    private Label lblText;

    @FXML
    private Label lblKeys;

    @FXML
    private JFXColorPicker colorPicker;

    private OrionKeyboard g910;

    private ArrayList<String> selKeys;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        record = false;

        // Init keyboard
        g910 = (OrionKeyboard)LogitechDevice.getAsLogiDevice(MainViewController.device);

        selKeys = new ArrayList<>();

    }

    @FXML
    public void onColorChanged(ActionEvent evt) {
        if (!selKeys.isEmpty()) {

            try {

                g910.setKey((byte) Math.round(colorPicker.getValue().getRed() * 255.0), (byte) Math.round(colorPicker.getValue().getGreen() * 255.0), (byte) Math.round(colorPicker.getValue().getBlue() * 255.0), selKeys);
            } catch (Exception ex) {

            }

        }
    }

    @FXML
    public void onBtnRecordClicked(ActionEvent evt) {
        if (!record) {
            record = true;
            btnRecord.setText("Stop record");
            lblText.setText("Click on the keys on your keyboard you want to colorize after recording");
            selKeys.clear();
            lblText.setText("");

            // 
            btnRecord.getParent().getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                if (!selKeys.contains(event.getCode().getName())) {
                    selKeys.add(event.getCode().getName());
                    lblKeys.setText(lblKeys.getText() + event.getCode().getName() + ", ");
                }
            });

        } else {
            record = false;
            btnRecord.setText("Record keys");
            lblText.setText("Now choose a color for your selected keys or select other keys");
        }
    }
}
