package com.buda.Wavy;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*
 *********************************
 *********************************
 *** ///////////////////////// ***
 ***Created by Buda on 7/12/16.***
 *** \\\\\\\\\\\\\\\\\\\\\\\\\ ***
 *********************************
 *********************************
 */


public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;

    private boolean running = false;

    private Handler handler;


    public Game() {
        Window wavyWave = new Window(WIDTH, HEIGHT, "Wavy", this);
        handler = new Handler();

    }


    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                render();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            return;
        }

        Graphics g = bs.getDrawGraphics();
        handler.render(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        g.dispose();
        bs.show();
    }






    public static void main(String[] args) {
        new Game();
    }
}