package infrastructure;

import model.Display;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 15.10.2015.
 */
public class ArrayUserDAO implements UserDAO{


    List<User> listOfAllUsers = new ArrayList<>();
    Display display;

    public List<User> getListOfAllUsers() {
        return listOfAllUsers;
    }


    public ArrayUserDAO() {
        display = new Display();
    }

    public void deleteArray(){
        listOfAllUsers.clear();
    }

    @Override
    public boolean createUser(User user) {
        if (user != null ) {
            listOfAllUsers.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        int index =0;
        if (listOfAllUsers.size() > 0) {
           for(User u : listOfAllUsers){
               index++;

               if(u.getId() == user.getId()){
                   listOfAllUsers.remove(u);
                   listOfAllUsers.add(index,user );
                   display.updateUser(user);

                   return true;
               }
           }

            return true;
        }
        display.updateUser(user);
        return false;
    }

    @Override
    public Optional<User> getUserById(int id) {
        User user = new User();
        if(id > 1 && getListOfAllUsers().size() >= 1){
            getListOfAllUsers().contains(id);

            for(User u : getListOfAllUsers()){
                user = u;
                if(getListOfAllUsers().contains(id)){
                    return Optional.of(new User(u.getId(), u.getEmail(), u.getPassword(), u.getWorkType()));
                }
            }
           // listOfAllUsers.stream().filter(u -> u.getId() == u.getId()).forEach(display::updateUser);
            // List<User> user2 = listOfAllUsers.stream().filter((l) -> l.getId() == id).collect(Collectors.toList());

            return Optional.of(new User(user.getId(), user.getEmail(), user.getPassword(), user.getWorkType()));
        }
        display.getUserById(Optional.empty());
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        if(listOfAllUsers.size() > 1) {
        //    listOfAllUsers.forEach(user -> System.out.println(user.getId() + " " + user.getEmail() + " " + user.getPassword() + " " + user.getWorkType()));
            display.getAllUsers(listOfAllUsers);
            return getListOfAllUsers();
        }
        display.getAllUsers(listOfAllUsers);
       return null;
    }

    @Override
    public boolean deleteUser(int id) {
        if( id >= 1) {
            listOfAllUsers.remove(id);
            display.deleteUser(id);
            return true;
        }else{
            return false;
        }
    }

}
