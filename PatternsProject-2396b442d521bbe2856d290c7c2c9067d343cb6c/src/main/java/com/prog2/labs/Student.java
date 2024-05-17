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
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author grech
 */
public class Student  
{
    private static Student instance;
    //LibraryController controller = LibraryController.getInstance();
    private Connection connection;
    String sId;
    String name;
    String contact;
    
    ArrayList<ArrayList<String>> studentInfoBorrow = new ArrayList<>();
    ArrayList<String> studInfo = new ArrayList<>();

    /**
 * Constructs a new Student object.
 * Initializes the database connection.
 */
public Student() 
{
    try {
        // Attempt to establish a connection to the SQLite database
        connection = DriverManager.getConnection("jdbc:sqlite:library-books.db");
        System.out.println("\nLibrary Controller: Connected to SQLite database.");
    } catch (SQLException e) {
        // Print an error message if connection fails
        System.out.println("Error connecting to SQLite database: " + e.getMessage());
    }
}
    
    public Student(String id, String name, String contact) 
    {
        this.sId = id;
        this.name = name;
        this.contact = contact;
    }

    public static synchronized Student getInstance() 
    {
        if (instance == null) 
        {
            instance = new Student();
        }
        return instance;
    }
    
    /**
 * Validates the existence of a student in the database based on the provided student ID.
 *
 * @param id The student ID to validate.
 * @return true if the student exists in the database, false otherwise.
 */
public boolean validateStudent(String id) {
    boolean isExist = false;
    String sql = "SELECT * FROM Student WHERE studentID =  ?;";

    try (PreparedStatement statement = connection.prepareStatement(sql)) 
    {
        statement.setString(1, id); // Set the id parameter in the SQL query
        try (ResultSet resultSet = statement.executeQuery()) 
        {
            while (resultSet.next()) 
            {
                String studID = resultSet.getString("studentID");
                if (id.equals(studID)) 
                {
                    isExist = true;
                }
            }
        }
    } catch (SQLException e) {
        // Print an error message if a SQL error occurs
        System.out.println("SQL Error: " + e.getMessage());
    }
    return isExist;
}
    
    
    /**
 * Retrieves a string containing information about all available books in the library.
 *
 * @return A string containing details of all available books, formatted for display.
 */
public String viewAvailableBooks() {
    String sql = "SELECT * FROM Book WHERE quantity > issued;";
    String outputStr = "";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve book information from the result set
                String sn = resultSet.getString("sn");
                outputStr += sn + " | ";

                String t = resultSet.getString("title");
                outputStr += t + " | ";

                String a = resultSet.getString("author");
                outputStr += a + "\n";

                // Print book information for debugging purposes
                System.out.println(sn + " " + t + " " + a);
            }
        }
    } catch (SQLException e) {
        // Print an error message if a SQL error occurs
        System.out.println("SQL Error: " + e.getMessage());
    }
    return outputStr;
}


    
    
    /**
 * Searches for a book by its title in the library database and retrieves information about it.
 *
 * @param searchByBookTitle The title of the book to search for.
 * @return A string containing information about the book with the matching title.
 */
public String searchBookByBookTitle(String searchByBookTitle) {
    // SQL query to select books with quantity greater than issued
    String sql = "SELECT * FROM Book WHERE quantity > issued;";
    String outputStr = "";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve book information from the result set
                String t = resultSet.getString("title");

                // Compare book titles (case-insensitive) to the search query
                if (searchByBookTitle.equalsIgnoreCase(t)) {
                    outputStr += t + " ";

                    String title = resultSet.getString("author");
                    outputStr += "by " + title;
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
    }
    System.out.println(outputStr);
    return outputStr;
}

/**
 * Searches for a book by its serial number (ID) in the library database and retrieves information about it.
 *
 * @param searchByBookID The serial number (ID) of the book to search for.
 * @return A string containing information about the book with the matching ID.
 */
public String searchBookByBookID(String searchByBookID) {
    // SQL query to select books with quantity greater than issued
    String sql = "SELECT * FROM Book WHERE quantity > issued;";
    String outputStr = "";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve book information from the result set
                String sn = resultSet.getString("sn");

                // Compare book IDs (case-insensitive) to the search query
                if (searchByBookID.equalsIgnoreCase(sn)) {
                    String t = resultSet.getString("title");
                    outputStr += t + " ";

                    String title = resultSet.getString("author");
                    outputStr += "by " + title;
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
    }
    System.out.println(outputStr);
    return outputStr;
}

/**
 * Finds information about a student based on the provided student ID.
 *
 * @param bookID      The ID of the book being borrowed.
 * @param chosenBook  The title of the chosen book.
 * @param studID      The ID of the student.
 * @return An ArrayList containing information about the student.
 */
public ArrayList<ArrayList<String>> findStudent(String bookID, String chosenBook, String studID) {
    // SQL query to select all students
    String sql = "SELECT * FROM Student;";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("studentID");
                String contact = resultSet.getString("contact");
                if (id.equals(studID)) {
                    String nameS = resultSet.getString("studentName");
                    studInfo.add(bookID); // 0
                    studInfo.add(id); // 1
                    studInfo.add(nameS); // 2
                    studInfo.add(chosenBook); // 3
                    studInfo.add(contact); // 4
                    studentInfoBorrow.add(studInfo);
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
    }
    return studentInfoBorrow;
}

/**
 * Checks if the student has borrowed the specified book.
 *
 * @param studentInfo The information about the student and the book.
 * @return true if the student has borrowed the book, false otherwise.
 */
public boolean didBorrowBook(ArrayList<ArrayList<String>> studentInfo) {
    boolean borrowed = true;
    String sql = "SELECT * FROM BorrowedBooks;";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                for (int i = 0; i < studentInfo.size(); i++) {
                    if (!studentInfo.get(i).contains(id)) {
                        borrowed = false;
                    }
                }
            }
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    return borrowed;
}

/**
 * Calculates and returns the return date for borrowed books.
 *
 * @return The calculated return date.
 */
public Date calculateReturnDate() {
    LocalDate currentDate = LocalDate.now();
    // Add 2 weeks to the current date to get the return date
    LocalDate returnDate = currentDate.plusWeeks(2);
    Date returnDateAsDate = java.sql.Date.valueOf(returnDate);
    return returnDateAsDate;
}

    /**
     * Checks if a book is to be returned by the student.
     *
     * @param book The book to check for return.
     * @return true if the book is to be returned, false otherwise.
     */
    public boolean toReturn(Book book) 
    {
        try {
            String query = "SELECT COUNT(*) FROM IssuedBooks WHERE SN = ? AND StId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getSN());
            preparedStatement.setString(2, this.sId); // Assuming studentId is the primary key in Students table
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Return true if the count is greater than 0, indicating the book is issued to the student
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false by default or if an exception occurs
    }
}
