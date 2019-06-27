package com.kshrd.btb.holymomo.utility;

public class Filter {
    String title;
    int cateId;

    public Filter(String title, int cateId) {
        this.title = title;
        this.cateId = cateId;
    }

    public Filter() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "title='" + title + '\'' +
                ", cateId=" + cateId +
                '}';
    }
}
