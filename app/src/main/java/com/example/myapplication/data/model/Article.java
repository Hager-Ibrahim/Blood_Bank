package com.example.myapplication.data.model;

public class Article {

    private String articleTest;
    private int drawable;


    public Article(String articleTest, int drawable) {
        this.articleTest = articleTest;
        this.drawable = drawable;
    }

    public String getArticleTest() {
        return articleTest;
    }

    public void setArticleTest(String articleTest) {
        this.articleTest = articleTest;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
