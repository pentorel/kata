package com.github.kata;

public interface Parser<T> {
    public void setItem(String item);
    public T getValue();
}
