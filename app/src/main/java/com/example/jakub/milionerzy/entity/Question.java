package com.example.jakub.milionerzy.entity;

import java.util.Set;

/**
 * Created by jakub on 09.11.2018.
 */

public class Question {

    private int idCategory;
    private String content;
    private Set<Answer> answerSet;

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

    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }
}
