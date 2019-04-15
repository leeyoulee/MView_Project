package com.mview.mview_one.main_fragment.review;

public class best_data {

    private String reviewNo;
    private String rsvDate;
    private String reviewGrade;
    private String reviewContents;
    private String category;
    private String pay;
    private String modelImage;
    private String shopName;
    private String reviewViews;

    public best_data(String reviewNo, String rsvDate, String reviewGrade, String reviewContents, String category, String pay, String modelImage, String shopName, String reviewViews) {
        this.reviewNo = reviewNo;
        this.rsvDate = rsvDate;
        this.reviewGrade = reviewGrade;
        this.reviewContents = reviewContents;
        this.category = category;
        this.pay = pay;
        this.modelImage = modelImage;
        this.shopName = shopName;
        this.reviewViews = reviewViews;
    }

    public String getReviewViews() {
        return reviewViews;
    }

    public void setReviewViews(String reviewViews) {
        this.reviewViews = reviewViews;
    }

    public String getReviewNo() { return reviewNo; }

    public void setReviewNo(String reviewNo) { this.reviewNo = reviewNo; }

    public String getRsvDate() {
        return rsvDate;
    }

    public void setRsvDate(String rsvDate) {
        this.rsvDate = rsvDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getModelImage() {
        return modelImage;
    }

    public void setModelImage(String modelImage) {
        this.modelImage = modelImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getReviewGrade() {
        return reviewGrade;
    }

    public void setReviewGrade(String reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public String getReviewContents() {
        return reviewContents;
    }

    public void setReviewContents(String reviewContents) {
        this.reviewContents = reviewContents;
    }
}
