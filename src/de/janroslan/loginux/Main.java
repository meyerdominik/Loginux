/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.loginux;

import de.janroslan.loginux.usb.USBUtils;
import de.timetoerror.jputils.jfx.FXUtils;
import java.io.UnsupportedEncodingException;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.usb.UsbException;
import org.usb4java.Context;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 *
 * @author jackjan
 */
public class Main extends Application {

    public static final short LOGITECH_VENDOR_ID = 0x046d;

    @Override
    public void start(Stage stage) throws Exception {

        FXUtils.openWindow(getClass().getResource("/de/janroslan/loginux/views/MainView.fxml"), null,"Loginux" , stage);
        
        stage.addEventFilter(KeyEvent.KEY_PRESSED, x -> {
            System.out.println(x.getCode());
        });

        
        
        

    }

    public static void main(final String[] args) throws InterruptedException, UnsupportedEncodingException, UsbException {

        // Low level implementation
        
        /*
        Context context = new Context();
        int result = LibUsb.init(context);
        if (result != LibUsb.SUCCESS) {
            System.out.println("Context init fail");
            throw new LibUsbException("Unable to initialize libusb.", result);
        }

        DeviceHandle handle = LibUsb.openDeviceWithVidPid(context, LOGITECH_VENDOR_ID, (short) G910.PRODUCT_ID);
        if (handle == null) {
            System.out.println("Handle null");
            return;

        }

        USBUtils.detachFromKernel(handle, 1);

        int re = LibUsb.claimInterface(handle, 1);
        if (re != LibUsb.SUCCESS) {
            System.out.println(LibUsb.strError(re));
            System.out.println("Claim interface ging nicht");
            return;
        }

        byte[] data = new byte[64];

        data[0] = 0x12;  // Base address
        data[1] = (byte) 0xff;  // Base address
        data[2] = 0x0f;  // Base address
        data[3] = 0x3d;  // Base address
        data[4] = 0x00;  // Base address
        data[5] = 0x01;  // Base address
        data[6] = 0x00;  // Base address
        data[7] = 0x0e;

        data[8] = 0x08;
        data[9] = (byte) 0xf0;
        data[10] = 0x00;
        data[11] = 0x00;

        for (int i = 12; i < data.length; i++) {
            data[i] = 0x00;
        }

        ByteBuffer b = ByteBuffer.allocateDirect(64);
        for (int i = 0; i < data.length; i++) {
            b.put(data[i]);
        }
        int res = LibUsb.controlTransfer(handle, (byte) 0x21, (byte) 0x09, (short) 0x0212, (short) 1, b, 2000);
        Thread.sleep(1000);

        if (res == LibUsb.ERROR_TIMEOUT || res == LibUsb.ERROR_PIPE || res == LibUsb.ERROR_NO_DEVICE) {
            System.out.println(LibUsb.strError(res) + res);
            System.out.println("Trans not okay");
            return;
        }

        // Commit
        byte[] commitData = new byte[20];
        commitData[0] = 0x11;
        commitData[1] = (byte) 0xff;
        commitData[2] = 0x0f;
        commitData[3] = 0x5d;

        for (int i = 4; i < 20; i++) {
            commitData[i] = 0x00;
        }

        ByteBuffer bu = ByteBuffer.allocateDirect(20);
        for (int i = 0; i < commitData.length; i++) {
            bu.put(commitData[i]);
        }
        int resu = LibUsb.controlTransfer(handle, (byte) 0x21, (byte) 0x09, (short) 0x0211, (short) 1, bu, 2000);
        Thread.sleep(1000);

        if (resu == LibUsb.ERROR_TIMEOUT || resu == LibUsb.ERROR_PIPE || resu == LibUsb.ERROR_NO_DEVICE) {
            System.out.println("Commit trans not okay");
        }

        int r = LibUsb.releaseInterface(handle, 1);
        if (r != LibUsb.SUCCESS) {
            System.out.println("Release interface failed");
        }

        USBUtils.attachToKernel(handle, 1);

        LibUsb.close(handle);
        LibUsb.exit(context);*/
        
        
        // USBX
        
        
        launch(args);
}}
