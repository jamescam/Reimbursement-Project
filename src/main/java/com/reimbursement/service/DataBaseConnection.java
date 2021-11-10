package com.reimbursement.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

        //A class that establishes a database connection to be used by other classes
public class DataBaseConnection {

    private static final Logger logger = LogManager.getLogger(DataBaseConnection.class);

    public Connection establishConnection() throws ClassNotFoundException, SQLException {

        //gets user environment variable
        String username = System.getenv("DB_USER");
        //gets password environment variable
        String password = System.getenv("DB_PASS");
        if(username == null | password == null){
            logger.info("Set the environment variables!");

        }
        String url = "DB_URL";
        Class.forName("org.postgresql.Driver"); //postgresql driver class
        Connection conn = DriverManager.getConnection(url, username, password);

        return conn;
    }
}
