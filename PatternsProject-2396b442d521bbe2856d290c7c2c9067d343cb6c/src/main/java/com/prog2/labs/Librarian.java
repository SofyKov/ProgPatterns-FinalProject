/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a librarian in the library management system.
 * Implements the LibraryObserver interface to receive notifications about book updates.
 * Provides methods for verifying login credentials and updating book information.
 * 
 * @author grech
 */
public class Librarian
{

    private String id;
    private Connection connection;

    /**
     * Constructs a new Librarian object and establishes a connection to the SQLite database.
     */
    public Librarian() {
        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:sqlite:library-books.db");
            System.out.println("\nLibrary Controller: Connected to SQLite database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database: " + e.getMessage());
        }
    }

    /**
     * Constructs a new Librarian object with the specified ID.
     * 
     * @param id The ID of the librarian.
     */
    public Librarian(String id) {
        this.id = id;
    }

    /**
     * Verifies login credentials for a librarian.
     * 
     * @param id       The ID of the librarian.
     * @param password The password of the librarian.
     * @return true if the login credentials are valid, false otherwise.
     */
    public boolean verifyLogin(String id, String password) {
        String sql = "SELECT * FROM Librarian WHERE ID = ? AND passwd = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if there's a matching record
            }
        } catch (SQLException e) {
            System.out.println("Error verifying login: " + e.getMessage());
        }
        return false; // Return false if an error occurs or no matching record found
    }
}
