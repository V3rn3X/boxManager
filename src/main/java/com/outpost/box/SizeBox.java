package com.outpost.box;

public enum SizeBox {

    SMALL("small"),
    MEDIUM("medium"),
    BIG("big");

    private final String displayValue;

    private SizeBox(String displayValue){
        this.displayValue = displayValue;
    }
    public String getDisplayValue() {
        return displayValue;
    }
}
