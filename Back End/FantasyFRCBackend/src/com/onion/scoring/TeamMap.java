package com.onion.scoring;

import org.jetbrains.annotations.NotNull;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

public class TeamMap {

    private static TeamMap instance;

    public static TeamMap getInstance(){
        if(instance == null){
            instance = new TeamMap();
        }
        return instance;
    }

    private final Map<String, Team> teamMap;
    private final DB db;

    private TeamMap(){
        //https://www.programcreek.com/java-api-examples/?api=org.mapdb.DB
        //https://github.com/tuplejump/MapDB/blob/master/src/test/java/examples/Custom_Value.java
        Serializer<Team> teamSerializer = new TeamSerializer();
        db = DBMaker.tempFileDB().make();
        teamMap = db.hashMap("map").valueSerializer(teamSerializer).valueSerializer(Serializer.JAVA).createOrOpen();
    }

    public void addTeam(final Team team){
        teamMap.put(team.getNumber(), team);
        db.commit();
    }

    public Team getTeam(final String number){
        return teamMap.get(number);
    }

    public void close(){
        db.close();
    }

    class TeamSerializer implements Serializer<Team>, Serializable, Comparator<Team> {

        @Override
        public void serialize(@NotNull DataOutput2 out, @NotNull Team team) throws IOException {
            out.writeUTF(team.getNumber());
            out.writeUTF(team.getScore() + "");
        }

        @Override
        public Team deserialize(@NotNull DataInput2 in, int available) throws IOException {
            return new Team(in.readUTF(), Integer.parseInt(in.readUTF())); //TODO Check that parse is good
        }

        @Override
        public int compare(Team t1, Team t2){
            if(t1.getScore() == t2.getScore()){
                return 0;
            }
            return t1.getScore() < t2.getScore() ? -1 : 1;
        }

    }
}