package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.TimeZone;

//import javax.sound.sampled.Clip;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
//import javax.swing.JSlider;

/**
 * This class counts playing time in the form of HH:mm:ss
 * It also updates the time slider
 * @author www.codejava.net
 *
 */
public class PlayingTimer extends Thread {
	private DateFormat dateFormater = new SimpleDateFormat("HH:mm:ss");	
	private boolean isRunning = false;
	private boolean isPause = false;
	private boolean isReset = false;
	private long startTime;
	private long pauseTime;
	private String totalTimeString;
	
	private JLabel incresingLabel;
	private JLabel decresingLabel;
	private JProgressBar slider;
	private MyMP3Player audioClip;
	MyUtility utility=new MyUtility();
	
	

	public PlayingTimer(MyMP3Player audioClip,JLabel incresingLabel,JLabel decresingLabel,JProgressBar slider,String timeString) {
		this.incresingLabel = incresingLabel;
		this.decresingLabel = decresingLabel;
		this.slider = slider;
		this.audioClip = audioClip;
		System.out.println("TimeString:"+timeString);
		this.totalTimeString=timeString;
	}
	
	public void run() {
		isRunning = true;
		
		startTime = System.currentTimeMillis();
		
		while (isRunning) {
			try {
				Thread.sleep(100);
				if (!isPause) {
					if (audioClip != null && audioClip.getStatus()==1) {
						//incresingLabel.setText(toTimeString());
						toTimeString();
						int currentSecond =  audioClip.getPosition() / 1000; 
						slider.setValue(currentSecond);
					}
				} else {
					pauseTime += 100;
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				if (isReset) {
					slider.setValue(0);
					incresingLabel.setText("00:00");
					decresingLabel.setText("00:00");
					
					isRunning = false;		
					break;
				}
			}
		}
	}
	
	
	/**
	 * Reset counting to "00:00:00"
	 */
	public void reset() {
		isReset = true;
		isRunning = false;
	}
	
	public void pauseTimer() {
		isPause = true;
	}
	
	public void resumeTimer() {
		isPause = false;
	}
	
	/**
	 * Generate a String for time counter in the format of "HH:mm:ss"
	 * @return the time counter
	 */
	private void toTimeString() {
		long now = System.currentTimeMillis();
		long currentIncreasing = now - startTime - pauseTime;
		long currentDecreasing = (utility.getTimeSec(totalTimeString)*1000)-currentIncreasing;
		dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timeCounter1 = dateFormater.format(currentIncreasing);
		String timeCounter2 = dateFormater.format(currentDecreasing);
		decresingLabel.setText(timeCounter2.substring(0));//3 for set to minute
		incresingLabel.setText(timeCounter1.substring(0));
		
	}
}