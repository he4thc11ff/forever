package com.effective.section2.demo3;

public class Elvis1 {
    public static final Elvis1 INSTANCE = new Elvis1();

    private Elvis1() {
    }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis1 elvis = Elvis1.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
