package com.example.jakub.milionerzy.entity;

/**
 * Created by jakub on 09.11.2018.
 */

public class Ranking {

    private String position;
    private String name;
    private int result;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
