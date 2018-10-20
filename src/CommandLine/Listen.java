package CommandLine;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;

public class Listen {
    public MaryInterface marytts;
    public AudioPlayer ap1;

    /**
     * Constructor Listen load voice
     * @param voiceName
     */
    public Listen(String voiceName) {
        try
        {
            marytts = new LocalMaryInterface();
            marytts.setVoice(voiceName);
            ap1 = new AudioPlayer();
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


            ap1.setAudio(audio);
            ap1.start();
        }
        catch (SynthesisException ex)
        {
            System.err.println("Error saying phrase.");
        }
    }
}
