package de.example.frank.bluetooth;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Master on 26.09.2016.
 */
public class CennectedThread extends Thread {
    private final BluetoothSocket bluetoothSocket;
    private final InputStream input;
    private final OutputStream output;

    public CennectedThread(BluetoothSocket bluetoothServerSocket) {
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        this.bluetoothSocket = bluetoothServerSocket;
        try {
            tmpIn = bluetoothServerSocket.getInputStream();
            tmpOut = bluetoothServerSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = tmpIn;
        output = tmpOut;
    }

    public void write(byte[] bytes) {
        try {
            output.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cancel(){
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
