package com.effective.section2.demo3;

public enum Elvis3 {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis3 elvis = Elvis3.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
