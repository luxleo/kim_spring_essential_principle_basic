package hello.core.order;

public class Order {
    private Long memId;
    private String itemName;
    private int itemPrice;
    private int discountedPrice;

    public Order(Long memId, String itemName, int itemPrice, int discountedPrice) {
        this.memId = memId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountedPrice = discountedPrice;
    }
    public int calcPrice(){
        return itemPrice - discountedPrice;
    }

    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemId) {
        this.itemName = itemId;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memId=" + memId +
                ", itemId='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
