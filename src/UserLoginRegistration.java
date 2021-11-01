import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class UserLoginRegistration {
    //associate the movie database (?)

    private InformationalDatabase database;

    public UserLoginRegistration(InformationalDatabase database) {

        this.database = database;
    }

    //method that return User object to register new User
    //use scanner as paremeter as well the filename;
    //Create a new user object with the information collected
    //Check if user its unique by using the boolean method, set it to true so we can verify if name already exists
    //if it doesnt exist I call the method saveUser and input a new user
    public User registerNewUser(Scanner inputScanner, String fileName) {
        System.out.print("Please input your name: --> ");
        String name = inputScanner.nextLine();
        name = inputScanner.nextLine();
        System.out.println();
        System.out.print("Please input your email: --> ");
        String email = inputScanner.next();
        System.out.print("Please input your password: --> ");
        String password = inputScanner.next();
        System.out.println();
        User user = new User(name, email, password);
        if (checkIfUniqueUser(name) == true) {
            System.out.println(" --- SORRY, THIS EMAIL IS ALREADY TAKEN! --- ");
        } else {
            //call save user method and its parameters
            //use the database and add the user to be related to it
            saveUserToFile(user, fileName);
            database.addUser(user);
        }
        return user;
    }
//a method that has User as return type
    //has a scanner to get input and an user that we called active user
    //create variables to save the information
    //then create a boolean variable for find an user and set it to false
    //loop to get users saved into the database, set the active user to user and set it to true to end
    public User login(Scanner inputScanner, User activeUser) {
        System.out.print("Please input your email: --> ");
        String email = inputScanner.next();
        System.out.print("Please input your password: --> ");
        String password = inputScanner.next();
        System.out.println();
        boolean foundUser = false;
        for (User user : database.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                activeUser = user;
                foundUser = true;
            }
        }
        if (foundUser == true) {
            System.out.println(" --- GLAD TO SEE YOU BACK, " + activeUser.getName() + " ---\n");
        } else {
            System.out.println(" --- EMAIL OR PASSWORD DO NOT MATCH, TRY AGAIN ---");
            login(inputScanner, activeUser);
        }
        return activeUser;
    }
// a method to save user to file
    //2 parameters: user and the file name
    //create a variable to save user info using the getters from the user class
    //write it to file
    public void saveUserToFile(User user, String USER_FILE_NAME) {
        try {
            FileWriter fileWriter = new FileWriter(USER_FILE_NAME, true);
            String userInfo = user.getName() + "," + user.getEmail() + "," + user.getPassword() + "\n";
            fileWriter.write(userInfo);
            fileWriter.close();
        } catch (Exception exception) {
            System.out.println("ERROR: wrong file name or missing file!");
        }
    }

    public void readUsersFromFile(String USER_FILE_NAME) {
        try {
            File file = new File(USER_FILE_NAME);
           if (file.exists())
            {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    String[] info = line.split(",");
                    String name = info[0];
                    String email = info[1];
                    String password = info[2];
                    User user = new User(name, email, password);
                    database.addUser(user);
                }
            } else {
                System.out.println("FILE DOESNT EXIST");
            }

        } catch (Exception exception) {
            System.out.println("ERROR: wrong file name or missing file!");
        }
    }

    public boolean checkIfUniqueUser(String name) {
        if (database.getUsers().size() == 0) {
            return true;
        }
        for (User user : database.getUsers()) {
            if (user.getEmail().equals(name)) {
                return false;
            }
        }
        return true;
    }


}
