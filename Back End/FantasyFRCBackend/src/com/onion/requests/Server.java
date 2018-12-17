package com.onion.requests;

import javax.net.ssl.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

public class Server implements Runnable{

    private final int port;
    private Thread thread;
    private boolean running = false;

    public Server(final int port) throws IOException {
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
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();

            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);

            System.out.println("Server Started");

            while(running){
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

                sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());
                try{
                    sslSocket.startHandshake();

                    SSLSession sslSession = sslSocket.getSession();

                    System.out.println("SSLSession :");
                    System.out.println("\tProtocol : " + sslSession.getProtocol());
                    System.out.println("Cipher suite : " + sslSession.getCipherSuite());

                    InputStream inputStream = sslSocket.getInputStream();
                    OutputStream outputStream = sslSocket.getOutputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));

                    String line = null;
                    while((line = bufferedReader.readLine()) != null){
                        System.out.println("Input : " + line);

                        if(line.trim().isEmpty()){
                            break;
                        }
                    }

                    printWriter.print("HTTP/1.1 200\r\n");
                    printWriter.flush();

                    sslSocket.close();
                }catch(Exception e ){ //TODO Add more specific exception
                    e.printStackTrace();
                }
            }
        }catch (Exception e){ //TODO Add more specific exception
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