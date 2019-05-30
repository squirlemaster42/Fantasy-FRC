package com.onion.client;

public class NIOClient implements Runnable{

    private NioSslClient client;

    private Thread thread;
    private boolean running = false;

    private int id;

    public NIOClient(final String ip, final int port){
        try {
            client = new NioSslClient("TLSv1.2", ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(running){
            try {
                client.read();
            } catch (Exception e) {
                if(!(e instanceof NullPointerException)){
                    e.printStackTrace();
                }
            }
        }
    }

    public void setID(final int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public NioSslClient getClient(){
        return this.client;
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
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
