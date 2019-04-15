package com.mview.mview_one.main_fragment.review;

public class review_data {

    public static String category;
    public static String Loc_1;
    public static String Loc_2;
    public static String pay;

    public review_data(String category, String loc_1, String loc_2, String pay) {
        this.category = category;
        Loc_1 = loc_1;
        Loc_2 = loc_2;
        this.pay = pay;
    }

    public static String getPay() {
        return pay;
    }

    public static void setPay(String pay) {
        pay = pay;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        review_data.category = category;
    }

    public static String getLoc_1() {
        return Loc_1;
    }

    public static void setLoc_1(String loc_1) {
        Loc_1 = loc_1;
    }

    public static String getLoc_2() {
        return Loc_2;
    }

    public static void setLoc_2(String loc_2) {
        Loc_2 = loc_2;
    }
}
