package utility;

	
	import javax.swing.JLabel;

import GUI.MainFrame;

	/**
	 *
	 * @author harshal
	 */
	public class Transition extends Thread{
	    
	    public static String displayString="";
	    JLabel label;
            private boolean flagContinue=true;   
          
	    public Transition(String s,JLabel label)
	    {
	        Transition.displayString=s;
	        this.label=label;
                flagContinue=true;               
	    }	    
            
    public void setFlagContinue(boolean flagContinue) {
        this.flagContinue = flagContinue;
    }

    public boolean isFlagContinue() {
        return flagContinue;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        Transition.displayString = displayString;
    }
    
	   
	    @Override
	    public void run()
	    {
	    	Transition.displayString +="   ";
	        label.setText(displayString);
	        //MainFrame.mainframe.songLabel.setText(displayString);
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException ex) {
	                   System.out.println("Interrupted Exception:"+ex);
	        }
	        while(flagContinue){
	        	
	          label.setText(Transition.displayString);
	          //MainFrame.mainframe.songLabel.setText(displayString);
	         String s1=Transition.displayString.substring(1);
	         char s2=Transition.displayString.charAt(0);
	         Transition.displayString=s1+s2;	         
	                              try
	                               {
	                                     Thread.sleep(400);
	                               }catch(Exception e){	             
	                                     System.out.println("transition Exception");
	                                }
	        }
	    
	    } 
}
