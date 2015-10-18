package model;

import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 15.10.2015.
 */
public class Display {
    public void createHeader(){
        System.out.println("\n\t\t\t\t\t\tID\tEPOST\t\t\t\tPASSORD\t\tJOBBTYPE\n******************************************************************");
    }

    public void createUser(User user){

        if(user !=null ) {
            System.out.format("Opprettet bruker: %d\t%s\t\t%s\t\t%s\n", user.getId(), user.getEmail(), user.getPassword(), user.getWorkType());
        }else
            System.err.println("Feil ingen bruker ble opprette.. ");
    }

    public void updateUser(User user){

        if(user !=null) {
            System.out.println("Oppdatert bruker " + "ID:" + user.getId() + "\tEpost: " + user.getEmail() + "\tPassord:" + user.getPassword() + "\tJobbtype:" + user.getWorkType());
        }
        else
            System.err.println("Ingen bruker ble funnet..");
    }

    public void deleteUser(int id){
        if(id > 0 ){
            System.err.println("Bruker slettet med id: " + id);
        }else
            System.err.println("Ingen bruker ble funnet...");
    }

    public void getAllUsers(List<User> allUsers) {
        if (!allUsers.isEmpty() || allUsers.size() > 0) {
        System.out.println("\n[Liste over alle brukere]:\n--------------------------------------------------------------");
            allUsers.forEach((user) -> System.out.format("%d\t%s\t\t%s\t\t%s\n", user.getId(), user.getEmail(), user.getPassword(), user.getWorkType()));
        }else
            System.err.println("Listen er tom..");
    }

    public void getUserById(Optional<User> user) {
        if(user.isPresent()) {
            System.out.println("Funnet en bruker " + "ID:" + user.get().getId() + "\tEpost: " + user.get().getEmail() + "\tPassord:" + user.get().getPassword() + "\tJobbtype:" + user.get().getWorkType() );
        }
        else
            System.err.println("Bruker ikke funnet!..");
    }
    public void deleteArray(String deleteArray){
        System.err.println(deleteArray);
    }



/// H2 DATABASE OUTPUT
    public void createUserH2(User user) {
        if (user != null) {
            System.out.format("H2 bruker opprettet: \t%d\t%s\t\t%s\t%s\n", user.getId(), user.getEmail(), user.getPassword(), user.getWorkType());
        }else
            System.err.println("Feil registrert, ingen bruker ble opprettet.");
    }

    public void updateUserH2(User user) {
        if (user !=null) {
            System.out.format("Bruker er oppdatert H2.\t\t %s\t\t%s\t\t%s\n", user.getEmail(), user.getPassword(), user.getWorkType());
        }else
            System.err.println("Ingen bruker ble oppdater , ingen bruker ble funnet..");
    }

    public void getUserByIdH2(Optional<User> userById) {
        if (userById.isPresent()) {
            System.out.println("Funnet bruker H2:" + "ID:" + userById.get().getId() + "\tEpost: " + userById.get().getEmail() + "\tPassord:" + userById.get().getPassword() + "\tJobbtype" + userById.get().getWorkType());
        }else
            System.err.println("Ingen bruker ble funnet med det id nummeret");
    }

    public void deleteUserH2(int id) {
        if (id != 0) {
            System.out.println("Bruker slettet via H2 med id: " + id);
        }else
            System.out.println("Ingen bruker kunne slettes.. ");
    }

    public void getAllUsersH2(List<User> allUsers) {
        if (allUsers.size() > 0 || !allUsers.isEmpty()) {
            allUsers.forEach((user) -> System.out.format("Listet alle brukere i H2: %d\t%s\t\t%s\t\t%s\n", user.getId(), user.getEmail(), user.getPassword(), user.getWorkType()));
        }else
            System.err.println("Databasen er tom! ");
    }

    public void dropTable(String tableName) {
        if (!tableName.isEmpty()) {
            System.out.println("Table: " + tableName + " where dropped!");
        }else
            System.err.println("Ingen tabell ble funnet..");
    }
}