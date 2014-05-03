/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Aleix
 */
public class TesterVistaControladorX {
    JFrame finestra;
    FlappyJones di;
    int puntuation;
    JLabel puntuacio;
    public TesterVistaControladorX() throws InterruptedException {
        di = new FlappyJones();
        finestra = compositeVistaX();
        finestra.setSize(400,500);
        finestra.setVisible(true) ;
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Mètode que s'executa per sempre
        //Controla els passos del joc
        
        while (true) {
            //Agafa la puntuacio de la partida
            puntuation = di.getPunts();
            puntuacio.setText("Puntuació: "+ puntuation+" "+"Per a volar ↑ /// Resetejar partida:R");
            //Fa els moviments en el model
            di.move();
            //Pinta el que hi ha en el model
	    di.repaint();
            //Fa que passin 10 milisegons abans de tornar a executar el bucle altre cop
            Thread.sleep(10);
        }
        
    }
    
    public JFrame compositeVistaX() {
        JFrame cv = new JFrame("Flappy Jones");
        Container cont = cv.getContentPane();
        puntuation = di.getPunts();
        puntuacio = new JLabel("Puntuació: "+ puntuation+" "+"Per a volar ↑ /// Resetejar partida:R");
        cont.add(di, BorderLayout.CENTER);
        cont.add(puntuacio, BorderLayout.SOUTH);
        return cv;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        TesterVistaControladorX hola = new TesterVistaControladorX();
    }
}

