package com.mview.mview_one.main_fragment.event;

public class event_data {
    private String shopNo;
    private String shopImage;
    private String shopName;
    private String shopLoc_1;
    private String shopLoc_2;
    private String eventTitle;
    private String modelPrice;

    public event_data(String shopNo, String shopImage, String shopName, String shopLoc_1, String shopLoc_2, String eventTitle, String modelPrice) {
        this.shopNo = shopNo;
        this.shopImage = shopImage;
        this.shopName = shopName;
        this.shopLoc_1 = shopLoc_1;
        this.shopLoc_2 = shopLoc_2;
        this.eventTitle = eventTitle;
        this.modelPrice = modelPrice;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLoc_1() {
        return shopLoc_1;
    }

    public void setShopLoc_1(String shopLoc_1) {
        this.shopLoc_1 = shopLoc_1;
    }

    public String getShopLoc_2() {
        return shopLoc_2;
    }

    public void setShopLoc_2(String shopLoc_2) {
        this.shopLoc_2 = shopLoc_2;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(String modelPrice) {
        this.modelPrice = modelPrice;
    }
}
