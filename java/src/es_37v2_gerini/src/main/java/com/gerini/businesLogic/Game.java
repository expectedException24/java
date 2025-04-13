package com.gerini.businesLogic;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Game {
    private static final int MAXHORSES = 10;
    private List<Horse> horses = new ArrayList<>();
    private CountDownLatch latch;
    private int amount;

    public List<Horse> getHorses() {
        return horses;
    }

    public static int getMaxhorses() {
        return MAXHORSES;
    }

    public int getWinner() {
        return Winner.getIdWinner();
    }

    public void addHorse(int amount) throws IOException {

        if (amount > MAXHORSES || amount <= 1)
            throw new IOException("Numero di cavalli non permesso ");

        if (!horses.isEmpty())
            throw new IOException("Cavalli già inseriti");

        this.amount = amount;
        this.latch = new CountDownLatch(this.amount);
        horses.clear();
        for (int i = 0; i < this.amount; i++) {
            Horse h = new Horse(latch);
            horses.add(h);
        }

    }

    public void startGame() {
        for (Horse horse : horses) {
            new Thread(horse).start();
        }
        try {
            latch.await();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
