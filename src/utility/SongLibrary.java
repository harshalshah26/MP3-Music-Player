package utility;



import java.io.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import pojo.SongDetail;


/**
 *
 * @author harshal
 */


public class SongLibrary {
    ConnectionUtil util=new ConnectionUtil();
    Connection con;
    MyUtility myUtility=new MyUtility();
    public SongLibrary()
    {
         con=util.beginConnection(); 
    }
    
   
    //this method will display song to the library
    public void displayData(JTable table,String tablename)
    {
        con=util.beginConnection();
         System.out.println(con);
       try {
            
            
            String sql="SELECT * from "+tablename+" order by song;";
            //PreparedStatement preparedStatement=con.prepareStatement(sql);
            ResultSet res=util.getMyResult(sql);
            //res.beforeFirst();
            while(res.next())
            {
                DefaultTableModel model=(DefaultTableModel)table.getModel();
                model.addRow(new Object[]{res.getString("song"),res.getString("artist"),res.getString("album"),res.getString("genre"),res.getString("year"),res.getString("duration"),res.getString("location")});
                              
            }
       } catch (SQLException e) {
            System.out.println("SQLException from displayData in MyUtility:"+e);
        }             
    }//end displayData()
    
    //This method will add song into database and library when clicked add song
    public int addSong(File[] selectedFiles,JTable libraryTable,int row,String tableName,boolean flag)
    {         
    	System.out.println("Table Name:"+tableName);
    	String song,artist,album,year,genre,duration,fileLocation;
    	int temp1=0,temp2=0;
            for(int i=0;i<selectedFiles.length;i++)
            {
               	SongDetail songDetail=new SongDetail(selectedFiles[i]);
            	//songDetail.readTaginfo(selectedFiles[i]);
            	song=songDetail.getSong();
            	artist=songDetail.getArtist();
            	album=songDetail.getAlbum();
            	year=songDetail.getYear();
            	genre=songDetail.getGenre();
            	duration=songDetail.getDuration();
            	fileLocation=songDetail.getFileLocation();
            	year=songDetail.getYear();
            	if(!tableName.equals("library"))
            	{
            		temp1=util.addSongtoDatabase("library", song, artist, album, year, genre, duration, fileLocation);
            	}
                   
                         temp2=util.addSongtoDatabase(tableName,song, artist, album, year, genre, duration, fileLocation);
                        //System.out.println("temp:"+temp);
                        if(temp2==1&&flag==true)//flag for whether to display in jtable or not
                        {
                        	DefaultTableModel model=(DefaultTableModel)libraryTable.getModel();
                            if(row>0){
                            model.insertRow(row,new Object[]{song, artist, album, genre,year,duration, fileLocation});
                            libraryTable.setRowSelectionInterval(row,row);
                            
                            }else
                            {
                            	 model.addRow(new Object[]{song, artist, album, genre, year,duration, fileLocation});
                            	 libraryTable.setRowSelectionInterval(libraryTable.getRowCount()-1, libraryTable.getRowCount()-1);
                            }
                        }
            
            
            }//for end
            System.out.println("temp1+temp2:"+(temp1+temp2));
           return (temp1+temp2);
        } //end of addSong()
    
    
    
    
    //this method will delete song from database and library 
    
   
    
    public void deleteSong(String table,String song,String album,JTable libraryTable)
    {
        String sql="delete from "+table+  " where song='"+song+"' AND album='" +album+"'" ;
        System.out.println(sql);
        try {
            util.executeMyQuery(sql);
        } catch (SQLException e) {
            System.out.println("SQLException in deleteSong():"+e);
        }
        DefaultTableModel model=(DefaultTableModel)libraryTable.getModel();
        int rowNo=findRow(song,libraryTable);
        model.removeRow(rowNo);        
    }
    
   
    
    
    //return the number of selected row for which songName or songPath is passed
    public int findRow(String song,JTable libraryTable)
    {
    	System.out.println("Passed Song:"+song);
         DefaultTableModel model=(DefaultTableModel)libraryTable.getModel();
         for(int i=0;i<model.getRowCount();i++)
         {
             if(libraryTable.getValueAt(i,0).equals(song)) //for songname
             {
                 return i;
             }
             else if(libraryTable.getValueAt(i,6).equals(song))//for song path
             {
                 return i;
             }
            	 
            
         }
                 return -1;
    }//end findRow()   
}
