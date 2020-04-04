package com.effective.section6.demo33;

import java.util.EnumMap;
import java.util.Map;

public enum Phase {//结构
    SOLID, LIQUID, GAS;//固体、液体、气体
	
    public enum Transition {//转变
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID), BOIL(LIQUID, GAS), 
        CONDENSE(GAS, LIQUID), SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
    	//MELT融化 FREEZE结冰 BOIL沸腾 
    	//CONDENSE凝结  SUBLIME升华 DEPOSIT沉淀
    	
        private final Phase src;
        private final Phase dst;
        
        Transition(Phase src, Phase dst) {
            this.src = src;
            this.dst = dst;
        }

        private static final Map<Phase, Map<Phase, Transition>> m = new EnumMap<Phase, Map<Phase, Transition>>(Phase.class);
        static {
            for (Phase p : Phase.values())
                m.put(p, new EnumMap<Phase, Transition>(Phase.class));
            for (Transition trans : Transition.values())
                m.get(trans.src).put(trans.dst, trans);
        }

        public static Transition from(Phase src, Phase dst) {
            return m.get(src).get(dst);
        }
    }
}
