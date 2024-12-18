package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Settings_Frame extends JFrame{
    ImageIcon close_icon, frame_image;
    Image close_icon_resize, frame_image_resize;
    Sound_util music;
    public Settings_Frame(ImageIcon bg, Sound_util m) {
        this.music = m;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 768);
        setTitle("LOCUS");
        try {
            close_icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/close.png")));
            close_icon_resize = close_icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
            frame_image = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/Frame.png")));
            frame_image_resize = frame_image.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }



        JLabel bg_label = new JLabel();
        bg_label.setIcon(bg);
        bg_label.setBounds(0, 0, 1300, 768);


        JLabel close_label = new JLabel();
        close_label.setBounds(10, 10, 32, 32);
        close_label.setIcon(new ImageIcon(close_icon_resize));

        JPanel panel = new JPanel();
        panel.setBounds(250, 100, 800, 600);
        panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.setAlignmentY(CENTER_ALIGNMENT);
        panel.setOpaque(false);
        
        JLabel background_Label = new JLabel(new ImageIcon(frame_image_resize));
        background_Label.setBounds(250, 100, 800, 600);

        JSlider slider = new JSlider(0, 100, 50);
        slider.setPreferredSize(new Dimension(200, 200));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
        slider.setOpaque(false);
        slider.setAlignmentX(LEFT_ALIGNMENT);
        slider.setAlignmentY(LEFT_ALIGNMENT);


        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25);

        slider.setPaintLabels(true);
        slider.setFont(new Font("MONOSPACED", Font.PLAIN, 18));

        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.setAlignmentX(CENTER_ALIGNMENT);
        slider.setAlignmentY(CENTER_ALIGNMENT);


        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                music.setVolume(((JSlider) e.getSource()).getValue());
            }
            
        });

        close_label.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new GameTitle(music);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });

        add(close_label);
        add(panel);
        add(background_Label);
        panel.add(slider);
        add(bg_label);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}