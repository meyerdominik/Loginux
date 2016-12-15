/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import de.timetoerror.jputils.jfx.AdvancedController;
import de.timetoerror.jputils.jfx.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author jackjan
 */
public class G910FrameController implements Initializable, AdvancedController {

    @FXML
    private ToggleButton tgBtnRGB;
    @FXML
    private HBox hBoxContent;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void onInitFinished() {
        
        // Load RGB frame
        hBoxContent.getChildren().add(FXUtils.loadFXML(getClass().getResource("/de/janroslan/loginux/views/frames/RGBMethodChooserFrame.fxml")));
        
    }

    @Override
    public void onUnload() {
        
    }
    
}
