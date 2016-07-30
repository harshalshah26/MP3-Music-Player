package GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.Enumeration;
//import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import utility.ConnectionUtil;

public class TreeControl {
	ConnectionUtil util;
	TreeControl()
	{
		util=new ConnectionUtil();
	}

	private TreePath find(DefaultMutableTreeNode root, String s) {
	    @SuppressWarnings("unchecked")
	    Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
	    while (e.hasMoreElements()) {
	        DefaultMutableTreeNode node = e.nextElement();
	        if (node.toString().equalsIgnoreCase(s)) {
	            return new TreePath(node.getPath());
	        }
	    }
	    return null;
	}
	
	public void createNode(String name,JTree tree)
	{		
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		TreePath path = find((DefaultMutableTreeNode)model.getRoot(),"PlayList");
		DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) (path.getLastPathComponent());
		DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(name);
		int index=parentNode.getChildCount();
		System.out.println(index);
		model.insertNodeInto(childNode, parentNode, index);
		model.reload(parentNode);
		for(int i=0;i<tree.getRowCount();i++)  
        {  
            tree.expandRow(i);  
        }	
		tree.setSelectionRow(2+index);
	}
	
	   public DefaultTreeModel displayTree()
		{
		   
	    	DefaultTreeModel model=null;
			DefaultMutableTreeNode root=new DefaultMutableTreeNode("All List");
			DefaultMutableTreeNode songLibrary=new DefaultMutableTreeNode("SongLibrary");
			DefaultMutableTreeNode playList=new DefaultMutableTreeNode("PlayList");
			String sql="select * from playlisttable;";
			try {
				ResultSet res=util.getMyResult(sql);
				///int temp=res.getFetchSize();
				while(res.next())
				{
					DefaultMutableTreeNode playList1=new DefaultMutableTreeNode(res.getString("name"));
					playList.add(playList1);
				}
				root.add(songLibrary);
				root.add(playList);		
			    model=new DefaultTreeModel(root);
			} catch (SQLException e) {
			System.out.println("Exception in createTree "+e);
			}
			return model;
				
		}
	/*   public List<String> getPlaylist()
	    {
	    	String sql="SELECT * from playlisttable;";
	    	ArrayList<String> playlist=new ArrayList<String>();
	    	try {
				ResultSet res=util.getMyResult(sql);
				
				while(res.next())
				{
					playlist.add(res.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Exception in getting playlist");
			}
	    	return playlist;
	    	
	    }	*/
}
