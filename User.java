package CoffeeShop;

import java.util.*;

public class User {


    private HashMap<String, Registered> RegisterList;
    private HashMap<String, Admin> AdminList;
    private String name;

    public User() {
        this.RegisterList = new HashMap<>();
        this.AdminList = new HashMap<>();
    }

    public User(String name) {
        this.name = name;
//        this.gender = gender;
    }

    public void addUser(Admin user) {
        AdminList.put(user.getName(), user);
    }

    public void addUser(Registered user) {
        RegisterList.put(user.getName(), user);
    }

    public boolean CheckAdminUser(String user) {
        return AdminList.containsKey(user);
    }

    public boolean CheckRegisteredUser(String user) {
        return RegisterList.containsKey(user);
    }

    public void deleteUser(String name) {
        RegisterList.remove(name);
    }

    public HashMap<String, Registered> getRegisteredList() {
        return RegisterList;
    }

    public Registered getRegistered(String name) {
        return RegisterList.get(name);
    }

    public String getName() {
        return name;
    }

}
