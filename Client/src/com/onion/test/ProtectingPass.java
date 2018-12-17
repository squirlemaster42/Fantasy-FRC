package com.onion.test;

public class ProtectingPass {
    public static void main(String[] args)
    {
        String myPassword = "myPassword123";

        // Generate Salt. The generated value can be stored in DB.
        String salt = EncryptionTest.getSalt(30);

        // Protect user's password. The generated value can be stored in DB.
        String mySecurePassword = EncryptionTest.generateSecurePassword(myPassword, salt);

        // Print out protected password
        System.out.println("My secure password = " + mySecurePassword);
        System.out.println("Salt value = " + salt);
    }
}
