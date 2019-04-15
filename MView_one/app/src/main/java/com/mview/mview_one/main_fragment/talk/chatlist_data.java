package com.mview.mview_one.main_fragment.talk;

public class chatlist_data {

    private String shopImage;
    private String shopName;
    private String shopNo;

    public chatlist_data(String shopImage, String shopName, String shopNo) {
        this.shopImage = shopImage;
        this.shopName = shopName;
        this.shopNo = shopNo;
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
}
