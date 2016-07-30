package pojo;


import java.io.File;








import java.io.IOException;



import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.spi.mpeg.sampled.file.*;
import utility.MyUtility;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public class SongDetail {
	MyUtility myUtility=new MyUtility();
	private String tableName, song, artist, album, year, genre, duration, fileLocation;
	private byte[] image;
	
	public SongDetail(File file)
	{
		readTaginfo(file);
	}

	public String getTableName() {
		return tableName;
	}

	

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getSong() {
		return song;
	}

	

	public String getArtist() {
		return artist;
	}

	

	public String getAlbum() {
		return album;
	}

	

	public String getYear() {
		return year;
	}

	

	public String getGenre() {
		return genre;
	}

	

	public String getDuration() {
		return duration;
	}

	

	public String getFileLocation() {
		return fileLocation;
	}

	
	public void readTaginfo(File file)
	{
		//String song="",artist="",album="",year="",genre="",duration="";   
        try {
        	
            fileLocation=file.getPath().replace('\\', '/');
            String fileName=file.getName();
            fileName=fileName.substring(0,fileName.lastIndexOf("."));
            System.out.println("Location of File:"+fileLocation);
            Mp3File openMP3 = new Mp3File(fileLocation);
            
            if(openMP3.hasId3v2Tag())
            {
               
                ID3v2 id3v2=openMP3.getId3v2Tag();
                 song=id3v2.getTitle()==null || (id3v2.getTitle().equals(""))?fileName:id3v2.getTitle();
                 artist=id3v2.getArtist()==null || (id3v2.getArtist().equals(""))?"-":id3v2.getArtist();
                 album=id3v2.getAlbum()==null || (id3v2.getAlbum().equals(""))?"-":id3v2.getAlbum();
                 year=id3v2.getYear()==null || (id3v2.getYear().equals(""))?"-":id3v2.getYear();
                 genre=id3v2.getGenreDescription()==null || (id3v2.getGenreDescription().equals(""))?"-":id3v2.getGenreDescription();
                 duration=myUtility.getTimeFormat(getTime(file));
                 image=id3v2.getAlbumImage();
            }
            else if(openMP3.hasId3v1Tag())
            {
                

                ID3v1 id3v1=openMP3.getId3v1Tag();
                 song=id3v1.getTitle()==null || (id3v1.getTitle().equals(""))?fileName:id3v1.getTitle();
                 artist=id3v1.getArtist()==null ||(id3v1.getArtist().equals(""))?"-":id3v1.getArtist();
                 album=id3v1.getAlbum()==null || (id3v1.getAlbum().equals(""))?"-":id3v1.getAlbum();
                 year=id3v1.getYear()==null || (id3v1.getYear().equals(""))?"-":id3v1.getYear();
                 genre=id3v1.getGenreDescription()==null || (id3v1.getGenreDescription().equals(""))?"-":id3v1.getGenreDescription();
                 duration=myUtility.getTimeFormat(getTime(file));
                 image=null;
                
            }
            else
            {
                song=fileName;
                artist="-";
                album="-";
                year="-";
                genre="-";
                duration=myUtility.getTimeFormat(getTime(file));//myUtility.getTimeFormat(getDuration(file));
            }
         
	}
	catch(Exception e)
	{
		System.out.println("Exception in Tag:"+e);
	}
}
	
	
	public long getTime(File file) //method for getting time of audio file
	{
		
		AudioFileFormat baseFileFormat=null;
		try {
			baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(file);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map properties = baseFileFormat.properties();
		Long duration = (Long) properties.get("duration");
		System.out.println("duration:"+(duration/1000000)+1);
		return (duration/1000000);
		
	}

}