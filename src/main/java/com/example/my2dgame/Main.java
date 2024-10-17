package com.example.my2dgame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("My 2D Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
