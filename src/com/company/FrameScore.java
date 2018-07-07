package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kolya on 06.07.2018.
 */
public class FrameScore extends JFrame{
   private static int score;

   JTextField  text = new JTextField("name");
   JButton but = new JButton("OK");
   FrameScore(){
       super.add(text);

       super.setSize(300,300);
       super.setResizable(false);
       super.pack();
       super.setVisible(true);
   }
    public static void main(String[] args){
       new FrameScore();
    }

    private static void onRepain(Graphics g){

    }

    private static class ScorePanel extends JPanel{


        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            onRepain(g);
        }
    }





    public static void setScore(int score) {
        FrameScore.score = score;
    }
}
