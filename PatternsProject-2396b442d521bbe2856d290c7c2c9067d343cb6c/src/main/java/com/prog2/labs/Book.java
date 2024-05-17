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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * OBSERVABLE
 *
 * @author grech
 */
public class Book 
{

    String SN;
    String title;
    String author;
    String publisher;
    float price;
    int quantity;
    int issued;
    Date addedDate;

    private Connection connection;
    
    public Book()
    {
        try 
        {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:sqlite:library-books.db");
            System.out.println("\nBook: Connected to SQLite database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database: " + e.getMessage());
        }
    }

    public Book(String SN, String title, String author, String publisher, float price, int quantity) {
        this.SN = SN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
        this.issued = 0;
        this.addedDate = addedDate;
    }
    
    //Setters
    public void setSN(String SN) {
        this.SN = SN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    //Getters
    public String getSN() {
        return SN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getIssued() {
        return issued;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    //////////////////////////////////DATABSE QUERIE///////////////////////
    /**
 * Retrieves a map containing details of all books available in the catalog.
 * 
 * @return A map where each key is the title of a book and the corresponding value
 *         is a formatted string containing information about the book's details.
 */
public Map<String, String> viewCatalog() 
{
    Map<String, String> catalog = new HashMap<>();
    String sql = "SELECT * FROM Book";

    try (PreparedStatement statement = connection.prepareStatement(sql); 
            ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) 
        {
            // Retrieve book information from the result set
            String sn = resultSet.getString("sn");
            String t = resultSet.getString("title");
            String a = resultSet.getString("author");
            String ps = resultSet.getString("publisher");
            Float pr = resultSet.getFloat("price");
            int qu = resultSet.getInt("quantity");
            int is = resultSet.getInt("issued");

            // Format the book details into a single string
            String details = String.format("Author: %s\nPublisher: %s\nSN: %s\nPrice: %.2f\nQuantity: %d\nIssued: %d",
                    a, ps, sn, pr, qu, is);

            // Put the title and details into the map
            catalog.put(t, details);
        }
        if(catalog.isEmpty()){
            System.out.println("Cannot be found");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
    return catalog;
}


    /**
 * Retrieves a map containing details of all books that have been issued to students.
 * 
 * @return A map where each key is the serial number (SN) of an issued book and the corresponding value
 *         is a formatted string containing information about the book's issuance.
 */
public Map<String, String> viewIssuedBooks() {
    Map<String, String> catalog = new HashMap<>();

    String sql = "SELECT * FROM IssuedBooks";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) { // Iterate over all issued books in the result set
                // Retrieve information for each issued book
                String id = resultSet.getString("id");
                String sn = resultSet.getString("sn");
                String stid = resultSet.getString("studentId");
                String stName = resultSet.getString("studentName");
                String c = resultSet.getString("contact");
                String idate = resultSet.getString("issuedate");

                // Format the book details
                String formattedDetails = String.format("| ID: %-5s | Student ID: %-10s | Student Name: %-10s | Contact: %-5s | Issue Date: %-5s | "
                        + "Issued: %-5f | Added Date: %-5s |", id, stid, stName, c, idate.toString());

                // Put the formatted details into the catalog map with the serial number as the key
                catalog.put(sn, formattedDetails);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
    return catalog;
}

    
   /**
 * Checks if a book with the given serial number is available in the library.
 * 
 * @param sn The serial number of the book to check availability for.
 * @return {@code true} if the book is available; {@code false} otherwise.
 */
public boolean isAvailableBooks(String sn) {
    String sql = "SELECT * FROM Book WHERE sn = ?;";
    boolean isAvailable = false;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, sn); // Set the serial number parameter in the SQL query

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve the quantity of the book from the result set
                int quantity = resultSet.getInt("quantity");
                
                // Check if the quantity is greater than 0
                if (quantity > 0) {
                    isAvailable = true; // Set to true if the book is available
                    break; // Exit the loop since we only need to check one record
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
    }
    return isAvailable;
}

    
    /**
 * Issues a book to a student if it is available and the student exists.
 * 
 * @param studentID The ID of the student who is borrowing the book.
 * @param bookID    The ID of the book to be issued.
 */
public void issueBook(Student stud, String studentID, String bookID)
{
    // Check if the book is available
    boolean isAvailableBook = isAvailableBooks(bookID);

    // Check if the student exists
    boolean isExistStud = stud.validateStudent(studentID);

    // If the book is available and the student exists
    if (isAvailableBook && isExistStud) {
        // Update the quantity of the book (decrease by 1)
        this.updateBookMinus(bookID);
        System.out.println("Removed one copy from quantity");

        // Update the issued count of the book (increase by 1)
        this.updateBookPlus(bookID);
        System.out.println("Added one copy to issued");

        // Add an entry to the IssuedBooks table
        addToIssuedBooksTable(stud, bookID, studentID);
    }
}

    
    /**
 * Adds an entry to the IssuedBooks table in the library database.
 * 
 * @param student   The student who issued the book.
 * @param bookID    The ID of the book that was issued.
 * @param studentID The ID of the student who issued the book.
 */
public void addToIssuedBooksTable(Student student, String bookID, String studentID) {
    // Generate a random ID for the entry
    Random random = new Random();
    int id = random.nextInt(Integer.MAX_VALUE - 100) + 100;

    // Get the current date
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = currentDate.format(formatter);

    // Prepare the SQL query to insert the entry into the IssuedBooks table
    String sql = "INSERT INTO IssuedBooks (id, sn, studentID, studentName, contact, issueDate) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        // Set parameters for the SQL query
        statement.setString(1, Integer.toString(id));
        statement.setString(2, bookID);
        statement.setString(3, studentID);
        statement.setString(4, student.studInfo.get(2)); // Assuming index 2 contains the student's name
        statement.setString(5, student.studInfo.get(4)); // Assuming index 4 contains the student's contact information
        statement.setString(6, formattedDate);

        // Execute the SQL query to insert the entry into the IssuedBooks table
        statement.executeUpdate();
        System.out.println("Successfully Added!");
    } catch (SQLException e) {
        // Handle any SQL errors that occur during the execution of the SQL query
        System.out.println("SQL Error: " + e.getMessage());
    }
}

    
   /**
 * Decreases the quantity of available copies of a book in the library database.
 * 
 * @param bookID The ID of the book to be updated.
 */
public void updateBookMinus(String bookID) {
    // SQL statement to update the quantity of the book
    int newValue = 0;
    String updateSQL = "UPDATE Book SET quantity = ? WHERE sn = ?";
    try (PreparedStatement statement = connection.prepareStatement(updateSQL)) 
    {
        // Set the book ID parameter in the SQL statement
        statement.setString(1, getNewValue(bookID));
        statement.setString(2, bookID);
        // Execute the update statement
        statement.executeUpdate();
    } 
    catch (SQLException ex) 
    {
        // Log any SQL exceptions that occur during the update process
        Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public String getNewValue(String bookID) throws SQLException
    {
        String newValue = "";
        int newValInt = 0;
        String updateSQL = "SELECT * FROM Book WHERE sn = ?;";
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) 
        {
            statement.setString(1, bookID);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    newValue = resultSet.getString("quantity");
                    newValInt = Integer.parseInt(newValue);
                    newValInt = newValInt - 1;
                }
            }
        }
        return String.valueOf(newValInt);
    }
    
    public String getNewValuePlus(String bookID) throws SQLException
    {
        String newValue = "";
        int newValInt = 0;
        String updateSQL = "SELECT * FROM Book WHERE sn = ?;";
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) 
        {
            statement.setString(1, bookID);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    newValue = resultSet.getString("quantity");
                    newValInt = Integer.parseInt(newValue);
                    newValInt = newValInt + 1;
                }
            }
        }
        return String.valueOf(newValInt);
    }
