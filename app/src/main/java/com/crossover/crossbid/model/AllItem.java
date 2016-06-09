package com.crossover.crossbid.model;

/**
 * Created by Ibrahim on 09/06/2016.
 */
public class AllItem {

    private int id;
    private byte[] image;
    private String name;
    private float price;
    private boolean isBid;

    public AllItem() {
    }

    public AllItem(int id, byte[] image, String name, float price, boolean isBid) {

        super();
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.isBid = isBid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public Boolean getIsBid() {
        return isBid;
    }

    public void setIsBid(Boolean isBid) {
        this.isBid = isBid;
    }


}
