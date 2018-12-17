package com.onion.match;

import com.onion.team.Team;

import java.util.PriorityQueue;
import java.util.Queue;

public class ScoringManager implements Runnable{

    private static ScoringManager instance;

    public ScoringManager getInstance(){
        if(instance == null){
            instance = new ScoringManager();
        }
        return instance;
    }

    private boolean running = false;
    private Thread thread;
    private Queue<Team> scoringQueue;

    private ScoringManager(){
        scoringQueue = new PriorityQueue<>();
    }

    public void addTeamToScore(Team t){
        synchronized (scoringQueue){
            scoringQueue.add(t);
        }
    }

    @Override
    public void run() {
        while(running){
            synchronized (scoringQueue){
                Scorer.getInstance().scoreMatches(scoringQueue.poll());
            }
        }

        stop();
    }

    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread();
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