/**
 * Increases the number of issued copies of a book in the library database.
 * 
 * @param bookID The ID of the book to be updated.
 */
public void updateBookPlus(String bookID) 
{
    String updateSQL = "UPDATE Book SET issued = ? WHERE sn = ?";
    try (PreparedStatement statement = connection.prepareStatement(updateSQL)) 
    {
        // Set the book ID parameter in the SQL statement
        statement.setString(1, getNewValuePlus(bookID));
        statement.setString(2, bookID);
        // Execute the update statement
        statement.executeUpdate();
    } 
    catch (SQLException ex) 
    {
        // Log any SQL exceptions that occur during the update process
        Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    

    /**
 * Adds a new book to the library database.
 * 
 * @param SN       The serial number of the book.
 * @param title    The title of the book.
 * @param author   The author of the book.
 * @param publisher The publisher of the book.
 * @param price    The price of the book.
 * @param quantity The quantity of the book available.
 * @param issued   The number of issued copies of the book.
 */
public void addBook(String SN, String title, String author, String publisher,
        float price, int quantity, int issued) {
    Book book = new Book(SN, title, author, publisher, price, quantity);

    // Prepare the SQL query to insert the book into the database
    String sql = "INSERT INTO book (SN, title, author, publisher, price, quantity, issued, addedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();

        // Define a custom date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the current time using the custom format
        String formattedTime = currentTime.format(formatter);

        // Create a prepared statement with the SQL query
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, SN);
        ps.setString(2, title);
        ps.setString(3, author);
        ps.setString(4, publisher);
        ps.setFloat(5, price);
        ps.setInt(6, quantity);
        ps.setInt(7, issued);
        ps.setString(8, formattedTime);

        // Execute the prepared statement to insert the book into the database
        ps.executeUpdate();

        // Close the database connection
        connection.close();

        // Print the SQL statement
        System.out.println(ps.toString());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}


    /**
 * Returns a book that was borrowed by a student.
 * 
 * @param book    The book to be returned.
 * @param student The student who is returning the book.
 * @return true if the book was successfully returned, false otherwise.
 */
public boolean returnBook(Book book, Student student) {
    try {
        if (student.toReturn(book)) { // Check if the book is issued to the student
            // Update the database (increase quantity, decrease issued copies, delete record)
            String updateBooksQuery = "UPDATE Books SET quantity = quantity + 1, issued = issued - 1 WHERE sn = ?";
            PreparedStatement updateBooksStmt = connection.prepareStatement(updateBooksQuery);
            updateBooksStmt.setString(1, book.getSN());
            int rowsAffected = updateBooksStmt.executeUpdate();

            if (rowsAffected > 0) { // If update successful
                String deleteIssuedBooksQuery = "DELETE FROM IssuedBooks WHERE SN = ?";
                PreparedStatement deleteIssuedBooksStmt = connection.prepareStatement(deleteIssuedBooksQuery);
                deleteIssuedBooksStmt.setString(1, book.getSN());
                int deleteRows = deleteIssuedBooksStmt.executeUpdate();

                if (deleteRows > 0) { // If delete successful
                    return true; // Book successfully returned
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Return false if the book was not successfully returned
}

}
