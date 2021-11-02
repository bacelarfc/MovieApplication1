package helpers;

import models.Cinema;
import models.User;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class UserInputHelper {
    //associate the movie database (?)

    private Cinema database;
    private final User wotf;

    public UserInputHelper(Cinema iDatabase, User wotf) {
        this.database = iDatabase;
        this.wotf = wotf;
    }

    //method that return Models.User object to register new Models.User
    //use scanner as parameter as well the filename;
    //Create a new user object with the information collected
    //Check if user it's unique by using the boolean method, set it to true, so we can verify if name already exists
    //if it doesn't exist I call the method saveUser and input a new user

    public static User getNewUserInput(Scanner inputScanner) {
        String name = UserInputHelper.askName(inputScanner);
        System.out.println();
        System.out.print("Please input your email: --> ");
        String email = inputScanner.next();
        System.out.print("Please input your password: --> ");
        String password = inputScanner.next();
        return new User(name, email, password);
    }

    public static boolean checkIfUserIsRegistered(Scanner inputScanner) {
        System.out.println("Are you a registered user or not (Y/N) ?");
        String userChoice = inputScanner.next();
        while (!userChoice.equalsIgnoreCase("Y") && !userChoice.equalsIgnoreCase("N")) {
            System.out.println("--- ERROR: Invalid input, please try again!");
            System.out.println("Are you a registered user or not (Y/N) ?");
            userChoice = inputScanner.next();
        }
        return userChoice.equalsIgnoreCase("Y");
    }

    public static String askName(Scanner inputScanner) {
        System.out.print("Please input your username: --> ");
        return inputScanner.next();
    }
}
