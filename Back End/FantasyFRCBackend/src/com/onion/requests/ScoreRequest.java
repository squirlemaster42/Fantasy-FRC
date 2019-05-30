package com.onion.requests;

import com.onion.scoring.Scorer;
import com.onion.responses.ScoreResponse;
import com.onion.scoring.TeamMap;
import com.onion.team.Team;

public class ScoreRequest extends Request {

    public ScoreRequest() {
        super("ScoreRequest:");
    }

    @Override
    public String handleRequest(String[] input){
        int response = TeamMap.getInstance().getTeam(input[0]).getScore();
        return "ScoreResponse " + response;
    }
}
