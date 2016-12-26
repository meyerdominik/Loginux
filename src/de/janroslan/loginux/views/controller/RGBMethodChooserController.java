/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import de.janroslan.loginux.devices.G910;
import de.janroslan.loginux.devices.OrionKeyboard;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.timetoerror.jputils.jfx.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.usb.UsbException;

/**
 * FXML Controller class
 *
 * @author jackjan
 */
public class RGBMethodChooserController implements Initializable {

    @FXML
    private AnchorPane pane;
    
    
    @FXML
    private FontAwesomeIconView btnList;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void onBtnVirtualClicked(MouseEvent evt)
    {
        try {
            OrionKeyboard g910 = new G910(MainViewController.device);
            g910.setAllKeys((byte)120, (byte)0, (byte)0);
        } catch (UsbException ex) {
            Logger.getLogger(RGBMethodChooserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    public void onBtnBunchClicked(MouseEvent evt)
    {
        Pane parent = (Pane)FXUtils.loadFXML(getClass().getResource("/de/janroslan/loginux/views/frames/KeyboardBunchFrame.fxml"));
        
        
        parent.setPrefHeight(Integer.MAX_VALUE);
        parent.setPrefWidth(Integer.MAX_VALUE);
        ((Pane)pane.getParent()).getChildren().setAll(parent);
    }
    @FXML
    public void onBtnListClicked(MouseEvent evt)
    {
        Pane parent = (Pane)FXUtils.loadFXML(getClass().getResource("/de/janroslan/loginux/views/frames/KeyboardRGBListFrame.fxml"));
        parent.setPrefHeight(Integer.MAX_VALUE);
        parent.setPrefWidth(Integer.MAX_VALUE);
        ((Pane)pane.getParent()).getChildren().setAll(parent);
        
    }
    
    
    
}
