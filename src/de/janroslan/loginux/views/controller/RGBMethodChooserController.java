/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux.views.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.timetoerror.jputils.jfx.FXUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void onBtnVirtualClicked(MouseEvent evt)
    {
        
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
        Pane parent = (Pane)FXUtils.loadFXML(getClass().getResource("/de/janroslan/loginux/views/frames/KeyboardRGBFrame.fxml"));
        //parent.setPrefHeight(Double.MAX_VALUE);
        //parent.setPrefWidth(Double.MAX_VALUE);
        pane.getChildren().setAll(parent);
        
    }
    
    
    
}
