/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Aleix Lògica del joc
 */
class FlappyJones extends JPanel {

    private Personatge character;
    private Tuberia tuberia1;
    private Tuberia tuberia2;
    private Tuberia tuberia3;
    private int punts;

    public FlappyJones() {
        character = new Personatge(this);
        tuberia1 = new Tuberia(this, 400); 
        tuberia2 = new Tuberia(this, 600);
        tuberia3 = new Tuberia(this, 800);

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
                    punts = 0;
                    tuberia1.setPosition(400);
                    tuberia2.setPosition(800);
                    tuberia3.setPosition(600);
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
        //Millora els grafics en moviment
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ImageIcon welcome = new ImageIcon(getClass().getResource("avion-fondo-pantalla.jpg"));
        g2d.drawImage(welcome.getImage(), 0, 0, getWidth(), getHeight(), null);
        setForeground(Color.WHITE);

        character.paint(g2d);
        tuberia1.paint(g2d);
        tuberia2.paint(g2d);
        tuberia3.paint(g2d);
    }

    /**
     * Mètode que fa moure a tots els personatges de la pantalla
     */
    void move() {
        //Si el personatge no s'ha estavellat, segueix jugant, sinó es para.
        if (haTocat() == false) {
            character.move();
            tuberia1.move();
            tuberia2.move();
            tuberia3.move();
        }
    }

    /**
     * Metode no implementat pero especialment útil si es vol cambiar la
     * dificultat dle joc
     *
     * @param i
     */
    void setTime(int i) {
        tuberia1.setSpeed(i);
        tuberia2.setSpeed(i);
        tuberia3.setSpeed(i);
    }

    /**
     * Mètode que suma 1 cada cop que el personatge atravessa una tuberia i
     * retorna el comptador de punts
     *
     * @return
     */
    int getPunts() {
        if (character.getX() == tuberia1.getX() || character.getX() == tuberia2.getX() || character.getX() == tuberia3.getX()) {
            ++punts;
        }
        if (punts==10 && (character.getX()-tuberia1.getX()==1 || character.getX()-tuberia2.getX()==1 || character.getX()-tuberia3.getX()==1)) {
        URL url = TesterVistaControladorX.class.getResource("elpelucasabe.wav");
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
        }
        return punts;
    }

    /**
     * Mètode per detectar les col·lisions Si ha colisio amb alguna tuberia,
     * s'acaba la partida
     *
     * @return
     */
    boolean haTocat() {
        boolean haTocat = false;
        //Si la pilota es troba horitzontalment aprop de la tuberia passa a la seguent condicio
        if (character.getX() - tuberia1.getX() > -26 && character.getX() - tuberia1.getX() < 40) {
            //Si la pilota es troba també verticalment aprop de la tuberia, vol dir que ha tocat i fi de partida
            if (character.getY() - tuberia1.getCostatEsquerraDAbaix() > -40) {
                haTocat = true;
            }
            if (character.getY() < tuberia1.getCostatEsquerraDAdalt()) {
                haTocat = true;
            }
        }

        if (character.getX() - tuberia2.getX() > -20 && character.getX() - tuberia2.getX() < 27) {
            if (character.getY() - tuberia2.getCostatEsquerraDAbaix() > -39) {
                haTocat = true;
            }
            if (character.getY() < tuberia2.getCostatEsquerraDAdalt()) {
                haTocat = true;
            }
        }

        if (character.getX() - tuberia3.getX() > -20 && character.getX() - tuberia3.getX() < 27) {
            if (character.getY() - tuberia3.getCostatEsquerraDAbaix() > -39) {
                haTocat = true;
            }
            if (character.getY() < tuberia3.getCostatEsquerraDAdalt()) {
                haTocat = true;
            }
        }
        return haTocat;
    }
}
