package com.mview.mview_one.menu_fragment;

public class model_data {
    public static String Loc_1;
    public static  String Loc_2;
    public static  String pay;
    public static  String category;

    public model_data(String loc_1, String loc_2, String pay, String category) {
        Loc_1 = loc_1;
        Loc_2 = loc_2;
        this.pay = pay;
        this.category = category;
    }

    public String getLoc_1() {
        return Loc_1;
    }

    public void setLoc_1(String loc_1) {
        Loc_1 = loc_1;
    }

    public String getLoc_2() {
        return Loc_2;
    }

    public void setLoc_2(String loc_2) {
        Loc_2 = loc_2;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
