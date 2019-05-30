package com.onion.match;

import java.util.Map;

public class Match {

    long actual_time;
    Map<String, Alliance> alliances;
    String comp_level;
    String event_key;
    String key;
    int match_number;
    long post_result_time;
    long predicted_time;
    Map<String, MatchResults> score_breakdown;
    int set_number;
    long time;
    Video[] videos;
    String winning_alliance;

    public long getActual_time() {
        return actual_time;
    }

    public Map<String, Alliance> getAlliances() {
        return alliances;
    }

    public String getComp_level() {
        return comp_level;
    }

    public String getEvent_key() {
        return event_key;
    }

    public String getKey() {
        return key;
    }

    public int getMatch_number() {
        return match_number;
    }

    public long getPost_result_time() {
        return post_result_time;
    }

    public long getPredicted_time() {
        return predicted_time;
    }

    public Map<String, MatchResults> getScore_breakdown() {
        return score_breakdown;
    }

    public int getSet_number() {
        return set_number;
    }

    public long getTime() {
        return time;
    }

    public Video[] getVideos() {
        return videos;
    }

    public String getWinning_alliance() {
        return winning_alliance;
    }

    @Override
    public String toString() {
        return key;
    }
}
