package example.com.shujiaapplication.ui;

public class Discount {

    public int imageID;
    public String discountMoney;
    public String disCouuntDate;
    public String discountStore;

    public Discount(int i,String m,String s,String d){
        this.imageID = i;
        this.discountMoney = m;
        this.discountStore = s;
        this.disCouuntDate = d;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getDiscountMoney() {
        return discountMoney;
    }

    public String getDiscountStore() {
        return discountStore;
    }

    public String getDisCouuntDate() {
        return disCouuntDate;
    }

    public void setDiscountMoney(String discountMoney) {
        this.discountMoney = discountMoney;
    }

    public void setDiscountStore(String discountStore) {
        this.discountStore = discountStore;
    }

    public void setDisCouuntDate(String disCouuntDate) {
        this.disCouuntDate = disCouuntDate;
    }
}
