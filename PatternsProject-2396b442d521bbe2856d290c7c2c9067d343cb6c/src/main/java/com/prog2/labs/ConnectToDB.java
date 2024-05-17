/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * This class is responsible for establishing a connection to the SQLite database.
 * It provides a method {@code connectDB()} to connect to the database and returns
 * a {@code Connection} object.
 * <p>
 * To use this class, create an instance and call the {@code connectDB()} method.
 * </p>
 * <p>
 * Example:
 * <pre>{@code
 * ConnectToDB dbConnector = new ConnectToDB();
 * Connection connection = dbConnector.connectDB();
 * }</pre>
 * </p>
 * <p>
 * If the connection is successful, a message will be displayed indicating success.
 * If an error occurs during the connection process, an error message will be displayed.
 * </p>
 * <p>
 * Note: This class assumes that the SQLite JDBC Driver is available in the classpath.
 * </p>
 */
public class ConnectToDB 
{
    /**
     * Establishes a connection to the SQLite database.
     * 
     * @return A {@code Connection} object representing the connection to the database.
     */
    public Connection connectDB()
    {
        Connection con = null;
        try 
        {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            System.out.println("Connection Successful");
            JOptionPane.showMessageDialog(null, "Connection Successful"+con);
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("SQLite JDBC Driver not found");
            JOptionPane.showMessageDialog(null, "SQLite JDBC Driver not found");
            e.printStackTrace();
        } 
        catch (SQLException e) 
        {
            System.out.println("Connection failed: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Connection failed");
            e.printStackTrace();
        }
        return con;
    }
}       
