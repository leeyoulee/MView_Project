package com.mview.mview_one.notice_qna;

public class qna_data {

    private String qnaTitle_data;
    private String qnaNo_data;

    public qna_data(String qnaTitle_data, String qnaNo_data) {
        this.qnaTitle_data = qnaTitle_data;
        this.qnaNo_data = qnaNo_data;
    }

    public String getQnaTitle_data() {
        return qnaTitle_data;
    }

    public void setQnaTitle_data(String qnaTitle_data) {
        this.qnaTitle_data = qnaTitle_data;
    }

    public String getQnaNo_data() {
        return qnaNo_data;
    }

    public void setQnaNo_data(String qnaNo_data) {
        this.qnaNo_data = qnaNo_data;
    }
}
