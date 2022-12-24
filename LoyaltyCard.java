package CoffeeShop;

public class LoyaltyCard {
    private String owner;
    private String ID;
    private int StampNum;
    private float discount = 0.95F;
    public LoyaltyCard(String owner,String ID, int stampNum) {
        this.owner = owner;
        this.ID = ID;
        this.StampNum = stampNum;
    }

    public void addStamp() {
        StampNum++;
    }
    public boolean FreeDrink() {
        boolean status = false;
        if (getStampNum() >= 10) {
            status = true;
        }
        return status;
    }
    public void getFreeDrink() {
        StampNum = StampNum - 10;
    }
    public String getOwner() {
        return owner;
    }
    public int getStampNum() {
        return StampNum;
    }
    public float getDiscount() {
        return discount;
    }
}
