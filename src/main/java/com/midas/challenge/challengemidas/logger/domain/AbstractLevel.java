package com.midas.challenge.challengemidas.logger.domain;

public abstract class AbstractLevel {

    protected String name;
    protected int value;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

}
