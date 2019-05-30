package com.tst.sql;

import com.onion.sql.SQLClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SQLTest {

    @Test
    void testRead(){
        assertEquals("natepass", SQLClient.getInstance().readUser("Nate"));
        assertEquals("spencerpass", SQLClient.getInstance().readUser("Spencer"));
        assertEquals("jakobpass", SQLClient.getInstance().readUser("Jakob"));
        assertEquals("bobpass", SQLClient.getInstance().readUser("Bob"));
        assertEquals("fredpass", SQLClient.getInstance().readUser("Fred"));
    }

    @Test
    void testWrite(){
        SQLClient.getInstance().writeUser("Nate", "natepass");
        SQLClient.getInstance().writeUser("Spencer", "spencerpass");
        SQLClient.getInstance().writeUser("Jakob", "jakobpass");
        SQLClient.getInstance().writeUser("Bob", "bobpass");
        SQLClient.getInstance().writeUser("Fred", "fredpass");
    }
}
