package com.mview.mview_one.main_fragment.main;

public class model_data {
    private String modelImage_data;
    private String modelName_data;
    private String modelLoc_1_data;
    private String modelLoc_2_data;
    private String modelPrice;

    public model_data(String modelImage_data, String modelName_data, String modelLoc_1_data, String modelLoc_2_data, String modelPrice) {
        this.modelImage_data = modelImage_data;
        this.modelName_data = modelName_data;
        this.modelLoc_1_data = modelLoc_1_data;
        this.modelLoc_2_data = modelLoc_2_data;
        this.modelPrice = modelPrice;
    }

    public String getModelImage_data() {
        return modelImage_data;
    }

    public void setModelImage_data(String modelImage_data) {
        this.modelImage_data = modelImage_data;
    }

    public String getModelName_data() {
        return modelName_data;
    }

    public void setModelName_data(String modelName_data) {
        this.modelName_data = modelName_data;
    }

    public String getModelLoc_1_data() {
        return modelLoc_1_data;
    }

    public void setModelLoc_1_data(String modelLoc_1_data) {
        this.modelLoc_1_data = modelLoc_1_data;
    }

    public String getModelLoc_2_data() {
        return modelLoc_2_data;
    }

    public void setModelLoc_2_data(String modelLoc_2_data) {
        this.modelLoc_2_data = modelLoc_2_data;
    }

    public String getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(String modelPrice) {
        this.modelPrice = modelPrice;
    }
}
