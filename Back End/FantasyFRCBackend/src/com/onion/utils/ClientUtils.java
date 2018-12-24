package com.onion.utils;

public class ClientUtils {

    public static int generateClientID(final String ip){
        return ip.hashCode();
    }
}
