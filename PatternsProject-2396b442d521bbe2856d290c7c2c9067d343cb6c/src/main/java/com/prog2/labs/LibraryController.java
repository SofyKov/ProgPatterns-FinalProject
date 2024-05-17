/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grech
 */
public class LibraryController 
{
    //Entities
    Librarian librarian;
    Book book;
    Student student;
    public Connection connection;

    private static LibraryController instance;

 /**
 * Constructs a new LibraryController object.
 * Initializes the database connection and creates instances of Book, Student, and Librarian.
 */
public LibraryController() {
    try {
        // Attempt to establish a connection to the SQLite database
        this.connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
    } catch (SQLException ex) {
        // Log any SQLException that occurs during database connection setup
        Logger.getLogger(LibraryController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    // Initialize instances of Book, Student, and Librarian classes
    this.book = new Book();
    this.student = new Student();
    this.librarian = new Librarian();
}

    // Static method to get the singleton instance
    public static LibraryController getInstance()
    {
        if (instance == null) 
        {
            instance = new LibraryController();
        }
        return instance;
    }

    public void setLibrarian(Librarian librarian) 
    {
        this.librarian = librarian;
    }

    public void setStudent(Student student) 
    {
        this.student = student;
    }


 /**
 * Creates an entity based on the specified person type and parameters.
 *
 * @param person The type of person/entity to create ("Librarian" or "Student").
 * @param params The parameters required to create the entity. The number and format of parameters vary based on the person type.
 *               For a Librarian, only one parameter (ID) is required.
 *               For a Student, three parameters (ID, name, and contact) are required.
 * @return The created entity object.
 * @throws IllegalArgumentException If the person type is invalid or if the number of parameters does not match the required number for the specified person type.
 */
public Object createEntity(String person, String... params) 
{
    // Factory patterns:
    Factory_LibraryEntity entityFactory = new Factory_LibraryEntity();

    // Print information for debugging purposes
    System.out.println(person + " " + params[0] + " " + params.length);

    // Create entity based on the specified person type
    switch (person) 
    {
        case "Librarian":
            // Create a Librarian entity
            // Only one parameter (ID) is required
            if (params.length != 1) 
            {
                throw new IllegalArgumentException("Invalid number of parameters to make Librarian: " + params.length);
            }
            return entityFactory.createObject(params[0]);

        case "Student":
            // Create a Student entity
            // Three parameters (ID, name, and contact) are required
            if (params.length != 3) 
            {
                throw new IllegalArgumentException("Invalid number of parameters to make Student: " + params.length);
            }
            return entityFactory.createObject(params[0], params[1], params[2]);

        default:
            // Throw an exception if the person type is invalid
            throw new IllegalArgumentException("Invalid entity type: " + person);
    }
}


    public Librarian getLibrarian()
    {
        return librarian;
    }

/**
 * Adds a new book to the library database with the provided details.
 *
 * @param SN        The serial number of the book.
 * @param title     The title of the book.
 * @param author    The author of the book.
 * @param publisher The publisher of the book.
 * @param price     The price of the book.
 * @param quantity  The quantity of the book available in the library.
 * @param issued    The number of copies of the book currently issued to students.
 */
public void addBook(String SN, String title, String author, String publisher, float price, int quantity, int issued) {
    // Create a new Book object with the provided details
    Book book = new Book(SN, title, author, publisher, price, quantity);

    // SQL query to select all books
    String query = "SELECT * FROM Book;";

    try 
    {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currDate = new java.sql.Date(calendar.getTime().getTime());

        // SQL query to insert a new book record into the database
        String sql = "insert into book (SN, title, author, publisher, price, quantity, issued, addedDate) values (?, ?, ?, ?, ?, ?, ?, ?)";

        // Prepare the SQL statement
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, SN);
        ps.setString(2, title);
        ps.setString(3, author);
        ps.setString(4, publisher);
        ps.setFloat(5, price);
        ps.setInt(6, quantity);
        ps.setInt(7, 0); // Setting issued to 0 for a newly added book

        // Get the current local date-time
        LocalDateTime currentTime = LocalDateTime.now();

        // Define a custom date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the current local date-time using the custom format
        String formattedTime = currentTime.format(formatter);

        // Set the addedDate parameter in the prepared statement
        ps.setString(8, formattedTime);

        // Execute the SQL statement to add the book record
        ps.execute();
        
        // Close the database connection
        connection.close();
        
        // Print the prepared statement for debugging
        System.out.println(ps.toString());
    } catch (Exception e) {
        // Print any exception that occurs during the process
        System.out.println(e.getMessage());
    }
}

    /**
 * Verifies login credentials for a librarian.
 *
 * @param id The ID of the librarian.
 * @param pw The password of the librarian.
 * @return true if the login credentials are valid, false otherwise.
 */
public boolean verifyLogin(String id, String pw) {
    Librarian authenticate = new Librarian();
    return authenticate.verifyLogin(id, pw);
}

/**
 * Retrieves a map containing information about all issued books.
 *
 * @return A map containing the details of all issued books in the library.
 */
public Map<String, String> viewIssuedBooks() 
{
    Book getBookdb = new Book();
    return getBookdb.viewIssuedBooks();
}

/**
 * Retrieves a map containing information about all books in the library catalog.
 *
 * @return A map containing the details of all books in the library catalog.
 */
public Map<String, String> viewAllBooks()
{
    Book getBookdb = new Book();
    // Print the set of entries for debugging purposes
    System.out.println(getBookdb.viewCatalog().entrySet());
    return getBookdb.viewCatalog();
}

/**
 * Adds a new book to the library catalog.
 *
 * @param SN       The serial number of the book.
 * @param title    The title of the book.
 * @param author   The author of the book.
 * @param publisher The publisher of the book.
 * @param price    The price of the book.
 * @param quantity The quantity of the book available in the library.
 * @param issued   The number of copies of the book currently issued to students.
 */
public void addBooks(String SN, String title, String author, String publisher,
                     float price, int quantity, int issued) 
{
    Book getBookdb = new Book();
    getBookdb.addBook(SN, title, author, publisher, price, quantity, issued);
}
}
