package com.gerini;

import java.util.Random;

import javax.security.auth.callback.Callback;

public class Operation {
    private int a,b,r;
    private Random rand = new Random();

    //per motivi di test numeri possono essere casuali
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getR() {
        return r;
    }
    
    public Operation(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    public int exeOperation(MyCallback callback){
        this.r=this.a+this.b;
        callback.stampa();
        return this.r;
    }

    public Operation() {
        this.a = rand.nextInt(50);
        this.b = rand.nextInt(50);
    }
}
