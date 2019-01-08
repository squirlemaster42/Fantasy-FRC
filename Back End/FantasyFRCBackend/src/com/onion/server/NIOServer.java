package com.onion.server;

public class NIOServer implements Runnable{

    private NioSslServer server;

    private Thread thread;
    private boolean running = false;

    public NIOServer(final String ip, final int port){
        try {
            this.server = new NioSslServer("TLSv1.2", ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NioSslServer getServer(){
        return server;
    }

    public void stop(){
        server.stop();
    }

    public synchronized void startThread(){
        if(running){
            return;
        }
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    @SuppressWarnings("Duplicates")
    public synchronized void stopThread(){
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
