package de.example.frank.bluetooth;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

/**
 * Created by Master on 26.09.2016.
 */

public class AcceptTread extends Thread {
    private final BluetoothServerSocket serverSocket;


    public AcceptTread(BluetoothServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        super.run();
        BluetoothSocket bluetoothServerSocket = null;
        while (true) {
            try {
                bluetoothServerSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(bluetoothServerSocket != null) {
                new CennectedThread(bluetoothServerSocket);
            }
        }
    }
}
