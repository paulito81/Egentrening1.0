package domain;

import infrastructure.*;
import model.Display;
import model.Type;
import model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 15.10.2015.
 */
public class UserService {


    @H2DAOQualifier
    H2UserDAO h2UserDAO;

    @ArrayListQualifier
    ArrayUserDAO arrayUserDAO;

    UserDAO userDAO;

    //PRINT OUT
    Display display = new Display();


    UserService() {
        h2UserDAO = new H2UserDAO();
        arrayUserDAO = new ArrayUserDAO();
        display.createHeader();
    }




    /*
        ARRAYLIST
        CRUD OPPERATIONS

     */
    public void createUser(int id, String email, String password, Type workType) {
        if (id != 0 || email != null || password != null || workType != null) {

            User user = new User(id, email, password, workType);
            display.createUser(user);
            arrayUserDAO.createUser(user);
        } else
            System.out.println("Your value is invalid it contains 0!");
    }

    public boolean updateUser(User user) {
        if (user != null) {
            arrayUserDAO.updateUser(user);
            return true;
        }

        return false;

    }

    public boolean updateUser(int id, String email, String password, Type workType) {
        if (id != 0 || email != null || password != null || workType != null) {

            User user = new User(id, email, password, workType);
           // arrayUserDAO.updateUser(user);
            updateUser(user);
            return true;
        } else
            display.updateUser(null);
        return false;
    }

    public Optional<User> getUserByID(int id) {
        Optional<User> user;
        if (id != 0) {

            user = arrayUserDAO.getUserById(id);
            display.getUserById(user);

            return arrayUserDAO.getUserById(id);
        } else
            return null;
    }

    public List<User> getAllUsers() {
        return arrayUserDAO.getAllUsers();
    }

    public void deleteArrayList() {
        arrayUserDAO.deleteArray();
        display.deleteArray("Listen ble slettet");
    }

    public boolean deleteAUser(int id) {
        return arrayUserDAO.deleteUser(id);
    }


    /*
        H2DATABASE
        CRUD OPERATIONS

     */
    public boolean createUserH2(int id, String email, String password, Type workType) {

        if (id != 0 || email != null || password != null || workType != null) {
            User user = new User(id, email, password, workType);

            h2UserDAO.createUser(user);
            if(!h2UserDAO.getAllUsers().isEmpty()) {
                display.createUserH2(user);
            }

            return true;

        } else
            return false;
    }

    public boolean updateUserH2(User user) {
        if (user != null) {

            h2UserDAO.updateUser(user);
            display.updateUserH2(user);
            return true;
        } else
            return false;
    }

    public Optional<User> getUserByIDH2(int id) {
        if (id != 0) {
            display.getUserByIdH2(h2UserDAO.getUserById(id));
            return h2UserDAO.getUserById(id);
        } else
            return null;
    }

    public List<User> getAllUsersH2() {

        display.getAllUsersH2(h2UserDAO.getAllUsers() );

        return h2UserDAO.getAllUsers();

    }

    public boolean deleteAUserH2(int id) {
        display.deleteUserH2(id);
        return id != 0 && h2UserDAO.deleteUser(id);
    }

    public void dropTable(String tableName) {

        H2UserDAO h2UserDAO = new H2UserDAO();
        h2UserDAO.dropTable(tableName);
        display.dropTable(tableName);

    }
    public void closeConnection(){
        h2UserDAO.closeConnectionToH2();
    }


}
