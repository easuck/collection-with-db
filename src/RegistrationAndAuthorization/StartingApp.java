package RegistrationAndAuthorization;

import DAO.UserDAO;
import Utility.ConsoleManager;

import java.util.Scanner;

public class StartingApp {

    private ConsoleManager consoleManager;
    private UserDAO userDAO;

    public StartingApp(UserDAO userDAO){
        consoleManager = new ConsoleManager();
        this.userDAO = userDAO;
    }



    public void start(){
        int option;
        while(true){
            while(true){
                consoleManager.println("1. Sign up");
                consoleManager.println("2. Sign in");
                consoleManager.print("choose what you want to do: ");
                try{
                    option = Integer.parseInt(consoleManager.readString());
                    if (!(option == 1 || option == 2)) throw new Exception();
                    break;
                }
                catch(NumberFormatException e){
                    consoleManager.println("enter a number");
                }
                catch(Exception e){
                    consoleManager.println("number must be either 1 or 2");
                }
            }
            if (option == 1){
                consoleManager.print("create username: ");
                String username = consoleManager.readString();
                consoleManager.print("create password: ");
                String password = consoleManager.readString();
                userDAO.signUp(username, password);
            }
            else{
                consoleManager.print("enter your username: ");
                String username = consoleManager.readString();
                consoleManager.print("enter your password: ");
                String password = consoleManager.readString();
                int userId = userDAO.singIn(username, password);
                if (userId != -1){
                    UserDAO.setUserId(userId);
                    break;
                }
                else{
                    consoleManager.println("incorrect username or password/user is not registered");
                }
            }
        }

    }
}
