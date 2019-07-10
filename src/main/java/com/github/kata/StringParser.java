package com.github.kata;

public class StringParser implements Parser<String> {
    private String item;
    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return item.trim();
    }
}
