import domain.UserService;
import model.Type;
import model.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;

/**
 * Created by Paul on 15.10.2015.
 */
public class Main {
    /*
        @Inject
        private Service service;

        @Inject @ArrayListQualifier
        private ServicqualifierService;
    /*
        public void callService(){
            service.execute();
            qualifierService.execute();
        }
    */
    public static void main(String[] args) {

        WeldContainer container = new Weld().initialize();
        Instance<UserService> inst = container.instance().select(UserService.class);
        UserService userService = inst.get();

      //  UserService userService = container.instance().select(UserService.class).get();

        createArrayList(userService);
        System.out.println("---------------------------------------------------------------------\n");
        createH2(userService);

        container.instance().destroy(userService);
    }

    public static void createArrayList(UserService userService) {
        System.out.println("--------ARRAYLIST PRINTOUT:-------------------------------------");
        userService.createUser(1, "ola@yahoo.no", "passord8", Type.STUDENT);
        userService.createUser(2, "Gun@yahoo.no", "passord2", Type.TEACHER);
        userService.createUser(3, "kei@yahoo.no", "passord3", Type.STUDENT);
        userService.createUser(4, "lars@yahoo.no", "passord4", Type.TEACHER);
        userService.createUser(5, "silje@yahoo.no", "passord5", Type.STUDENT);
        userService.getAllUsers();
        System.out.println("\n");
        userService.getUserByID(4);
        userService.updateUser(4, "erik@yahoo.no", "passord4", Type.STUDENT);
        userService.getAllUsers();
        userService.deleteAUser(4);
        userService.getAllUsers();
        userService.deleteArrayList();
        userService.getAllUsers();

        //  userService.getAllUsers();
    }
    public static void createH2(UserService userService) {
        System.out.println("-----H2 PRINTOUT:-----------------------------------------------");

        userService.createUserH2(3, "ola@yahoo.no", "passord8", Type.STUDENT);
        userService.createUserH2(4, "Gun@yahoo.no", "passord2", Type.TEACHER);
        userService.createUserH2(5, "kei@yahoo.no", "passord3", Type.STUDENT);
        userService.createUserH2(6, "lars@yahoo.no", "passord4", Type.TEACHER);
        userService.createUserH2(7, "silje@yahoo.no", "passord5", Type.STUDENT);

        System.out.println("\n");
        userService.getAllUsersH2();
        userService.updateUserH2(new User(2, "Gun@yahoo.no", "passordrr", Type.STUDENT));
        userService.getUserByIDH2(4);
        System.out.println("\n");
        userService.deleteAUserH2(4);
        userService.getAllUsersH2();

    }

}