package com.tst.map;

import com.onion.scoring.Team;
import com.onion.scoring.TeamMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamMapTest {

    @Test
    void testMap(){
        TeamMap teamMap = TeamMap.getInstance();
        teamMap.addTeam(new Team("1234"));
        teamMap.addTeam(new Team("5678"));
        teamMap.addTeam(new Team("9876"));
        teamMap.addTeam(new Team("4321"));
        teamMap.addTeam(new Team("6789"));
        teamMap.addTeam(new Team("4567"));
        teamMap.addTeam(new Team("3456"));
        teamMap.addTeam(new Team("2345"));
        teamMap.addTeam(new Team("6543"));
        assertEquals(new Team("1234"), teamMap.getTeam("1234"));
        assertEquals(new Team("5678"), teamMap.getTeam("5678"));
        assertEquals(new Team("9876"), teamMap.getTeam("9876"));
        assertEquals(new Team("4321"), teamMap.getTeam("4321"));
        assertEquals(new Team("6789"), teamMap.getTeam("6789"));
        assertEquals(new Team("4567"), teamMap.getTeam("4567"));
        assertEquals(new Team("3456"), teamMap.getTeam("3456"));
        assertEquals(new Team("2345"), teamMap.getTeam("2345"));
        assertEquals(new Team("6543"), teamMap.getTeam("6543"));
        teamMap.close();
    }
}
