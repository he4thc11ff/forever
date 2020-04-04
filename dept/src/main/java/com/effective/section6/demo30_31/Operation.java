package com.effective.section6.demo30_31;

public enum Operation {
    PLUS("+") {
        double apply(double x, double y) {return x + y;}
    },
    MINUS("-") {
        double apply(double x, double y) {return x - y;}
    },
    TIMES("*") {
        double apply(double x, double y) {return x * y;}
    },
    DIVIDE("/") {
        double apply(double x, double y) {return x / y;}
    };
	
    private final String symbol;//操作符
    
    Operation(String symbol) {
        this.symbol = symbol;
    }
    public String toString() {
        return symbol;
    }
    
    abstract double apply(double x, double y);
}
