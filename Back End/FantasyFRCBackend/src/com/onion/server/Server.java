package com.onion.server;

import com.onion.client.Client;
import com.onion.client.ClientHandler;

import javax.net.ssl.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.FILE;

public class Server implements Runnable{

    //TODO Try using tomcat
    //TODO Try out Heroku

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
            keyStore.load(new FileInputStream("mytestkey.jks"), "passphrase".toCharArray()); //TODO Change passphrase and file name

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, "passphrase".toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(km, tm, null);
            return sslContext;
        }catch(NoSuchAlgorithmException | KeyManagementException | KeyStoreException | UnrecoverableKeyException | CertificateException | IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void run(){ //TODO Figure out how to use NIO selectors
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
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

                sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());
                sslSocket.startHandshake();

                SSLSession sslSession = sslSocket.getSession();
//                System.out.println("SSLSession :");
//                System.out.println("\tProtocol : " + sslSession.getProtocol());
//                System.out.println("Cipher suite : " + sslSession.getCipherSuite());

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

                ClientHandler.getInstance().registerClient(new Client(sslSocket.getLocalAddress().toString(), printWriter, bufferedReader));

//                String line;
//                while(running) {
//                    line = bufferedReader.readLine();
//                    if (line != null) {
//                        System.out.println("Input: " + line);
//                    }
//                }

//                printWriter.print("HTTP/1.1 200\r\n");
//                printWriter.flush();
//                sslSocket.close();
            }
        }catch (IOException e){
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

    @SuppressWarnings("Duplicates")
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