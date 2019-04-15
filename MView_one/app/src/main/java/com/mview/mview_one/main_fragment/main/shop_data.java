package com.mview.mview_one.main_fragment.main;

public class shop_data {

    private String shopImage_data;
    private String shopName_data;
    private String shopLoc_1_data;
    private String shopLoc_2_data;

    public shop_data(String shopImage_data, String shopName_data, String shopLoc_1_data, String shopLoc_2_data) {
        this.shopImage_data = shopImage_data;
        this.shopName_data = shopName_data;
        this.shopLoc_1_data = shopLoc_1_data;
        this.shopLoc_2_data = shopLoc_2_data;
    }

    public String getShopImage_data() {
        return shopImage_data;
    }

    public void setShopImage_data(String shopImage_data) {
        this.shopImage_data = shopImage_data;
    }

    public String getShopName_data() {
        return shopName_data;
    }

    public void setShopName_data(String shopName_data) {
        this.shopName_data = shopName_data;
    }

    public String getShopLoc_1_data() {
        return shopLoc_1_data;
    }

    public void setShopLoc_1_data(String shopLoc_1_data) {
        this.shopLoc_1_data = shopLoc_1_data;
    }

    public String getShopLoc_2_data() {
        return shopLoc_2_data;
    }

    public void setShopLoc_2_data(String shopLoc_2_data) {
        this.shopLoc_2_data = shopLoc_2_data;
    }
}
