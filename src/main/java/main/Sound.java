package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


class Sound_util {
    private Clip clip;
    private FloatControl gainControl;

    public Sound_util(String filename)
    {
	try
	{
            URL f = getClass().getResource(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(audioStream);            
            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	catch (Exception e) { e.printStackTrace(); }
    }

    public void playOnce() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void playLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void setVolume(int g) {
        float gain = (float) g/100;
        if (gain < 0)
            gain = 0;
        if (gain > 100)
            gain = 100;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }

    public boolean Active(){
        return clip.isActive();
    }
}