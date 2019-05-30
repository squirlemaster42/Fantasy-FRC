package com.onion.match;

import com.onion.parser.Parser;
import com.onion.utils.Constants;
import com.onion.utils.HttpReqUtils;
import com.onion.utils.ConcurrentFileWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GetBigData implements Runnable {

    private Queue<String> matchQueue;
    private Thread thread;
    private boolean running;
    private ConcurrentFileWriter writer;
    private BufferedReader reader;

    public GetBigData(){
        matchQueue = new ConcurrentLinkedQueue<>();
        try {
            writer = new ConcurrentFileWriter(matchQueue, "data/Matches.bltcrispychickensandwichfromwendys");
        }catch(IOException e){
            e.printStackTrace();
        }
        writer.start();
        try {
            reader = new BufferedReader(new FileReader(new File("data/Matches.bltcrispychickensandwichfromwendys")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        Constants.getInstance().setTBAAuthKey("DthYAt40rXjLrvWZpxlnsFSvSDzPUjCtQ4deP8eJuy7sEpCmhxWITxqkhpTgi6Jf");
        try {
            Object output = HttpReqUtils.makeRequest(HttpReqUtils.makeYearEventListReq(2019));
            String output2 = (String) output;
            output2 = output2.replaceAll("\"", "");
            output2 = output2.replaceAll("\\[", "");
            output2 = output2.replaceAll(" ", "");
            output2 = output2.replaceAll("\\]", "").trim();
            String[] eventlst = output2.split(",");
            ArrayList<String> events = new ArrayList<>();
            System.out.println("Adding events:");
            for (String e : eventlst) {
                events.add(e);
                System.out.println(e);
            }
            System.out.println("Adding matches:");
            for (String e : events) {
                try {
                    System.out.println("Adding matches for event " + e + ":");
                    String matchesData = (String) HttpReqUtils.makeRequest(HttpReqUtils.makeMatchListReq(e));
                    matchesData = matchesData.replaceAll("\"", "");
                    matchesData = matchesData.replaceAll("\\[", "");
                    matchesData = matchesData.replaceAll(" ", "");
                    matchesData = matchesData.replaceAll("\\]", "").trim();
                    String[] matcheslst = matchesData.split(",");
                    for (String mk : matcheslst) {
                        System.out.println(mk);
                        String k = reader.readLine();
                        if(k != null) {
                            k = k.substring(0, k.indexOf(","));
                            k = k.trim();
                            mk = mk.trim();
                        }

                        if(k != null && k.equals(mk)) {
                            System.out.println("Yo we skipped " + k);
                        } else {
                            Match m = Parser.parseMatch((String) HttpReqUtils.makeRequest(HttpReqUtils.makeMatchReq(mk)));
                            String s = BigData.formatMatch(m);
                            System.out.println(s);
                            synchronized (matchQueue) {
                                matchQueue.add(s);

                            }
                        }

                    }
                } catch (NullPointerException n) {
                    n.printStackTrace();
                    System.out.println("Uh oh");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running) return;
        running = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

