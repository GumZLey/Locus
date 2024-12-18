package main;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;

public class Credits extends JFrame{
    Sound_util music;
    ImageIcon bg, close_icon;
    Image bg_resize, close_icon_resize;
    public Credits(Sound_util m)
    {
        this.music = m;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 768);
        setTitle("LOCUS");

        try {
            bg = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/credits_title.png")));
            bg_resize = bg.getImage().getScaledInstance(1300, 768, Image.SCALE_DEFAULT);
            close_icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/close.png")));
            close_icon_resize = close_icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel bg_label = new JLabel(new ImageIcon(bg_resize));
        
        JLabel close_label = new JLabel();
        close_label.setBounds(10, 10, 32, 32);
        close_label.setIcon(new ImageIcon(close_icon_resize));
        
        close_label.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new GameTitle(music);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                
            }

            
        });

        add(close_label);
        add(bg_label);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
