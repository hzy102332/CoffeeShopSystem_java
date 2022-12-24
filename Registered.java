package CoffeeShop;

import java.util.ArrayList;
import java.util.List;

public class Registered extends User {

    private LoyaltyCard card;
    private boolean vipStatus;
    private List<String> order;

    public Registered(String name, LoyaltyCard card, boolean vipStatus) {
        super(name);
        this.card = card;
        this.vipStatus = vipStatus;
        this.order = new ArrayList<>();

    }

    public void addOrder(String receipt) {
        order.add(receipt);
    }

    public String getOrder() {
        String receipt = "";
        for (String l : order) {
            receipt += l;
        }
        return receipt;
    }

    public int getStampNum() {
        return card.getStampNum();
    }

    public LoyaltyCard getCard() {
        return card;
    }

    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }
}
