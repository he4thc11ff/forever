package com.effective.section6.demo34;

public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {return Math.pow(x, y);}
    },
    REMAINDER("%") {
        public double apply(double x, double y) {return x % y;}
    };
    
    private final String symbol;
    private ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }
    public String toString() {
        return symbol;
    }
}