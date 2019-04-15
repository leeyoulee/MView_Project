package com.mview.mview_one.main_fragment.main;

public class main_notice_data {

    private String noticeTitle_data;
    private String noticeDate_data;
    private String noticeNo_data;

    public main_notice_data(String noticeTitle_data, String noticeDate_data, String noticeNo_data) {
        this.noticeTitle_data = noticeTitle_data;
        this.noticeDate_data = noticeDate_data;
        this.noticeNo_data = noticeNo_data;
    }

    public String getNoticeTitle_data() {
        return noticeTitle_data;
    }

    public void setNoticeTitle_data(String noticeTitle_data) {
        this.noticeTitle_data = noticeTitle_data;
    }

    public String getNoticeDate_data() {
        return noticeDate_data;
    }

    public void setNoticeDate_data(String noticeDate_data) {
        this.noticeDate_data = noticeDate_data;
    }

    public String getNoticeNo_data() {
        return noticeNo_data;
    }

    public void setNoticeNo_data(String noticeNo_data) {
        this.noticeNo_data = noticeNo_data;
    }
}
