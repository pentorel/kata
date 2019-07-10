package com.github.kata;

public class BooleanParser implements Parser<Boolean> {
    private String item;
    public void setItem(String item) {
        this.item = item;
    }

    public Boolean getValue() {
        return true;
    }
}
