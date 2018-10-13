package EVDictionary;



import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

public class Voice
{
    public  MaryInterface marytts;
    public  AudioPlayer ap;

    public Voice(String voiceName) {
        try
        {
            marytts = new LocalMaryInterface();
            marytts.setVoice(voiceName);
            ap = new AudioPlayer();
        }
        catch (MaryConfigurationException ex)
        {
            ex.printStackTrace();
        }


    }

    public void say(String input) {
        try
        {
            AudioInputStream audio = marytts.generateAudio(input);

            ap.setAudio(audio);
            ap.start();
        }
        catch (SynthesisException ex)
        {
            System.err.println("Error saying phrase.");
        }
    }

//    public static  void main(String[] a){
//        Voice voice = new Voice("cmu-rms-hsmm");
//        voice.say("hello mother fucker");
//    }


}


