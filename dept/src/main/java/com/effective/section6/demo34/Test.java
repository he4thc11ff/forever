package com.effective.section6.demo34;

public class Test {
    private static <T extends Enum & Operation> void test(Class<T> opSet, double x, double y) {
        
        for(Operation op : opSet.getEnumConstants())
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }
    
    public static void main(String[] args) {
        double x = 2.0;
        double y = 4.0;
        test(ExtendedOperation.class, x, y);
        test(BasicOperation.class, x, y);
//        test(AA.class, x, y); T extends Enum
    }
}
