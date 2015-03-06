package com.mygdx.game;

/**
 * Created by Oscar on 20/02/2015.
 */
public class Crono extends Thread {

    private int segundo;

    public Crono() {
        segundo = 0;
    }

    @Override
    public void run() {
        try {
            while(segundo < 3){
                sleep(1000);
                segundo++;
                System.out.println("Segundo" + segundo);
            }
            segundo = 0;
            interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
