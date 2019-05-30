package com.onion.client;

import com.onion.requests.Request;
import com.onion.requests.RequestList;
import com.onion.utils.ClientUtils;

import javax.net.ssl.SSLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.Arrays;

public class Client implements Runnable{

    private final int id;
    private boolean connected;
    private Thread thread;
    private final PrintWriter writer;
    private final BufferedReader reader;

    public Client(final String ip, final PrintWriter writer, final BufferedReader reader){
        this.id = ClientUtils.generateClientID(ip);
        connected = false;

        this.reader = reader;
        this.writer = writer;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                '}';
    }

    @Override
    public void run() {
        System.out.println("Run started");
        String input;
        while(connected){
            System.out.println("Looping client: " + id);
            try {

                try {
                    input = reader.readLine();
                } catch(SSLException | SocketException e){
                    stop();
                    ClientHandler.getInstance().removeClient(getId());
                    return;
                }

                if(input != null){
                    //Handle Request
                    System.out.println("Handling Request");

                    String[] splitInput = input.split(" ");

                    try{
                        Request request = RequestList.getInstance().getRequest(splitInput[0]).newInstance();
                        writer.println(request.handleRequest(splitInput));
                        writer.flush();
                        writer.close();
                    }catch(NullPointerException e){
                       System.err.println("Invalid Request");
                    }
                }
            } catch (IOException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start(){
        if(connected){
            return;
        }
        connected = true;
        thread = new Thread(this);
        thread.start();
        System.out.println("Thread started");
    }

    public synchronized void stop(){
        if(!connected){
            return;
        }
        connected = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
