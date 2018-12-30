package com.onion.server;

import com.onion.client.Client;
import com.onion.client.ClientHandler;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class NIOServer implements Runnable{

    //TODO Try using tomcat
    //TODO Try out Heroku

    private final int port;
    private final String ip;
    private Thread thread;
    private boolean running = false;

    public NIOServer(final int port, final String ip) {
        this.port = port;
        this.ip = ip;

        KeyGenerator.generateKeyFile(); //Generates key
    }

    @SuppressWarnings("Duplicates")
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

    @SuppressWarnings("Duplicates")
    public void run(){ //TODO Figure out how to use NIO selectors

        //Sets up SSL
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

            //Sets Up NIOSelector
            Selector selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(ip, port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(256);

            while(running){

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Thread Methods
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