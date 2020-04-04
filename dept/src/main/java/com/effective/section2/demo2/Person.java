package com.effective.section2.demo2;

public class Person {
    private final String name;
    private final int age;

    private final String address;
    private final String phone;

    public static class Builder{
        private final String name;
        private final int age;

        private String address = null;
        private String phone = null;

        public Builder(String name,int age){
            this.name = name;
            this.age = age;
        }

        public Builder address(String val){
            address = val;
            return this;
        }

        public Builder phone(String val){
            phone = val;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    private Person(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.phone = builder.phone;
    }

    @Override
    public String toString() {
        return "name:"+name+" age:"+age+" address:"+address+" phone:"+phone;
    }
}
