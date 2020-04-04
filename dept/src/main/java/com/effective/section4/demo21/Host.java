package com.effective.section4.demo21;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 宿主类
 * 
 * 因为策略接口被用作所有具体策略实例的类型，所以并不需要为了倒出具体策略，
 * 而把策略类做成公有的。相反“宿主类”还可以导出公有的静态域，其类型为策略接口，
 * 具体的策略类可以是宿主类的私有前套类。下面的例子使用静态成员类，而不是匿名类，
 * 以便允许具体的策略类实现第二个接口Serializable
 * @author Administrator
 *
 */
public class Host {
   private static class StudentCmp implements Comparator<Student>, Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -5797980299250787300L;
        
        public int compare(Student s1, Student s2) {
        	return s1.id-s2.id;
        }
    }
     
    // Return comparator is serializable
    public static final Comparator<Student> STUDENT_COMPARATOR = new StudentCmp();
}
