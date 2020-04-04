package com.effective.section11.demo78;

import java.io.*;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(new File("period")));
        Period obj=new Period(new Date(),new Date());
        oo.writeObject(obj);
        oo.flush();
        System.out.println("序列化成功: "+new File("period").length());
        ObjectInputStream ois= null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File("period")));
            Period period=(Period)ois.readObject();
            System.out.println(period);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
