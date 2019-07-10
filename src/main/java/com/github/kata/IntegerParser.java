package com.github.kata;

public class IntegerParser implements Parser<Integer> {
    private String item;
    public void setItem(String item) {
        this.item = item;
    }

    public Integer getValue() {
        try {
            return Integer.parseInt(item.trim());
        }catch (NumberFormatException e) {
            return 0;
        }
    }
}
