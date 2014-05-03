/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Aleix
 */
class Tuberia {
    FlappyJones game;
    int x;
    int y;
    int speedX;
    int speedY;
    int speedy;
    int ampladaTuberia;
    int alturaTUberia;

    /**
     * P
     * @param game
     * @param x Coordenada horitzontal d'on es crearàn les tuberies
     */
    public Tuberia(FlappyJones game, int x) {
        this.game = game;
        y = 0;
        speedX = -1; //Es mouran cap a l'esquerra a velocitat d 1 pixel
        speedy = 0; //Verticalment no es mouran
        this.x = x;
        ampladaTuberia = 40;
        alturaTUberia = 250;
        randomize();
    }

    void paint(Graphics2D g2d) {
        ImageIcon welcome = new ImageIcon(getClass().getResource("Bandera_Argentina.png"));
        g2d.drawImage(welcome.getImage(), x, y, ampladaTuberia, alturaTUberia, null);
        //El 900 es exagerat, pero aixi sempre entra a la pantalla
        g2d.drawImage(welcome.getImage(), x, alturaTUberia+105, ampladaTuberia, 900, null);
//        g2d.fillRect(x, y, ampladaEnemy, alturaEnemy);
//        g2d.fillRect(x, alturaEnemy+120, ampladaEnemy, 900); //akest 900 surt per sota de la pantalla pero es igual
    }
    
    void move() { 
        //Si la tuberia surt de la pantalla, cambiala d'altura i torna-la a posar a la part dreta del joc
        if (x == -40) { 
            x = 600;
            randomize();
        }
        x=x+speedX; //speedX es 1, augmentar per augmentar la dificultat
    }
    
    /**
     * Coordenada horitzontal de la tuberia (per colisions)
     * @return 
     */
    int getX() {
        return x;
    }
    
    /**
     * Coordenada vertical de la tuberia (per colisions)
     * @return 
     */
    int getY() {
        return y;
    }

    /**
     * Mètode que no està implementat ara mateix, pero si es vol ajustar 
     * la dificultat de joc podria servir
     * @param i 
     */
    void setSpeed(int i) {
        speedX = i;
    }
    
    /**
     * Fa que les tuberies tinguin una llargària aleatoria
     */
    void randomize() {
       int yx = (int) (Math.random()*5); //pot donar 5 casos
        switch (yx) {
            case 1:
                alturaTUberia = 100;
                break;
            case 2: 
                alturaTUberia = 200;
                break;
            case 3: alturaTUberia = 300;
                break;
            case 4: alturaTUberia = 50;
                break;
            case 5: alturaTUberia = 340;
                break;
            default:
                alturaTUberia=250;
        }
    }
    
    /**
     * Per altura de la tuberia (per colisions)
     * @return 
     */
    int getCostatEsquerraDAdalt() {
        return alturaTUberia;
    }
    
    /**
     * Amplada de la tuberia (per colisions)
     * @return 
     */
    int getCostatInferior() {
        return ampladaTuberia;
    }
    
//    /**
//     * 
//     * @return 
//     */
//    int getCostatSuperior() {
//        return ampladaTuberia;
//    }
    
    /**
     * Altura de la tuberia d'abaix
     * Per colisions
     * @return 
     */
    int getCostatEsquerraDAbaix() {
    return alturaTUberia+120;
}

    /**
     * Especial pel reset del joc, que torni a la posició de la dreta
     * @param i 
     */
    void setPosition(int i) {
        x = i;
    }
}
