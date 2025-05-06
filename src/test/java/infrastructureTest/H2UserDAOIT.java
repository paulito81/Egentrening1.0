package infrastructureTest;

import infrastructure.H2UserDAO;
import model.Type;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Paul on 15.10.2015.
 */
public class H2UserDAOIT {
    private H2UserDAO h2UserDAO;


    @Before
    public void setUp(){
        h2UserDAO = new H2UserDAO();

    }
    @After
    public void tearDown(){
        h2UserDAO.closeConnectionToH2();
    }

    @Test
    public void createANewUser(){
        User user = new User("ole@yahoo.no", "passord", Type.STUDENT);
        boolean created = h2UserDAO.createUser(user);
        Optional<User> optionalUser = h2UserDAO.getUserById(2);
        System.out.println(
                "1) Test opprettelse av ´ny bruker: \tID: " +
                        optionalUser.get().getId() + "\tEpost: " +
                        optionalUser.get().getEmail() + "\tPassord: "+
                        optionalUser.get().getPassword() + "\tJobb: " +
                        optionalUser.get().getWorkType());
        Assert.assertTrue(created);
    }

    @Test
    public void updateAUser(){
        Optional<User> user = h2UserDAO.getUserById(1);
        if(user.isPresent()) {
            System.out.println("2) Test oppdatering av ´bruker X´: \tID: " + user.get().getId() + "\tEpost: " + user.get().getEmail() + "\tPassord: "+ user.get().getPassword()+ "\tJobb: " + user.get().getWorkType());
        }

        boolean isUpdated = h2UserDAO.updateUser(new User(1, "fredrik@yahoo.no", "gutt1234", Type.STUDENT));
        Assert.assertTrue(isUpdated);
    }

    @Test
    public void getAUserById(){
        Optional<User> user = h2UserDAO.getUserById(1);
        if(user.isPresent() ){
            System.out.println("3) Test hente bruker med ´ID X´: \tID: " + user.get().getId()  + "\tEpost: " + user.get().getEmail()+ "\tPassord: "+ user.get().getPassword()+ "\tJobb: " + user.get().getWorkType());
        }
        user = h2UserDAO.getUserById(1);
        Assert.assertNotNull(user);
    }
    @Test
    public void getAllUsers(){
        h2UserDAO.createUser(new User( "ole@yahoo.no", "passord0", Type.STUDENT));
        h2UserDAO.createUser(new User( "per@yahoo.no", "passord2", Type.TEACHER));
        h2UserDAO.createUser(new User( "knu@yahoo.no", "passord3", Type.TEACHER));
        h2UserDAO.createUser(new User( "rut@yahoo.no", "passord4", Type.STUDENT));

        for (User user : h2UserDAO.getAllUsers()) {
            System.out.println("4) Teste hente alle brukere´ \t\tID: " + user.getId()  + "\tEpost: " + user.getEmail()+ " Passord: "+ user.getPassword()+ "\tJobb: " + user.getWorkType());
        }
        Assert.assertTrue(!h2UserDAO.getAllUsers().isEmpty());


    }
    @Test
    public void getAllUsersIsCopied(){
        List<User> listOfUsers = new ArrayList<>();
        h2UserDAO.createUser(new User( "ole@yahoo.no", "passord0", Type.STUDENT));
        h2UserDAO.createUser(new User( "per@yahoo.no", "passord2", Type.TEACHER));
        h2UserDAO.createUser(new User("knu@yahoo.no", "passord3", Type.TEACHER));
        h2UserDAO.createUser(new User("rut@yahoo.no", "passord4", Type.STUDENT));

        listOfUsers.addAll(h2UserDAO.getAllUsers().stream().collect(Collectors.toList()));
        System.out.println("5) Teste at størrelsen på fil er like stor som den som ble kopiert\t\tH2-DB:  " + h2UserDAO.getAllUsers().size() +"\tLokal List<User>: " + listOfUsers.size());

        Assert.assertEquals(h2UserDAO.getAllUsers().size(), listOfUsers.size());

    }

    @Test
    public void deleteAUser() {
        h2UserDAO.createUser(new User( 1,"ole@yahoo.no", "passord0", Type.STUDENT));
        Optional<User> userOpt = h2UserDAO.getUserById(1);
        userOpt.ifPresent(System.out::println);

        boolean deleted = h2UserDAO.deleteUser(1);
        Assert.assertTrue(deleted);
    }
    @Test
    public void deleteTable(){

        h2UserDAO.createUser(new User("ole@yahoo.no", "passord0", Type.STUDENT));
        h2UserDAO.createUser(new User( "per@yahoo.no", "passord2", Type.TEACHER));
        h2UserDAO.createUser(new User( "knu@yahoo.no", "passord3", Type.TEACHER));
        h2UserDAO.createUser(new User( "rut@yahoo.no", "passord4", Type.STUDENT));

        boolean isDeletedTable = h2UserDAO.dropTable("User");
        Assert.assertTrue(isDeletedTable);


    }

}