package com.gerini;

import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Operation op=new Operation();
        System.out.println(op.exeOperation(new MyCallback()));
    }
}