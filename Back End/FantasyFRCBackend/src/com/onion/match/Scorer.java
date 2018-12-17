package com.onion.match;

import com.onion.parser.Parser;
import com.onion.team.Team;
import com.onion.utils.HttpReqUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Scorer {

    private static Scorer instance;

    public static Scorer getInstance(){
        if(instance == null){
            instance = new Scorer();
        }
        return instance;
    }

    public int scoreMatch(Team team, String matchID){
        int totalScore = 0;

        try {
            Match m = Parser.parseMatch((String) HttpReqUtils.makeRequest(HttpReqUtils.makeMatchReq(matchID)));

            totalScore += score(m, team);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (NullPointerException e){
            System.err.println("Match not played yet.");

        }
        return totalScore;
    }

    public int scoreMatches(Team team){
        try {
            Object output = HttpReqUtils.makeRequest(HttpReqUtils.makeTeamMatchListReq(team, 2018));
            String output2 = (String) output;
            output2 = output2.replaceAll("\"", "");
            output2 = output2.replaceAll("\\[", "");
            output2 = output2.replaceAll(" ", "");
            output2 = output2.replaceAll("\\]", "").trim();
            String[] outputList = output2.split(",");
            ArrayList<Match> matches = new ArrayList<>();

            for(String matchString : outputList){
                matches.add(Parser.parseMatch((String) HttpReqUtils.makeRequest(HttpReqUtils.makeMatchReq(matchString))));
            }

            int totalScore = 0;
            for(Match m : matches){
                try {
                    totalScore += score(m, team);
                }catch (NullPointerException e){
                    System.err.println("Match not played yet.");
                }

            }
            return totalScore;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public int scoreRed(final Match m){
        int score = 0;
        try {
            String winner = m.winning_alliance;
            if (m.comp_level.equals("qm")) {
                MatchResults results = m.score_breakdown.get("red");
                if (results.autoQuestRankingPoint) {
                    score += 1;
                }
                if (results.faceTheBossRankingPoint) {
                    score += 2;
                }
            }
            if (winner.equals("red")) {
                if (m.comp_level.equals("qm")) {
                    score += 3;
                } else if (m.comp_level.equals("qf")) {
                    score += 6;
                } else if (m.comp_level.equals("sf")) {
                    score += 12;
                } else if (m.comp_level.equals("f")) {
                    score += 24;
                }
            }
            if (winner.equals("blue") && m.comp_level.equals("f")) {
                score += 18;
            }
        }catch(NullPointerException n){
            System.out.println("Match not played yet :(");
        }
        return score;
    }

    public int scoreBlue(final Match m){
        int score = 0;
        try {
            String winner = m.winning_alliance;
            if (m.comp_level.equals("qm")) {
                MatchResults results = m.score_breakdown.get("blue");
                if (results.autoQuestRankingPoint) {
                    score += 1;
                }
                if (results.faceTheBossRankingPoint) {
                    score += 2;
                }
            }
            if (winner.equals("blue")) {
                if (m.comp_level.equals("qm")) {
                    score += 3;
                } else if (m.comp_level.equals("qf")) {
                    score += 6;
                } else if (m.comp_level.equals("sf")) {
                    score += 12;
                } else if (m.comp_level.equals("f")) {
                    score += 24;
                }
            }
            if (winner.equals("red") && m.comp_level.equals("f")) {
                score += 18;
            }
        }catch(NullPointerException n){
            System.out.println("Match not played yet :(");
        }
        return score;
    }

    private int score(Match m, Team team){
        int totalScore = 0;

        if(isEinsteinKey(m.event_key) && m.comp_level.equals("sf")){
            totalScore += scoreEinsteinRRMatch(m, team);
        }else if(isEinsteinKey(m.event_key) && m.comp_level.equals("f")) {
            totalScore += scoreEinsteinFMatch(m, team);
        }else if(m.comp_level.equals("qm")){
            totalScore += scoreQualMatch(m, team);
        }else if(m.comp_level.equals("qf")){
            totalScore += scoreQFMatch(m, team);
        }else if(m.comp_level.equals("sf")){
            totalScore += scoreSFMatch(m, team);
        }else if(m.comp_level.equals("f")){
            totalScore += scoreFMatch(m, team);
        }

        return totalScore;
    }

    private boolean isEinsteinKey(final String eventKey){
        return eventKey.equals("2018cmptx") ^ eventKey.equals("2018cmpmi");
    }

    private int scoreQualMatch(final Match m, Team team){
        int score = 0;
        String alliance = getAlliance(m, team);
        boolean surrogateMatch = false;
        for(String key : m.alliances.get(alliance).surrogate_team_keys){
            if(key.equals(team.getTBARequestID())){
                surrogateMatch = true;
                break;
            }
        }
        if (!surrogateMatch) {
            MatchResults results = m.score_breakdown.get(alliance);
            if (m.winning_alliance.equals(alliance)) {
                score += 3;
            }
            if (results.autoQuestRankingPoint) {
                score += 1;
            }
            if (results.faceTheBossRankingPoint) {
                score += 2;
            }
        }
        return score;
    }

    private int scoreQFMatch(final Match m, Team team){
        if(m.winning_alliance.contains(getAlliance(m, team))){
            return 6;
        }
        return 0;
    }

    private int scoreSFMatch(final Match m, Team team){
        if(m.winning_alliance.contains(getAlliance(m, team))){
            return 12;
        }
        return 0;
    }

    private int scoreFMatch(final Match m, Team team){
        if(m.winning_alliance.contains(getAlliance(m, team))){
            return 24;
        }
        return 18;
    }

    private int scoreEinsteinRRMatch(final Match m, Team team){
        if(m.winning_alliance.contains(getAlliance(m, team))){
            return 18;
        }
        return 0;
    }

    private int scoreEinsteinFMatch(final Match m, Team team){
        if(m.winning_alliance.contains(getAlliance(m, team))){
            return 60;
        }
        return 30;
    }

    private String getAlliance(Match match, Team team){
        for(String e : match.alliances.get("red").team_keys) {
            if(team.getTBARequestID().equals(e)){
                return "red";
            }
        }
        return "blue";
    }
}
