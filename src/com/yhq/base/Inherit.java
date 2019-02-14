package com.yhq.base;


class Parent{
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void show(){
        System.out.println("Parent");
    }
}
class Child extends Parent{

    public void show(){
        super.show();
        System.out.println(super.hashCode());
        System.out.println(this.hashCode());
        super.setA(10);
        setA(11);
        System.out.println(super.getA());
        System.out.println(getA());
        System.out.println("Child");
    }
}
public class Inherit {

    public static void main(String[] args) {
        Parent parent = new Child();
        parent.show();
    }
}
