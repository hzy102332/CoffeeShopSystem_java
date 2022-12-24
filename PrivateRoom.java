package CoffeeShop;

import com.sun.jdi.PrimitiveValue;

import java.math.RoundingMode;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class PrivateRoom {

    private List<List<String>> Room;
    private Date time;
    private String ReservedName;
    private String  Phone;
    private String duration;
    private String  People_num;
    public PrivateRoom(){
        this.Room = new ArrayList<>();
    }
    public PrivateRoom(Date time, String reservedName,String number, String duration, String phone) {
        this.time = time;
        this.ReservedName = reservedName;
        this.Phone = phone;
        this.duration = duration;
        this.People_num = number;
    }

    /***
     *
     * @param room
     * 0: date
     * 1: name
     * 2: People number
     * 3: duration
     * 4: phone
     */
    public void addReservation(PrivateRoom room){
        List<String> temp = new ArrayList<>();
        temp.add(String.valueOf(room.getTime()));
        temp.add(room.getReservedName());
        temp.add(room.getPeopleNumber());
        temp.add(room.getDuration());
        temp.add(room.getPhone());
        Room.add(temp);
    }
    public void deleteReservation(String[] room){
        for (int i = 0; i < Room.size(); i++){
            List<String> list = Room.get(i);
            if(list.get(0).equals(room[0].substring(5))){
                if (list.get(1).equals(room[1].substring(5))){
                    if (list.get(3).equals(room[3].substring(9))) {
                        Room.remove(i);
                        return;
                    }
                }
            }
        }
    }

    public String getReservedInfo(){
        if(Room.isEmpty())
            return "There is no reservation !";
        String temp = "";
        for (List<String > i : Room){
            temp += i + "\n";
        }
        return  temp;
    }

    public String getPhone() {
        return Phone;
    }

    public List<List<String>> getRoom() {
        return Room;
    }
    public String getTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(time);
    }
    public String getReservedName() {
        return ReservedName;
    }
    public String getPeopleNumber() {
        return People_num;
    }
    public String getDuration() {
        return duration;
    }
}
