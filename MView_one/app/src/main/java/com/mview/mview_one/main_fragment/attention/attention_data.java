package com.mview.mview_one.main_fragment.attention;

public class attention_data {

    private String shopImage;
    private String shopLoc_1;
    private String shopLoc_2;
    private String shopName;
    private String modelNo;
    private String shopNo;

    public attention_data(String shopImage, String shopLoc_1, String shopLoc_2, String shopName, String modelNo, String shopNo) {
        this.shopImage = shopImage;
        this.shopLoc_1 = shopLoc_1;
        this.shopLoc_2 = shopLoc_2;
        this.shopName = shopName;
        this.modelNo = modelNo;
        this.shopNo = shopNo;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
