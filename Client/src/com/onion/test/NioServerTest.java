package com.onion.test;

import com.onion.client.Client;
import com.onion.dataRequests.LoginRequest;
import com.onion.utils.GetNetworkAddress;

import java.net.InetAddress;

public class NioServerTest {

    public static void main(String[] args){
        Client c = new Client("10.12.68.20", 19672);
        c.start();
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            String mac = GetNetworkAddress.GetAddress("mac");
            c.setID(ip.hashCode());
            Thread.sleep(500);
            c.getClient().write(new LoginRequest().makeRequest("Change", c.getId()));
            c.getClient().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.stop();
    }
}
