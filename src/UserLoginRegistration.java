import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class UserLoginRegistration {

    private InformationalDatabase database;

    public UserLoginRegistration(InformationalDatabase database) {
        this.database = database;
    }

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
            saveUserToFile(user, fileName);
            database.addUser(user);
        }
        return user;
    }

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

    public boolean checkIfUniqueUser(String email) {
        if (database.getUsers().size() == 0) {
            return true;
        }
        for (User user : database.getUsers()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }


}
