package com.onion.dataRequests;


import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
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

public class DataRequest implements Runnable{

    private Thread thread;
    private boolean running;
    private final String host;
    private final int port;

    private BufferedReader reader;
    private PrintWriter writer;

    public DataRequest(final String host, final int port) {
        this.port = port;
        this.host = host;

        KeyGenerator.genPrivateKey();
    }

    private SSLContext createSSLContext() {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("mytestkey.jks"), "passphrase".toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore,"passphrase".toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(km, tm, null);

            return sslContext;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        SSLContext sslContext = this.createSSLContext();

        try{
            SSLSocketFactory sslSocketFactory = null;
            if (sslContext != null) {
                sslSocketFactory = sslContext.getSocketFactory();
            }

            SSLSocket sslSocket = null;
            if (sslSocketFactory != null) {
                sslSocket = (SSLSocket) sslSocketFactory.createSocket(this.host, this.port);
            }

            System.out.println("Client Started");

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
                System.out.println("\tCipher suite : " + sslSession.getCipherSuite());
            }

            InputStream inputStream = null;
            if (sslSocket != null) {
                inputStream = sslSocket.getInputStream();
            }
            OutputStream outputStream = null;
            if (sslSocket != null) {
                outputStream = sslSocket.getOutputStream();
            }

            if (inputStream != null) {
                reader =  new BufferedReader(new InputStreamReader(inputStream));
            }
            if (outputStream != null) {
                writer = new PrintWriter(new OutputStreamWriter(outputStream));
            }

            writer.println("Hello Server");
            writer.println();
            writer.flush();

//            String line = null;
//            while((line = reader.readLine()) != null){
//                System.out.println("Input : " + line);
//
//                if(line.trim().equals("HTTP/1.1 200\r\n")){
//                    break;
//                }
//            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void closerConnection(){
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println("Input : " + line);
                if (line.trim().equals("HTTP/1.1 200\r\n")) {
                    break;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String sendRecieveData(final String sendString) throws IOException {
        System.out.println("Sending Data");
        writer.println(sendString);
        return reader.readLine();
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
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

