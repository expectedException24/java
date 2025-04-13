package com.gerini.businesLogic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Horse implements Runnable {
    private int id, position = 0;
    private static int i = 1;
    private CountDownLatch latch;
    private Random r = new Random();

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public Horse(CountDownLatch latch) {
        this.id = i++;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(10 * r.nextInt(7) + 1);
                position++;
            }
        } catch (Exception e) {
            System.err.println(e);
        }finally{
            latch.countDown();
        }
        if (!Winner.isGotWinner()) {
            Winner.setGotWinner(true);
            Winner.setIdWinner(this.id);
        }
    }
}
