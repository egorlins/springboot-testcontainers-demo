package com.egorlins.springboot;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {

    //NB it should be static, otherwise it will start and stop before and after every testcase not the whole test class
    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");

        MY_SQL_CONTAINER.start();

        /*
        System.out.println(MY_SQL_CONTAINER.getDatabaseName());
        System.out.println(MY_SQL_CONTAINER.getPassword());
        System.out.println(MY_SQL_CONTAINER.getUsername());
        System.out.println(MY_SQL_CONTAINER.getJdbcUrl());
        */
    }
}