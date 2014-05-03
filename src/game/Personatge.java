/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Aleix
 */
class Personatge {

    FlappyJones game;
    int x;
    int y;
    int speedX;
    int speedY;
    int limitJump;
    int again;
    int speedy;

    public Personatge(FlappyJones game) {
        this.game = game;
        x = 100; //Posicions en la que s'inicialitza
        y = 20;
        limitJump=0;
        again= 0;
        speedY = +2; //La pilota començara baixant
    }

    void paint(Graphics2D g2d) {
        g2d.fillOval(x, y, 25, 25);
//       ImageIcon welcome = new ImageIcon(getClass().getResource(""));
//       g2d.drawImage(welcome.getImage(), x, y, 35, 35, null);
    }

    void keyReleased(KeyEvent e) {
    }

    void keyPressed(KeyEvent e) {
        //Salta
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //Si hi ha 70 pixels per poder saltar, salta fins a 70 pixels
            if (y-70 > 0) {
            limitJump = y - 70;
            }
            //En aquest cas, la pilota esta molt adalt i no te suficient espai per saltar
            //Per a que no surti de la pantalla, com a molt podra saltar fins al pixel 0
            else {
                limitJump = 0;
            }
            //Això farà que la pilota puji a una velocitat de 3 pixels per bucle
            speedY = -3;
            //Variable auxiliar per saltar
            again = limitJump;
        }
    }

    void move() {     
        //
        y = y + speedY;
        if (y > 420) { //si la pilota passa dels 420 pixels, es quedarà quieta, sino surtiria de la pantalla
            speedY=0;
        }        

        //Cas en que s'ha premut la tecla SPACE i la pilota puja 70 pixels, mentres 
        //la posicio actual no superi els 70 pixels, segueix pujant
        if (again >= y) {
            speedY = +2;
        }
    }
    
    /**
     * Mètode especialment interessant per preparar les col·lisions
     * Distancia d'amplada en que es troba el personatge
     * @return 
     */
    int getX() {
        return x;
    }
    
    /**
     * Mètode especialment interessant per preparar les col·lisions
     * Altura en que es troba el personatge
     * @return 
     */
    int getY() {
        return y;
    }


    /**
     * Mètode interessant per fer el reset
     * @param i 
     */
    void setPosition(int i) {
        y=i;
    }


}
