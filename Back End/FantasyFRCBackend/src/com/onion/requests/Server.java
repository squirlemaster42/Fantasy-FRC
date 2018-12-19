package com.onion.requests;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore;

public class Server implements Runnable{

    private final int port;
    private Thread thread;
    private boolean running = false;

    public Server(final int port) {
        this.port = port;

        KeyGenerator.generateKeyFile(); //Generates key
    }

    private SSLContext createSSLContext(){ //TODO Add file name to constants and change
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("mytestkey.jks"), "passphrase".toCharArray()); //TODO Create key store

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, "passphrase".toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(km, tm, null);
            return sslContext;
        }catch(Exception e){ //TODO Get more specific exception
            e.printStackTrace();
        }
        return null;
    }

    public void run(){
        SSLContext sslContext = this.createSSLContext();

        try{
            SSLServerSocketFactory sslServerSocketFactory = null;
            if (sslContext != null) {
                sslServerSocketFactory = sslContext.getServerSocketFactory();
            }

            SSLServerSocket sslServerSocket = null;
            if (sslServerSocketFactory != null) {
                sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);
            }

            System.out.println("Server Started");

            while(running){
                SSLSocket sslSocket = null;
                if (sslServerSocket != null) {
                    sslSocket = (SSLSocket) sslServerSocket.accept();
                }

                if (sslSocket != null) {
                    sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());
                }
                if (sslSocket != null) {
                    sslSocket.startHandshake();
                }

                SSLSession sslSession = null;
                if (sslSocket != null) {
                    sslSession = sslSocket.getSession();
                }

                System.out.println("SSLSession :");
                if (sslSession != null) {
                    System.out.println("\tProtocol : " + sslSession.getProtocol());
                }

                if (sslSession != null) {
                    System.out.println("Cipher suite : " + sslSession.getCipherSuite());
                }

                InputStream inputStream = null;
                if (sslSocket != null) {
                    inputStream = sslSocket.getInputStream();
                }
                OutputStream outputStream = null;
                if (sslSocket != null) {
                    outputStream = sslSocket.getOutputStream();
                }

                BufferedReader bufferedReader = null;
                if (inputStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                }

                PrintWriter printWriter = null;
                if (outputStream != null) {
                    printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
                }

//                    String line = null;
//                    while((line = bufferedReader.readLine()) != null){
//                        System.out.println("Input : " + line);
//
//                        if(line.trim().isEmpty()){
//                            break;
//                        }
//                    }

                    //TODO Fix problem with server/client (https://stackoverflow.com/questions/18403208/server-socket-disconnects-and-stops-listening)
                    String line;
                    while(running){
                        System.out.println("Checking");
                        System.out.println(running);
                        if (bufferedReader != null && (line = bufferedReader.readLine()) != null) {
                            System.out.println("Input : " + line);
                        }
                    }
                    System.out.println("OH SHOOT WE OUT THE LOOP");
                    System.out.println(running);

                if (printWriter != null) {
                    printWriter.print("HTTP/1.1 200\r\n");
                }
                if (printWriter != null) {
                    printWriter.flush();
                }

                if (sslSocket != null) {
                    sslSocket.close();
                }
            }
        }catch (IOException e){ //TODO Add more specific exception
            e.printStackTrace();
        }
    }

    public synchronized void start(){
        if(running){
            return;
        }
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}