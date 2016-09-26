package de.example.frank.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;


public class ConnectThread extends Thread {
    private final static String LOG = ConnectThread.class.getSimpleName();

    private final BluetoothSocket bluetoothSocket;
    private final BluetoothDevice device;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */

    public ConnectThread(BluetoothDevice device) {
        this.device = device;
        BluetoothSocket tmp = null;
        try {
            tmp = device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
        } catch (IOException e) {
            Log.e(LOG, e.getMessage());
            e.printStackTrace();
        }
        bluetoothSocket = tmp;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        super.run();
        try {
            bluetoothSocket.connect();
        } catch (IOException e) {
            cancel();
            Log.e(LOG, e.getMessage());
            e.printStackTrace();
        }


    }

    public void cancel(){
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            Log.e(LOG, e.getMessage());
            e.printStackTrace();
        }
    }
}
