package com.onion.user;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Password {

    private final String ALGO = "AES";
    private final byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    private final String password;

    public Password(final String password){
        String holder = null;
        try {
            holder = encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(holder != null){
            this.password = holder;
        }else{
            this.password = "";
        }
    }

    private String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encVal);
    }

    private String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    private Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }

    public String getPassword(){ return this.password; }


}
