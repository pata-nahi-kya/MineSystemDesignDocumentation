package ChainOfResponsibilityPattern;

import java.util.HashMap;
import java.util.Map;

// in this example, i will make one database and a chain of responsibility pattern to handle different types of requests to the database for verification like user exists , password correct , etc.
public class COR {
    public static void main(String[] args) {
        Database db = new Database();
        Handler handler = new UserExistsHandler(db);
        handler.setNext(new ValidPasswordHandler(db)).setNext(new RoleCheckHandler());
        AuthService as = new AuthService(handler);
        as.logIn("username", "password");
    }
}

// Simple in-memory database simulation
class Database {

    private final Map<String, String> users;

    public Database() {
        users = new HashMap<>();
        users.put("admin_username", "admin_password");
        users.put("user_username", "user_password");
    }

    public boolean isValidUser(String username) {
        return users.containsKey(username);
    }

    public boolean isValidPassword(String username, String password) {
        return users.get(username).equals(password);
    }

}

// Abstract handler

abstract class Handler {
    protected Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return this;
    }

    // here , there will be code which handles request like verifing password
    public abstract boolean handle(String username , String password);

    // this will assign work to next handler but if there is no next handler it just return true 
    protected boolean handleNext(String username , String password){
        if(next == null) return true;
        return next.handle(username, password);
    }
}

// concrete handler
class RoleCheckHandler extends Handler {

    @Override
    public boolean handle(String username, String password) {
        if ("admin_username".equals(username)) {
            System.out.println("Loading Admin Page...");
            return true;
        }
        System.out.println("Loading Default Page...");
        return handleNext(username, password);
    }

}

class UserExistsHandler extends Handler {

    private final Database database;

    public UserExistsHandler(Database database) {
        this.database = database;
    }

    @Override
    public boolean handle(String username, String password) {
        if (!database.isValidUser(username)) {
            System.out.println("This username is not registered!");
            System.out.println("Sign Up to our app now!");
            return false;
        }
        return handleNext(username, password);
    }

}

class ValidPasswordHandler extends Handler {

    private final Database database;

    public ValidPasswordHandler(Database database) {
        this.database = database;
    }

    @Override
    public boolean handle(String username, String password) {
        if (!database.isValidPassword(username, password)) {
            System.out.println("Wrong Password!");
            return false;
        }
        return handleNext(username, password);
    }

}

// now we will have authentication service class which will handle and invoke this (handle method)authentication part , it takes handler as parameter , 


class AuthService{
    private Handler handler;
    public AuthService(Handler handler){
        this.handler = handler;
    }

    public boolean logIn(String username , String password){
        if(handler.handle(username, password)){
            System.out.println("authentication successful");
            return true;
        }
        return false;
    }
}

