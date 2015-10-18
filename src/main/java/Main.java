import domain.UserService;
import model.Type;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

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
       // Instance<Main> main = container.instance().select(Main.class);
       // main.get().callService();
        UserService userService = container.instance().select(UserService.class).get();

       // createArrayList(userService);
        createH2(userService);

        container.instance().destroy(userService);
    }

    public static void createArrayList(UserService userService) {
        System.out.println("<<<<<<ARRAYLIST PRINTOUT:>>>>>>");
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
        System.out.println("-----H2 PRINTOUT:------");

        userService.createUserH2(20, "ola@yahoo.no", "passord8", Type.STUDENT);
        userService.createUserH2(21, "Gun@yahoo.no", "passord2", Type.TEACHER);
        userService.createUserH2(22, "kei@yahoo.no", "passord3", Type.STUDENT);
        userService.createUserH2(23, "lars@yahoo.no", "passord4", Type.TEACHER);
        userService.createUserH2(24, "silje@yahoo.no", "passord5", Type.STUDENT);
        System.out.println("\n");
       // userService.closeConnection();
        userService.getAllUsersH2();
      /*
        System.out.println("\n");
        userService.updateUserH2(new User(20, "Gun@yahoo.no", "passordrr", Type.STUDENT));
        userService.getUserByIDH2(20);
        System.out.println("\n");

        userService.getAllUsersH2();
        System.out.println("\n");
        userService.deleteAUser(20);
        */
       // userService.getAllUsersH2();

       // userService.closeConnection();
    }

}