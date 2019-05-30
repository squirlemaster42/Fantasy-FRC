package com.tst.tests;

import com.onion.sql.SQLClient;

public class SQLTest {

    final String username = "root";
    final String password = "password";
    final String url = "jdbc:mysql://localhost:3306/userpass";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void run(){
        SQLClient.getInstance();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}
