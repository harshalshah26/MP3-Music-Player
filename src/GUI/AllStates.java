package GUI;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AllStates implements Serializable{
	public boolean shuffle=true;
	public boolean repeat=true;
	public boolean album,artist,genre,duration,year;
	List<String> recentList=new ArrayList<String>();		
		void writeStateObject(AllStates state)
		{
			try
			{
				FileOutputStream fs=new FileOutputStream("file1.hs");
				ObjectOutputStream os=new ObjectOutputStream(fs);
				os.writeObject(state);			
				System.out.println("Successfully write object");
				os.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception:"+e.getStackTrace());
		
			}		
		
		}
		
		public static AllStates readStateObject()
		{
			AllStates state=new AllStates();
			ObjectInputStream os=null;
			try{
				FileInputStream fs=new FileInputStream("file1.hs");
				os=new ObjectInputStream(fs);
				state=(AllStates)os.readObject();				
				os.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception:" +e);
			}
			
				return state; 
			
		}
		
}
