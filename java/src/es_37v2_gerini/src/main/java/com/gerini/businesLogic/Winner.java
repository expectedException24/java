package com.gerini.businesLogic;

public class Winner {
    private static boolean gotWinner=false;
    private static int idWinner;
    private static final Winner INSTANCE=new Winner();

    private Winner(){

    } 

    public static boolean isGotWinner() {
        return gotWinner;
    }

    public static synchronized void setGotWinner(boolean gotWinner) {
        Winner.gotWinner = gotWinner;
    }

    public static synchronized void setIdWinner(int idWinner) {
        Winner.idWinner = idWinner;
    }

    public static int getIdWinner() {
        return idWinner;
    }
    
}
