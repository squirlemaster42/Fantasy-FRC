package com.onion.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class ConcurrentFileWriter implements Runnable{

    private final Queue<String> queue;
    private Thread thread;
    private boolean isRunning;
    private final BufferedWriter writer;

    public ConcurrentFileWriter(final Queue<String> queue, final String filePath) throws IOException {
        this.queue = queue;
        this.writer = new BufferedWriter(new FileWriter(filePath, true));
    }

    @Override
    public void run() {
        while(isRunning){
            String toWrite;
            synchronized (queue){
                toWrite = queue.poll();
            }
            if(toWrite != null){
                try {
                    writer.append(toWrite + "\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start(){
        if(isRunning){
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!isRunning){
            return;
        }
        isRunning = false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
