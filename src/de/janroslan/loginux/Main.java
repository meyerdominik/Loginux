/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux;

import de.timetoerror.jputils.jfx.FXUtils;
import java.io.UnsupportedEncodingException;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.usb.UsbException;

/**
 *
 * @author jackjan
 */
public class Main extends Application {

    public static final short LOGITECH_VENDOR_ID = 0x046d;

    @Override
    public void start(Stage stage) throws Exception {

        FXUtils.openWindow(getClass().getResource("/de/janroslan/loginux/views/MainView.fxml"), null, "Loginux", stage);

        //stage.addEventFilter(KeyEvent.KEY_PRESSED, x -> {
         //   System.out.println(x.getCode());
        //});

    }

    public static void main(final String[] args) throws InterruptedException, UnsupportedEncodingException, UsbException {
        launch(args);
    }
}
