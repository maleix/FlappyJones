/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Aleix
 * Lògica del joc
 */
class FlappyJones extends JPanel {

    private Personatge character;
    private Tuberia enemy;
    private Tuberia enemy2;
    private Tuberia enemy3;
    private int punts;

    public FlappyJones() {
        character = new Personatge(this);
        enemy = new Tuberia(this, 400); //en akest 0 haura d'anar el random heigth
        enemy2 = new Tuberia(this, 600);
        enemy3 = new Tuberia(this, 800);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                character.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                character.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    enemy.setPosition(400);
                    enemy2.setPosition(800);
                    enemy3.setPosition(600);
                    character.setPosition(20);
                }
            }
        });
        setFocusable(true);
    }

    @Override
    /**
     * Pinta el joc
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon welcome = new ImageIcon(getClass().getResource("avion-fondo-pantalla.jpg"));
        g2d.drawImage(welcome.getImage(), 0, 0, getWidth(), getHeight(), null);
        setForeground(Color.WHITE);

        character.paint(g2d);
        enemy.paint(g2d);
        enemy2.paint(g2d);
        enemy3.paint(g2d);
    }

    /**
     * Mètode que fa moure a tots els personatges de la pantalla
     */
    void move() {
        //Si el personatge no s'ha estavellat, segueix jugant, sinó es para.
        if (haTocat() == false) {
            character.move();
            enemy.move();
            enemy2.move();
            enemy3.move();
        } 
    }

    /**
     * Metode no implementat pero especialment útil si es vol
     * cambiar la dificultat dle joc
     * @param i 
     */
    void setTime(int i) {
        enemy.setSpeed(i);
        enemy2.setSpeed(i);
        enemy3.setSpeed(i);
    }

    /**
     * Mètode que suma 1 cada cop que el personatge atravessa una tuberia
     * i retorna el comptador de punts
     * @return 
     */
    int getPunts() {
        if (character.getX() == enemy.getX() || character.getX() == enemy2.getX() || character.getX() == enemy3.getX()) {
            ++punts;
        }
        return punts;
    }

    /**
     * Mètode per detectar les col·lisions
     * Si ha colisio amb alguna tuberia, s'acaba la partida
     * @return 
     */
    boolean haTocat() {
        boolean haTocat = false;

        //Si la pilota es troba horitzontalment aprop de la tuberia passa a la seguent condicio
        if (character.getX() - enemy.getX() > -26 && character.getX() - enemy.getX() < 40) {
            //Si la pilota es troba també verticalment aprop de la tuberia, vol dir que ha tocat i fi de partida
            if (character.getY() - enemy.getCostatEsquerraDAbaix()>-40) {
                haTocat = true;
            }
            if (character.getY() < enemy.getCostatEsquerraDAdalt()) {
                haTocat = true;
            }
        }

        if (character.getX() - enemy2.getX() > -20 && character.getX() - enemy2.getX() < 27) {
            if (character.getY() - enemy2.getCostatEsquerraDAbaix()>-39) {
                haTocat = true;
            }
            if (character.getY() < enemy2.getCostatEsquerraDAdalt()) {
                haTocat = true;
            }
        }

        if (character.getX() - enemy3.getX() > -20 && character.getX() - enemy3.getX() < 27) {
            if (character.getY() - enemy3.getCostatEsquerraDAbaix()>-39) {
                haTocat = true;
            }
            if (character.getY() < enemy3.getCostatEsquerraDAdalt()) {
                haTocat = true;
            }
        }
        return haTocat;
    }
}
