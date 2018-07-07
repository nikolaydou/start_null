package com.company;

import com.sun.prism.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class game extends JFrame{
    private static game game_window;

    private static Image background;
    private static Image game_over;

    private static Image drop;
    private static float drop_left = 200;
    private static float drop_top = -120;
    private static long last_time;
    private static float drop_v = 100;
    game(){


    }
    private static int score = 0 ;
    public static void main(String[] args)throws IOException {
        background = ImageIO.read(game.class.getResourceAsStream("backgrounds.png"));
        game_over = ImageIO.read(game.class.getResourceAsStream("gameover.png"));
        drop = ImageIO.read(game.class.getResourceAsStream("kaplia.png"));
        game_window = new game();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200,200);
        game_window.setSize(906,478);
        game_window.setResizable(true);
        last_time = System.nanoTime();
        gamePanel panel = new gamePanel();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left+drop.getWidth(null)-40;
                float drop_botom = drop_top+drop.getHeight(null)-40;
                boolean is_drop = x >= drop_left+40 && x <= drop_right && y>=drop_top+30 && y<= drop_botom;
                if(is_drop){
                    drop_top = -120;
                    drop_left = Math.abs((int)((Math.random())*(panel.getWidth())-(drop.getWidth(null))));

                      drop_v+=20;
                        score++;
                        game_window.setTitle("количество капель = "+ score);
                }
            }
        });

        game_window.add(panel);
        game_window.setVisible(true);
    }
    private static void onRepain(Graphics g){
        long curent_time = System.nanoTime();
        float delta_time = (curent_time-last_time)*0.000000001f;
        last_time=curent_time;

        drop_top+=drop_v*delta_time;
        g.drawImage(background,0,0,null);
        g.drawImage(drop,(int)drop_left,(int)drop_top,null);
        if(drop_top>game_window.getHeight())
            g.drawImage(game_over, game_window.getWidth()/4, game_window.getHeight()/3,game_window.getWidth()/2 ,game_window.getHeight()/3,null);
    }

    private static class gamePanel extends JPanel{


        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            onRepain(g);
            repaint();
        }
    }
}
