package com.crossover.crossbid.model;

/**
 * Created by Ibrahim on 09/06/2016.
 */
public class BidItem {

    private int id, type;
    private byte[] image;
    private String title, price, count;
    private boolean isBid;

    public BidItem() {
    }

    public BidItem(int id, int type, byte[] image, String title, String price, String count, boolean isBid) {

        super();
        this.id = id;
        this.type = type;
        this.image = image;
        this.title = title;
        this.price = price;
        this.count = count;
        this.isBid = isBid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getType() { return type; }

    public void setType(int type) { this.type = type; }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    public Boolean getIsBid() {
        return isBid;
    }

    public void setIsBid(Boolean isBid) {
        this.isBid = isBid;
    }


}
