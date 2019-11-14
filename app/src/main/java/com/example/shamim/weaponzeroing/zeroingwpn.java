package com.example.shamim.weaponzeroing;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class zeroingwpn extends AppCompatActivity {

    public static final int REQUEST_ENABLE_BT = 1;    //bluetooth
    ListView lv_paired_devices;    //bluetooth
    Set<BluetoothDevice> set_pairedDevices;    //bluetooth
    ArrayAdapter adapter_paired_devices;    //bluetooth
    BluetoothAdapter bluetoothAdapter;    //bluetooth
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");    //bluetooth
    public static final int MESSAGE_READ = 0;    //bluetooth
    public static final int MESSAGE_WRITE = 1;    //bluetooth
    public static final int CONNECTING = 2;    //bluetooth
    public static final int CONNECTED = 3;    //bluetooth
    public static final int NO_SOCKET_FOUND = 4;    //bluetooth
    String bluetooth_message = "00";    //bluetooth

    TextView horizontal_Error, vertical_Error;
    TextView t_test;
    EditText editText_hor, editText_ver;
    Button b_start, b_start2;


    float f_vertical, f_horizontal;
    int v_rotation = 0, h_rotation = 0;

    ConnectedThread connectedThread;


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg_type) {
            super.handleMessage(msg_type);

            switch (msg_type.what) {
                case MESSAGE_READ:

                    byte[] readbuf = (byte[]) msg_type.obj;
                    String string_recieved = new String(readbuf);

                    //do some task based on recieved string

                    break;
                case MESSAGE_WRITE:

                    if (msg_type.obj != null) {
                        ConnectedThread connectedThread = new ConnectedThread((BluetoothSocket) msg_type.obj);
                        connectedThread.write(bluetooth_message.getBytes());

                    }
                    break;

                case CONNECTED:
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    break;

                case CONNECTING:
                    Toast.makeText(getApplicationContext(), "Connecting...", Toast.LENGTH_SHORT).show();
                    break;

                case NO_SOCKET_FOUND:
                    Toast.makeText(getApplicationContext(), "No socket found", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeroingwpn);
        initialize_layout();
        initialize_bluetooth();
        start_accepting_connection();
        initialize_clicks();


        vertical_Error = (TextView) findViewById(R.id.vertical_Error);
        horizontal_Error = (TextView) findViewById(R.id.horizontal_Error);
        t_test = (TextView) findViewById(R.id.t_test);
        editText_hor = (EditText) findViewById(R.id.editText_hor);
        editText_ver = (EditText) findViewById(R.id.editText_ver);
        b_start = (Button) findViewById(R.id.b_start);
        b_start2 = (Button) findViewById(R.id.b_start2);

        horizontal_Error.setText("H Error: " + getIntent().getStringExtra("pass_h_error"));

        vertical_Error.setText("Vertical Error: " + getIntent().getStringExtra("pass_v_error"));
        f_vertical = Float.parseFloat(getIntent().getStringExtra("pass_v_error"));
        f_horizontal = Float.parseFloat(getIntent().getStringExtra("pass_h_error"));


        h_rotation = Math.round(f_horizontal * 41 / 21);
        v_rotation = Math.round(f_vertical * 41 / 16);


        t_test.setText("" + h_rotation + " " + v_rotation);


        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passCalculatedData();
            }
        });
        b_start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passModifieData();
            }
        });


    }


    public void passCalculatedData() {
        Toast.makeText(getApplicationContext(), "Passing Data to Zeroing Tools ", Toast.LENGTH_SHORT).show();
        String temp = (t_test.getText().toString());
        connectedThread.write(temp.getBytes());
    }

    public void passModifieData() {
        Toast.makeText(getApplicationContext(), "Passing Data to Zeroing Tools ", Toast.LENGTH_SHORT).show();
        String user_input;
        user_input = (editText_hor.getText().toString() + "&" + editText_ver.getText().toString());
        connectedThread.write(user_input.getBytes());
    }

    public void start_accepting_connection() {
        //call this on button click as suited by you

        AcceptThread acceptThread = new AcceptThread();
        acceptThread.start();
        Toast.makeText(getApplicationContext(), "accepting", Toast.LENGTH_SHORT).show();
    }

    public void initialize_clicks() {
        lv_paired_devices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object[] objects = set_pairedDevices.toArray();
                BluetoothDevice device = (BluetoothDevice) objects[position];

                ConnectThread connectThread = new ConnectThread(device);
                connectThread.start();
                connectedThread = new ConnectedThread(connectThread.mmSocket);

                Toast.makeText(getApplicationContext(), "Device Choosen " + device.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initialize_layout() {
        lv_paired_devices = (ListView) findViewById(R.id.lv_paired_devices);
        adapter_paired_devices = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, R.id.textView13);
        lv_paired_devices.setAdapter(adapter_paired_devices);
    }

    public void initialize_bluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(getApplicationContext(), "Your Device doesn't support bluetooth.", Toast.LENGTH_SHORT).show();
            finish();
        }

        //Add these permisions before
//        <uses-permission android:name="android.permission.BLUETOOTH" />
//        <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
//        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
//        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            set_pairedDevices = bluetoothAdapter.getBondedDevices();

            if (set_pairedDevices.size() > 0) {

                for (BluetoothDevice device : set_pairedDevices) {
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address

                    adapter_paired_devices.add(device.getName() + "\n" + device.getAddress());
                }
            }
        }
    }


    public class AcceptThread extends Thread {
        public final BluetoothServerSocket serverSocket;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord("NAME", MY_UUID);
            } catch (IOException e) {
            }
            serverSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    break;
                }

                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    mHandler.obtainMessage(CONNECTED).sendToTarget();
                }
            }
        }
    }


    public class ConnectThread extends Thread {
        public final BluetoothSocket mmSocket;
        public final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            bluetoothAdapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mHandler.obtainMessage(CONNECTING).sendToTarget();

                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                }
                return;
            }

            // Do work to manage the connection (in a separate thread)
//            bluetooth_message = "Initial message"
//            mHandler.obtainMessage(MESSAGE_WRITE,mmSocket).sendToTarget();
        }

        /**
         * Will cancel an in-progress connection, and close the socket
         */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    public class ConnectedThread extends Thread {

        public final BluetoothSocket mmSocket;
        public final InputStream mmInStream;
        public final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[2];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();

                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
            }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }
}