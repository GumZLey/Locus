package main;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameTitle extends JFrame implements MouseListener{
    ImageIcon bg, logo, start_text, settings_text, credits_text, howto_text;
    BufferedImage bg_input, logo_image;
    Sound_util music;
    
    private int labelWidth = 500; int labelHeight = 100; int startY = 100;
    private int setting_Y = startY + labelHeight + 50, credits_Y = startY + 2 * (labelHeight + 50), howto_Y = startY + 3 * (labelHeight + 50);
    public GameTitle(Sound_util m){
        this.music = m;
        try {
            bg = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/Background.png")));
            logo = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/logo.png")));
            start_text = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/start.png")));
            settings_text = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/settings.png")));
            credits_text = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/credits.png")));
            howto_text = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("resources/howto.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 768);
        setTitle("LOCUS");

        JLabel bg_label = new JLabel();
        bg_label.setIcon(bg);
        bg_label.setBounds(0, 0, 1300, 768);
        

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1300, 768);
        panel.setOpaque(false);

        JLabel title_image = new JLabel();
        title_image.setIcon(logo);
        title_image.setBounds(375, 50, 500, 150);
        title_image.setHorizontalTextPosition(JLabel.CENTER);


        JLabel start_image = new JLabel(); start_image.setName("start");
        JLabel setting_image = new JLabel(); setting_image.setName("settings");
        JLabel credits_image = new JLabel(); credits_image.setName("credits");
        JLabel how_to_image = new JLabel(); how_to_image.setName("howto");

        start_image.setIcon(start_text);
        setting_image.setIcon(settings_text);
        credits_image.setIcon(credits_text);
        how_to_image.setIcon(howto_text);

        start_image.addMouseListener(this);
        setting_image.addMouseListener(this);
        credits_image.addMouseListener(this);
        how_to_image.addMouseListener(this);


        start_image.setBounds((1300 - labelWidth) / 2, startY, labelWidth, labelHeight);
        setting_image.setBounds((1300 - labelWidth) / 2, startY + labelHeight + 50, labelWidth, labelHeight);
        credits_image.setBounds((1300 - labelWidth) / 2, startY + 2 * (labelHeight + 50), labelWidth, labelHeight);
        how_to_image.setBounds((1300 - labelWidth) / 2, startY + 3 * (labelHeight + 50), labelWidth, labelHeight);

        panel.add(start_image);
        panel.add(setting_image);
        panel.add(credits_image);
        panel.add(how_to_image);

        bg_label.add(panel);
        add(bg_label);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        JLabel tmp = (JLabel) e.getSource();
        switch (tmp.getName()) {
            case "start":
                music.stop();
                dispose();

                JFrame window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setSize(Image_File.FRAMEWIDTH, Image_File.FRAMEHEIGHT);
                window.setResizable(false);
                window.setTitle("locus");
                GameWindow gameWindow = new GameWindow();
                window.add(gameWindow);

                window.setLocationRelativeTo(null);
                window.setVisible(true);
                
                gameWindow.setupGame();
                gameWindow.startGameThread();
            break;
            case "settings":
                dispose();
                new Settings_Frame(bg, music);
                break;
            case "credits":
                dispose();
                new Credits(music);
                break;
            case "howto":
                dispose();
                new Howto(music);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        JLabel tmp = (JLabel) e.getSource();
        tmp.setBounds(tmp.getBounds().x , tmp.getBounds().y - 20, labelWidth, labelHeight);
    }
    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        JLabel tmp = (JLabel) e.getSource();
        switch (tmp.getName()) {
            case "start":
                tmp.setBounds(tmp.getBounds().x , startY, labelWidth, labelHeight);
                break;
            case "settings":
                tmp.setBounds(tmp.getBounds().x , setting_Y, labelWidth, labelHeight);
                break;
            case "credits":
            tmp.setBounds(tmp.getBounds().x , credits_Y, labelWidth, labelHeight);
            break;
            case "howto":
            tmp.setBounds(tmp.getBounds().x , howto_Y, labelWidth, labelHeight);
            break;
            default:
                break;
        }
        tmp.setBounds(tmp.getBounds().x , tmp.getBounds().y, labelWidth, labelHeight);
    }
    
    
}
