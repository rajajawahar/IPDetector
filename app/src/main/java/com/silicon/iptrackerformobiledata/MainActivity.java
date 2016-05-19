package com.silicon.iptrackerformobiledata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mIPAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIPAddress = (TextView) findViewById(R.id.ip_address);
        findViewById(R.id.ipdetector).setOnClickListener(this);

    }

    public static String getDeviceIPAddress() {
        try {
            List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : networkInterfaces) {
                List<InetAddress> inetAddresses = Collections.list(networkInterface.getInetAddresses());
                for (InetAddress inetAddress : inetAddresses) {
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        String sAddr = inetAddress.getHostAddress().toUpperCase();
                        return sAddr;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @Override
    public void onClick(View v) {

        mIPAddress.setText(getDeviceIPAddress());

    }
}
