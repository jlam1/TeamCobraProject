package LogicController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//import sun.audio.AudioData;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//import sun.audio.ContinuousAudioDataStream;

public class MusicLogic {
	//public AudioPlayer musicPlayer = AudioPlayer.player;
	//public static AudioStream music;
	//public ContinuousAudioDataStream loop = null;

	public static Clip clip;
    public MusicLogic(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

    // play, stop, loop the sound clip
    }
	public void BGMPlay(){
		clip.setFramePosition(0);;
		clip.start();
		//AudioData musicData;
		//ContinuousAudioDataStream loop = null;
		//try{
			//music = new AudioStream(new FileInputStream("src/sound/traverse.wav"));
			//musicData = music.getData();
			//loop = new ContinuousAudioDataStream(musicData);
			//musicPlayer.start(loop);
		//} 
		//catch(IOException error){ 
			//System.out.println(error);
		//}
	}
	public void BGMLoop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void BGMStop(){
		//if (music != null){
			//AudioPlayer.player.stop(music);
			//music = null;
		//}
		clip.stop();
		clip.close();
	}
}
