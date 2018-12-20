package com.onion.utils;

public class ClientUtils {

    public static int hashClientData(final String username, final String ip){
        return username.hashCode() + ip.hashCode();
    }
}
