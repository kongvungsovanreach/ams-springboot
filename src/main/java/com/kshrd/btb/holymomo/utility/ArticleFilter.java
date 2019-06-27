package com.kshrd.btb.holymomo.utility;

public class ArticleFilter {
    int categroyId;
    String articleTitle;

    public ArticleFilter(int categroyId, String articleTitle) {
        this.categroyId = categroyId;
        this.articleTitle = articleTitle;
    }

    public ArticleFilter() {
    }

    public int getCategroyId() {
        return categroyId;
    }

    public void setCategroyId(int categroyId) {
        this.categroyId = categroyId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public String toString() {
        return "ArticleFilter{" +
                "categroyId=" + categroyId +
                ", articleTitle='" + articleTitle + '\'' +
                '}';
    }
}
