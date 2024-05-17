/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.labs;

/**
 *
 * @author grech
 */
public class Factory_LibraryEntity implements Factory_Object {

    private String id;

    @Override
    public Object createObject(String... params) {

        switch (params.length) {
//            case "Book":
//                if (params.length != 3) {
//                    throw new IllegalArgumentException("Invalid number of parameters for creating a Book.");
//                }
//                return new Book(params[0], params[1], params[2]);
            case 1:
             
                //librarian ID
                return new Librarian(params[0]);
//            case "Patron":
//                if (params.length != 2) {
//                    throw new IllegalArgumentException("Invalid number of parameters for creating a Patron.");
//                }
//                return new Patron(params[0], params[1]);
            default:
                throw new IllegalArgumentException("Factory: Invalid number of parameter for creating library entities " + params.length);
        }
    }

}
