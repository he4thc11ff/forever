package com.effective.section6.demo33;

public class Test {
	public static void main(String[] args) {
		testEnumMap();
	}
	
	private static void testEnumMap() {
        for (Phase src : Phase.values())
            for (Phase dst : Phase.values())
                if (src != dst)
                    System.out.printf("%s to %s : %s %n", src, dst,
							Phase.Transition.from(src, dst));
	}
}
