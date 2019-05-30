package com.onion.scoring;

import com.onion.match.Match;
import com.onion.match.MatchResults;
import com.onion.utils.Constants;
import com.onion.utils.HttpReqUtils;
import com.onion.teamstatus.Event;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class Scorer implements Runnable{

    enum MatchType {
        QUAL,
        QUARTER,
        SEMI,
        FINAL,
        EINSTEINRR,
        EINSTEINF
    }

    public static final int WIN_POINTS = 3;
    public static final int RP1_POINTS = 1; //Climb RP
    public static final int RP2_POINTS = 2; //Rocket RP

    public static Scorer instance;

    public static Scorer getInstance(){
        if(instance == null){
            instance = new Scorer();
        }
        return instance;
    }

    private Queue<Team> scoringQueue;

    private Thread thread;
    private boolean isRunning;

    private Scorer(){
        scoringQueue = new PriorityBlockingQueue<>();
    }

    public void run(){
        while(isRunning){
            int calcScore = 0;
            Team scoringTeam = scoringQueue.poll();
            if(scoringTeam == null){
                continue;
            }

            String matchKey = scoringTeam.matchList.get(scoringTeam.lastMatchScored++);

            //TODO Get Match
            Match match = null;
            Event eventStatus = null;
            try {
                eventStatus = Constants.getInstance().getGson().fromJson(HttpReqUtils.makeRequest(HttpReqUtils.makeTeamEventStatusRequest(new com.onion.team.Team(Integer.parseInt(scoringTeam.getNumber().replace("frc", ""))), "2019tur")), Event.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            MatchType type = getMatchType(match);

            switch (type){
                case QUAL:
                    String alliance = getAlliance(match, scoringTeam);
                    boolean surrogateMatch = false;
                    for(String key : match.getAlliances().get(alliance).getSurrogate_team_keys()){
                        if(key.equals(scoringTeam.getTBARequestID())){
                            surrogateMatch = true;
                            break;
                        }
                    }
                    if (!surrogateMatch) {
                        MatchResults results = match.getScore_breakdown().get(alliance);
                        if (match.getWinning_alliance().equals(alliance)) {
                            calcScore += 3;
                        }else if(match.getWinning_alliance().equals("")){
                            //There is a tie
                            calcScore += 1;
                        }
                        if (results.isHabDockingRankingPoint()) {
                            calcScore += 1;
                        }
                        if (results.isCompleteRocketRankingPoint()) {
                            calcScore += 2;
                        }
                    }
                    break;
                case QUARTER:
                case SEMI:
                case FINAL:
                    calcScore += scorePlayoffs(eventStatus);
                    break;
                case EINSTEINRR:
                    calcScore += scoreEinsteinRR(eventStatus);
                    break;
                case EINSTEINF:
                    calcScore += scoreEinsteinFinals(eventStatus);
                    break;
                    default:
                        calcScore += 0;
                        //TODO Throw exception
            }

            scoringTeam.setScore(calcScore);
        }
    }

    private MatchType getMatchType(final Match match){
        switch(match.getComp_level()){
            case "qm":
                return MatchType.QUAL;
            case "qf":
                return MatchType.QUARTER;
            case "sf":
                if(match.getKey().contains("cmptx") || match.getKey().contains("cmpmi")){
                    return MatchType.EINSTEINRR;
                }
                return MatchType.SEMI;
            case "f":
                if(match.getKey().contains("cmptx") || match.getKey().contains("cmpmi")){
                    return MatchType.EINSTEINF;
                }
                return MatchType.FINAL;
            default:
                return null; //TODO Throw exception
        }
    }

    private int scorePlayoffs(com.onion.teamstatus.Event status){

        String level = status.getPlayoff().getLevel();
        switch(level){
            case "qf":
                return 0;
            case "sf":
                return 6;
            case "f":
                if(status.getPlayoff().getStatus().equals("won")){
                    return 36;
                }else{
                    return 42;
                }
                default:
                    return 0;
        }
    }

    private int scoreEinsteinRR(com.onion.teamstatus.Event status){
        return status.getPlayoff().getRecord().getWins() * 18;
    }

    private int scoreEinsteinFinals(com.onion.teamstatus.Event status){
        return status.getPlayoff().getStatus().equals("won") ? 60 : 30;
    }

    //Returns the alliance this team is on
    private String getAlliance(Match match, Team team){
        for(String e : match.getAlliances().get("red").getTeam_keys()) {
            if(team.getTBARequestID().equals(e)){
                return "red";
            }
        }
        return "blue";
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