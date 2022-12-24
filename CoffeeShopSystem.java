package CoffeeShop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoffeeShopSystem {

    public static User user;
    public static Product product;
    public static PrivateRoom privateRoom;

    public static boolean isregister = false;

    public static void main(String[] args) throws ParseException {

        user = new User();
        product = new Product();
        privateRoom = new PrivateRoom();

        Product cappuccino =            new Product("Cappuccino",0, 2.0,24,161);
        Product iced_cap =              new Product("Iced Cappuccino",1, 1.0,26,131);
        Product espresso =              new Product("Espresso",2, 2.5,15,11);
        Product latte =                 new Product("Latte",3, 3.0,12,23);
        Product ice_Latte =             new Product("Ice Latte",4, 1.5,8,32);
        Product coffee_cake =           new Product("Coffee Cake",5, 1.0,17,75, 1000);
        Product red_velvet =            new Product("Red Velvet",6, 1.5,5,127, 1000);
        Product boston_cream_pie =      new Product("Boston Cream Pie",7, 2.5,3,68, 1000);
        Product black_forest_cake =     new Product("Black forest Cake",8, 3.5,7,54, 1000);
        Product lagos_chocolate_cake =  new Product("Lagos Chocolate Cake",9, 3.0,6,133, 1000);
        product.addProducts(cappuccino);
        product.addProducts(iced_cap);
        product.addProducts(espresso);
        product.addProducts(latte);
        product.addProducts(ice_Latte);
        product.addProducts(coffee_cake);
        product.addProducts(red_velvet);
        product.addProducts(boston_cream_pie);
        product.addProducts(black_forest_cake);
        product.addProducts(lagos_chocolate_cake);


//        System.out.println(product.getAllProducts().keySet());

        Registered p = new Registered("1",new LoyaltyCard("1","34343",0),false);
        String receipt = "\t\n Cafe Management Systems:\n\n" +
                "Reference:\t\t\t4835" +
                "\n==============================\t " +
                "\nCappuccino:\t\t\t3" +
                "\nIced Lattle:\t\t\t5" +
                "\n================================\t\t" +
                "\nCoffee Cake:\t\t\t1"+
                "\nRed Velvet Cake:\t\t2" +
                "\n================================\t" +
                "\nTax:\t\t\t$ 0.21" +
                "\nSub Total:\t\t\t$ 20.50" +
                "\nTotal:\t\t\t$ 20.71" +
                "\n================================\t" +
                "\nDate: 2022-12-02\tTime: 18:34:45" +
                "\n\nThank you";
        p.addOrder(receipt);
        Registered p2 = new Registered("2",new LoyaltyCard("Jack","34343",0),true);
        Registered p3 = new Registered("3",new LoyaltyCard("3","34343",10),true);

        user.addUser(p);
        user.addUser(p2);
        user.addUser(p3);

        Date date = new SimpleDateFormat("yyyy/MM/dd").parse("2022/12/23");
        PrivateRoom room = new PrivateRoom(date, "Thomas Selig", "10", "13:00","123456789");
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse("2022/12/23");
        PrivateRoom room1 = new PrivateRoom(date, "Thomas Selig", "20", "15:00","123456789");

        privateRoom.addReservation(room1);
        privateRoom.addReservation(room);


        Admin admin = new Admin("1");
        user.addUser(admin);

//        System.out.println(user.getRegistered("Tom"));

        LogInMenu lg = new LogInMenu(user);
     }
}
