/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication12;

import java.io.IOException;

/**
 *
 * @author borag
 */
public class JavaApplication12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        new Thread(() -> {
                new NewClass();
        }).start();
        
        login loginFrame = new login();
        loginFrame.setVisible(true);
    }

}
