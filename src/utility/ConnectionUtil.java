package utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConnectionUtil {
	private static Connection connection;
	//Statement s=null;
	//PreparedStatement preparedStatement;	
	//ResultSet res=null;

    public Connection getConnection() {
        return connection;
    }
    
   /* public void setConnection(Connection connection) {
        this.connection = connection;
    }*/	
	//method for open connection
	public Connection beginConnection() {		
		try {
			Class.forName("org.sqlite.JDBC");
                        String url="jdbc:sqlite:musicplayer.db";
                        //String username="root";
                        //String pw="root";
			connection = DriverManager.getConnection(url);
		} catch (Exception E) {
			System.out.println("Excetion in beginConnection() : " + E);
		}
		return connection;
	}
	//close connection
	public void endConnection() 
	{
		try {
			connection.close();
			System.out.println("Connection close");
		}
		catch(Exception e)
		{
			System.out.println("Exception in close connection..");
		
		}
	}
       
	
	//method for create playlist table if created
	public boolean iscreatePlayListTabel(String name)
        {
        	String sql;
        	int res=0;
        	
            
        	sql="CREATE TABLE IF NOT EXISTS "+name +" ( song     VARCHAR( 256 )  DEFAULT '-',";
            sql+="artist   VARCHAR( 255 )  DEFAULT '-',";
            sql+="album    VARCHAR( 255 )  DEFAULT '-',";
            sql+="year     VARCHAR( 255 )  DEFAULT '-',";
            sql+="genre    VARCHAR( 255 )  DEFAULT '-',";
            sql+="duration VARCHAR( 255 )  DEFAULT '-',";
            sql+="location VARCHAR( 255 )  DEFAULT '-',PRIMARY KEY (`song`,`album`)); ";
            System.out.println(sql);
			try {
				res=executeMyQuery(sql);
					System.out.println(name +" PlayList created:"+res);
					sql="insert into playlisttable(name) values('"+ name+"');";
					System.out.println(sql);
					res=executeMyQuery(sql);
					System.out.println("playlist inserted");
					return true;
				
			} catch (SQLException e) {
				System.out.println("Exception in create playlist:"+e);
				return false;
			}           
        }
	
	public int addSongtoDatabase(String tableName,String song,String artist,String album,String year,String genre,String duration,String fileLocation)
	{
		int temp=0;
		try
        {          
           String sql="INSERT INTO "+tableName+"(song,artist,album,year,genre,duration,location) values('";
           sql+=song+"','";
            sql+=artist+"','";
             sql+=album+"','";
              sql+=year+"','";
               sql+=genre+"','";
                sql+=duration+"','";
                 sql+=fileLocation+"');";
                 System.out.println(sql);
             temp=executeMyQuery(sql);
            //System.out.println("temp:"+temp);
            return temp;
        }
		catch (SQLException ex) {
            System.out.println("Exception in addsong to database:"+ex);
        } 
		return temp;
	}
	
	//method for delete playlist
	public void deletePlayList(String name)
	{
		String sql="DELETE FROM playlisttable WHERE name='"+name+ "';";
		try {
			executeMyQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="DROP TABLE "+name+";";
		try {
			executeMyQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//method for create schema        
	public Connection createSchema()
        {
		 connection = beginConnection();
            try {
               
               //DriverManager.getConnection(url,username,pw);
                System.out.println("Server Established");
                String sql;
               
                
                System.out.println("Database connected");
                
                    sql="CREATE TABLE IF NOT EXISTS library ( song     VARCHAR( 256 )  DEFAULT '-',";
                    sql+="artist   VARCHAR( 255 )  DEFAULT '-',";
                    sql+="album    VARCHAR( 255 )  DEFAULT '-',";
                    sql+="year     VARCHAR( 255 )  DEFAULT '-',";
                    sql+="genre    VARCHAR( 255 )  DEFAULT '-',";
                    sql+="duration VARCHAR( 255 )  DEFAULT '-',";
                    sql+="location VARCHAR( 255 )  DEFAULT '-',PRIMARY KEY (`song`,`album`)); ";
                   // System.out.println(sql);
                    int res=executeMyQuery(sql);
                   // System.out.println("Table created"); 
                    sql="CREATE TABLE IF NOT EXISTS playlisttable (name     VARCHAR( 256 )  NOT NULL,PRIMARY KEY (`name`));";
                    res=executeMyQuery(sql);
                //}
               
            }          
            catch (SQLException ex) {
                System.out.println("Exception:"+ex);
            }  
           System.out.println("Connection:" + connection==null);
             return connection;
        }	
	//method for update query
	public int executeMyQuery(String sql) throws SQLException
	{
			//connection=beginConnection();
			//if(connection!=null)
			//{
			Statement stmt=connection.createStatement();
			int result=stmt.executeUpdate(sql);
			System.out.println(result);
			return result;
			//}
			//else return -1;
	}	
	public ResultSet getMyResult(String sql) throws SQLException
	{
			//connection=beginConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet res = preparedStatement.executeQuery();
			System.out.println("getResult");
			return res;
	}	

	public List<String> getPlaylist()
    {
    	String sql="SELECT * from playlisttable;";
    	ArrayList<String> playlist=new ArrayList<String>();
    	try {
			ResultSet res=getMyResult(sql);
			
			while(res.next())
			{
				playlist.add(res.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("Exception in getting playlist");
		}
    	return playlist;
    	
    }
}
