package com.example.annuaire.controller;


import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    private static Connection connection;
    private final String user = "dalila";
    private final String password ="dalila"; //"admin_2022";
    private final String ip="192.168.1.50";//"192.168.1.63";
    private final String url = "jdbc:mysql://" + ip + "/annuaire";

    public Connection connect()
    {
        Thread thread = new Thread(() -> {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("connected:" + url);

                connection = DriverManager.getConnection(url, user, password);
            }
            catch (Exception e)
            {
                System.out.print(e.getMessage());
                e.printStackTrace();
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }



    public static Connection getInstance(){
        if(connection == null){
            new DataBaseConnection();
        }
        return connection;
    }


}
