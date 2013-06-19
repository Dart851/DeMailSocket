/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author Dart
 */
class MyIcon implements Icon {
    private Color color;
    private final MailBox outer;

    public MyIcon(Color color, final MailBox outer) {
        this.outer = outer;
        this.color = color;
    }

    public int getIconHeight() {
        return 20;
    }

    public int getIconWidth() {
        return 20;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.drawRect(0, 0, 25, 25);
    }
    
}
