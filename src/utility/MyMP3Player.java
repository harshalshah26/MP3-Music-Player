package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;

import GUI.MainFrame;
import pojo.SongDetail;

public class MyMP3Player {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
     // the player actually doing all the work
    private  Player player;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private   int status = NOTSTARTED;
    //stores name of current playing song
    public  String playingSong;
    public static int playingSongIndex=-1;
    public static SongDetail playingSongDetail;
    public String[] path;

    public void setStatus(int s) {
        status = s;
    }
    
    public int getStatus() {
        return status;
    }

    public MyMP3Player(String path,String song,int index) throws JavaLayerException {
        try {
			this.player = new Player(new FileInputStream(path));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        playingSong=song;
        playingSongIndex=index;
        playingSongDetail=new SongDetail(new File(path));
        
    }

    public void playSong(final MainFrame frame) throws JavaLayerException {
        System.out.println("Playing");
        
        synchronized (playerLock) {
            if(status==NOTSTARTED) {
               
                    final Runnable r = new Runnable() {
                        public void run() {
                           while (status != FINISHED) {
            try {
                //System.out.println("Playing bit");
            	if(frame.state.repeat && !player.play(1))
            	{
            		System.out.println("Repeat");
            		frame.playButtonActionPerformed(null,playingSongIndex);
            	}
            	else if(frame.state.shuffle && !player.play(1))
            	{
            		System.out.println("Shuffle");
            		frame.playButtonActionPerformed(null,new Random().nextInt(frame.getRowcount()));
            	}
            else if (!player.play(1)) {
            	   System.out.println("song over");
            	   frame.playNext(null);
            	   break;    
               
            	}//if end
            } catch (final JavaLayerException e) {
            	System.out.println("exception in playing:"+e);
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (status == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        System.out.println("Terminate Player");
                        break;
                    }
                }
            }
        }
        System.out.println("HARSHAL");
        close();
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                   // t.setPriority(Thread.MAX_PRIORITY);
                    status = PLAYING;
                    t.start();      
        } 
    }
    }

        public boolean pauseSong() {
        synchronized (playerLock) {
            if (status == PLAYING) {
                status = PAUSED;
            }
            return status == PAUSED;
        }
    }

    
    public boolean resumeSong() {
        synchronized (playerLock) {
            if (status == PAUSED) {
                status = PLAYING;
                playerLock.notifyAll();
            }
            return status == PLAYING;
        }
    }

    public void stopSong() {
        synchronized (playerLock) {
        	playingSong="";
            status = FINISHED;
            playerLock.notifyAll();
        }
    }

       
    public void close() {
    	System.out.println("Player closed");
        synchronized (playerLock) {
            status = FINISHED;
        }
        try {
           // player.close();
        } catch (final Exception e) {
            
        }
    }
    
    //method for changing volume
    public void volumeControl(float value)
    {
    	Info source = Port.Info.SPEAKER;
        //        source = Port.Info.LINE_OUT;
        //        source = Port.Info.HEADPHONE;

            if (AudioSystem.isLineSupported(source)) 
            {
                try 
                {
                    Port outline = (Port) AudioSystem.getLine(source);
                    outline.open();                
                    FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);                
                    System.out.println("       volume: " + volumeControl.getValue() );
                    volumeControl.setValue(value);
                    System.out.println("    New   volume: " + volumeControl.getValue() );
                } 
                catch (LineUnavailableException ex) 
                {
                    System.err.println("source not supported");
                    ex.printStackTrace();
                }            
            }
        } 

    	public int getPosition()
    	{
    		return player.getPosition();
    	}
    	
    	public int generateRandom(int range)
    	{
    		Random rn = new Random();

    		int randomNum =  rn.nextInt(range);
    		return randomNum;
    		
    	}
    	
    	/*public int getDuration(File file)
    	{
    		int duration=0;
            
    		AudioFile audioFile = AudioFileIO.read(file);
    		duration= audioFile.getAudioHeader().getTrackLength();
    		return duration;
    	}*/
    
}

