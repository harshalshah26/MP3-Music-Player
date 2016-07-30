/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author harshal
 */
public class MyUtility {
    
    public String getTimeFormat(long sec)
    {
        String min="",second="", hour="";
        if(sec>3600)
        {
        	hour=Long.toString(sec/3600);
        	sec=sec%3600;
        }
       
        	if(sec>60 && sec<3600)
        	{
            min=Long.toString(sec/60);
            if(min.length()<2)min="0"+min;
            second=Long.toString(sec%60);
            if(second.length()<2)second="0"+second;
        	}
        	else
        	{
        		hour="00";
        		min="00";
        		second=Long.toString(sec);
        	}
        if(hour.length()>0)
        return hour+":"+min+":"+second;
        else
        	return min+":"+second;
    }  
    
    public int getTimeSec(String s)//return timestring 00:00 in seconds
    {
    	try{
    	int sec=Integer.parseInt(s.substring(0,2))*60;
    	sec+=Integer.parseInt(s.substring(3));
    	System.out.println("Second:"+sec);
    	return sec+1;//1 for adjustment
    	}catch(NumberFormatException e)
    	{
    		return 300;
    	}    	
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage,int width,int height)
    {
    	int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
    	BufferedImage resizedImage = new BufferedImage(width, height, type);
    	Graphics2D g = resizedImage.createGraphics();
    	g.drawImage(originalImage, 0, 0, width, height, null);
    	g.dispose();     
    	return resizedImage;
      }
    
}
