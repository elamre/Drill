package com.drill.main;

import com.drill.game.entity.Block;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/22/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    Test2 test2;

    Test() {


    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.variable++;
        Test test = new Test();
        Test testz = new Test();
        test.setTest2(test2);
        testz.setTest2(test2);
        System.out.println("Number: " + test.getNumber());
        test.increment();
        System.out.println("Numbers: " + test2.variable);
        test2.variable++;
        System.out.println("Number: " + testz.getNumber());
    }

    public void setTest2(Test2 test2) {
        this.test2 = test2;
    }

    public void increment() {
        test2.variable++;
    }

    public int getNumber() {
        return test2.variable;
    }
}
