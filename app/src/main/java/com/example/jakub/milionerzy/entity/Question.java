package com.example.jakub.milionerzy.entity;

import java.util.ArrayList;

/**
 * Created by jakub on 09.11.2018.
 */

public class Question {

    private int idCategory;
    private String content;
    private ArrayList<Answer> answerList;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }
}
