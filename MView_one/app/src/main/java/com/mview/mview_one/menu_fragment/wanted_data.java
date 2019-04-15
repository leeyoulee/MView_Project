package com.mview.mview_one.menu_fragment;

public class wanted_data {

    public static String Loc_1;
    public static  String Loc_2;
    public static String category;
    public static String pay;


    public wanted_data(String loc_1, String loc_2, String category, String pay) {
        Loc_1 = loc_1;
        Loc_2 = loc_2;
        this.category = category;
        this.pay = pay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        wanted_data.category = category;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        wanted_data.pay = pay;
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
}
