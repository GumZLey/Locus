package main;

public class MainApplication 
{
    public static void main( String[] args )
    {
        
        Sound_util music = new Sound_util("resources/title_bg_music.wav");;
        
        new GameTitle(music);
        music.playLoop();
        music.setVolume(20);
        

        
    }
}
