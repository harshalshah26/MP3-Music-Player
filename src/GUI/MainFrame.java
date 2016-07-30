/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
//import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Enumeration;
import java.util.List;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
//import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;






import utility.MyMP3Player;
//import javax.swing.event.*;
import utility.MyUtility;
import utility.SongLibrary;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


//import net.sf.cuf.ui.table.SortingTable;
import pojo.SongDetail;
import javazoom.jl.decoder.JavaLayerException;
import utility.*;
/**
 *
 * @author harshal
 */
public class MainFrame extends javax.swing.JFrame  {
	TreeControl treeControl;
	public AllStates state;
    SongLibrary songLibrary=new SongLibrary();   
    MyMP3Player player;
    Transition transition;
	private JMenu control=new JMenu();
	ConnectionUtil util= new ConnectionUtil();  
	DefaultTreeModel model;
	private JPopupMenu subPopup1;
	PlayingTimer timer;
	private JLabel volumeLabel=new JLabel();
	private JLabel pictureLabel=new JLabel();
	private JLabel albumLabel=new JLabel();
	private JLabel albumNameLabel=new JLabel();
	private JLabel artistLabel=new JLabel();
	private JLabel artistNameLabel=new JLabel();
	private JLabel yearLabel=new JLabel();
	private JLabel yearNameLabel=new JLabel();
	private JLabel genreLabel=new JLabel();
	private JLabel genreNameLabel=new JLabel();
	private JLabel decreasingTimeLabel;
	JMenu recentlyPlayed;
	
	 
	
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame(String frameName) {
            this.setName(frameName);
         util.createSchema();
         treeControl=new TreeControl();         
         //File userDir = new File("P:/songs");
         openFile=new JFileChooser();
         openFile.setMultiSelectionEnabled(true);
         openFile.setDragEnabled(true);
         state=readStateObject();			
       //  System.out.println("shuffle:"+state.shuffle);
       // System.out.println("repeat:"+state.repeat);
        //System.out.println("repeat:"+state.recentList.size());
        //state.recentList.add("harshal");
         this.initComponents();
         songLibrary.displayData(libraryTable, frameName);
    }
    
    void writeStateObject(AllStates state)//method for write allstates
	{
    	if(state==null)
    	{
    		state=new AllStates();
    	}
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
	
	public  AllStates readStateObject() //method for read allstates
	{
		ObjectInputStream os=null;
		try{
			FileInputStream fs=new FileInputStream("file1.hs");
			os=new ObjectInputStream(fs);
			state=(AllStates)os.readObject();
			if(state==null)
				state=new AllStates();
			System.out.println(state.album);
			System.out.println(state.artist);
			System.out.println(state.duration);
			os.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Exception:" +e);
			state=new AllStates();
		}
		
		catch(Exception e)
		{
			System.out.println("Exception:" +e);
			state=new AllStates();
		}
		
			return state; 
		
	}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("serial")
	private void initComponents() {
    	//TreeStuff treeStuff=new TreeStuff(tree);
    	//state=readStateObject();
        mainPanel = new javax.swing.JPanel();
        northPanel = new javax.swing.JPanel();
        songLibraryLabel = new javax.swing.JLabel();
        southPanel = new javax.swing.JPanel();       
        stopButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        songLabel = new javax.swing.JLabel();
        increasingTimeLabel = new javax.swing.JLabel();
        decreasingTimeLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        treeScroll = new javax.swing.JScrollPane();
        volumeSlider = new javax.swing.JSlider();
        songSlider=new javax.swing.JProgressBar();
        //tree =createTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        //TableSorter sorter = new TableSorter(new DefaultTableModel());
       // table = new JTable(sorter);
      //  JTableHeader header = table.getTableHeader();
        //sorter.setTableHeader(header);

        libraryTable = new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        libraryTable.setAutoCreateRowSorter(true);
        JTableHeader header = libraryTable.getTableHeader();
        //sorter.setTableHeader(header);
        Menu = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        control= new javax.swing.JMenu();
        addSong = new javax.swing.JMenuItem();
        openSong = new javax.swing.JMenuItem();
        deleteSong = new javax.swing.JMenuItem();
        createPlayList = new javax.swing.JMenuItem();
        
        createPlayList.setMnemonic('P');
        exit = new javax.swing.JMenuItem();
        if(this.getName(). equals("library"))
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        else
        	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);	
    
        setTitle("H$ MusicBox");
        setAlwaysOnTop(false);
       if(!this.getName().equals("library"))
        setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double height=0,width=0;
		if(this.getName().equals("library"))
		{
		 width =screenSize.getWidth();
		 height =screenSize.getHeight()-40;
		}
		else
		{
		   width =1100;
		   height = screenSize.getHeight()-40;
		}
        setPreferredSize(new Dimension((int)width,(int)height));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            
        });
       
        songSlider.setValue(0);
       
        songSlider.setForeground(Color.RED);
      
        mainPanel.setBackground(new java.awt.Color(0, 255, 0));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.BorderLayout());

        northPanel.setBackground(new java.awt.Color(40, 49, 183));
        northPanel.setPreferredSize(new java.awt.Dimension(1100, 50));

        songLibraryLabel.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        songLibraryLabel.setForeground(new java.awt.Color(255, 255, 255));
        songLibraryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        int i;
        if(this.getName().equals("library"))
        {
        songLibraryLabel.setText("Song Library");
        i=400;
        }
        else
        {
        	songLibraryLabel.setText(this.getName());
        	i=300;
        	
        }
        javax.swing.GroupLayout northPanelLayout = new javax.swing.GroupLayout(northPanel);
        northPanel.setLayout(northPanelLayout);
        northPanelLayout.setHorizontalGroup(
            northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northPanelLayout.createSequentialGroup()
                .addGap(i,i,i)
                .addComponent(songLibraryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, (int)(width*.40), javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(444, Short.MAX_VALUE))
        );
        northPanelLayout.setVerticalGroup(
            northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(songLibraryLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(northPanel, java.awt.BorderLayout.PAGE_START);



        southPanel.setBackground(new java.awt.Color(40, 49, 186));
        southPanel.setPreferredSize(new java.awt.Dimension(1100, 125));

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/stop1.jpg"))); // NOI18N
        stopButton.setLabel("");
        stopButton.setPreferredSize(new java.awt.Dimension(40, 40));

        pauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/pause2.jpg"))); // NOI18N
        pauseButton.setPreferredSize(new java.awt.Dimension(40, 40));

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/play1.jpg"))); // NOI18N
        playButton.setPreferredSize(new java.awt.Dimension(40, 40));

        previousButton.setPreferredSize(new java.awt.Dimension(40, 40));
        nextButton.setPreferredSize(new java.awt.Dimension(40, 40));
       

        increasingTimeLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        increasingTimeLabel.setForeground(new java.awt.Color(255, 255, 255));
        increasingTimeLabel.setText("");
        
        decreasingTimeLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        decreasingTimeLabel.setForeground(new java.awt.Color(255, 255, 255));
        decreasingTimeLabel.setText("");


        volumeLabel.setBackground(new java.awt.Color(255, 0, 0));
        volumeLabel.setPreferredSize(new java.awt.Dimension(50, 50));
        volumeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/volume1.jpg")));

        songLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        songLabel.setForeground(new java.awt.Color(255, 255, 255));
        songLabel.setText("");

       // pictureLabel.setText("");
      
        albumLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        albumLabel.setForeground(new java.awt.Color(255, 255, 255));
        albumLabel.setText("");
        artistLabel.setPreferredSize(new java.awt.Dimension(68, 22));

        albumNameLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        albumNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        albumNameLabel.setText("");

        artistLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        artistLabel.setForeground(new java.awt.Color(255, 255, 255));
        artistLabel.setText("");
        artistLabel.setPreferredSize(new java.awt.Dimension(68, 22));

        artistNameLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        artistNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        artistNameLabel.setText("");

        yearLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        yearLabel.setForeground(new java.awt.Color(255, 255, 255));
        yearLabel.setText("");

        yearNameLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        yearNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        yearNameLabel.setText("");

        genreLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        genreLabel.setForeground(new java.awt.Color(255, 255, 255));
        genreLabel.setText("");

        genreNameLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        genreNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        genreNameLabel.setText("");
        javax.swing.GroupLayout southPanelLayout = new javax.swing.GroupLayout(southPanel);
        southPanel.setLayout(southPanelLayout);
        southPanelLayout.setHorizontalGroup(
            southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(southPanelLayout.createSequentialGroup()
                .addComponent(pictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addComponent(albumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(artistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genreNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yearNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(artistNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(increasingTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addComponent(songSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(decreasingTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(southPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(songLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );
        southPanelLayout.setVerticalGroup(
            southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(southPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(albumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(artistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(artistNameLabel))
                        .addGap(2, 2, 2)
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(southPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(yearNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(genreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(songLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(southPanelLayout.createSequentialGroup()
                                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9))
                            .addGroup(southPanelLayout.createSequentialGroup()
                                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(songSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(decreasingTimeLabel)
                                    .addComponent(increasingTimeLabel))
                                .addGap(35, 35, 35)))
                        .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(southPanelLayout.createSequentialGroup()
                                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, southPanelLayout.createSequentialGroup()
                                .addComponent(volumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        
        
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt,libraryTable.getSelectedRow());
            }
        });
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	pauseButtonActionPerformed(evt);
            }
        });
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	stopButtonActionPerformed(evt);
            }
        });
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	nextButtonActionPerformed(evt);
            }
        });
        
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	previousButtonActionPerformed(evt);
            }
        });
       
        //set icons
        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/play1.jpg")));
        pauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/pause2.jpg")));
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/stop1.jpg")));
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/next.gif")));
        previousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/previous.gif")));

        mainPanel.add(southPanel, java.awt.BorderLayout.PAGE_END);
        southPanel.getAccessibleContext().setAccessibleName("southPanel");

        centerPanel.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane2.setBackground(new java.awt.Color(255,255,255));
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        libraryTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        libraryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},new String [] {
                "Song", "Artist", "Album", "Genre","Year","Duration","URL"
            }
        ));
        
        libraryTable.getTableHeader().setReorderingAllowed(false);
        libraryTable.getTableHeader().setResizingAllowed(true);
        libraryTable.setFocusable(false);
        libraryTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        libraryTable.setDragEnabled(true);
        libraryTable.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        libraryTable.setSelectionBackground(new java.awt.Color(178, 231, 255));
        libraryTable.setSelectionForeground(new java.awt.Color(0, 0, 255));
        libraryTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       // libraryTable.getColumnModel().getColumn(1).setMinWidth(0);
        //libraryTable.getColumnModel().getColumn(1).setMaxWidth(0);
        libraryTable.getColumnModel().getColumn(6).setMinWidth(0);
        libraryTable.getColumnModel().getColumn(6).setMaxWidth(0);
        setAlbumview();
        setArtistview();
        setGenreview();
        setDurationview();
        setYearview();
        libraryTable.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	hidePopup();
                tableHeaderMouseClicked(evt);
            }
        });




        libraryTable.setDropTarget(new DropTarget() {
           
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
             
            	dropActionPerformed(dtde);
            }

        });
		 
        jScrollPane2.setViewportView(libraryTable);
        jScrollPane2.setDropTarget(new DropTarget() {
            

            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
            	dropActionPerformed(dtde);
            
            }

        });
        libraryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libraryTableMouseClicked(evt);
            }
        });
       
       
        jScrollPane2.setViewportView(libraryTable);
        System.out.println(this.getName());
        if(this.getName().equals("library"))
        {
        model=treeControl.displayTree();
        tree=new JTree(model);
        treeScroll.setPreferredSize(new java.awt.Dimension(74, 300));
        treeScroll.setViewportView(tree);	
        tree.setRootVisible(false);
        tree.setSelectionRow(1);     
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/GUI/stuff/playlist.png"));
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(imageIcon);
		
		 tree.setCellRenderer(renderer);
		 for(i=0;i<tree.getRowCount();i++)  
	        {  
	            tree.expandRow(i);  
	        }
		 
              
        
        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
              treeMouseClicked(me);
            }
          });
       
        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
            .addComponent(treeScroll, javax.swing.GroupLayout.DEFAULT_SIZE,(int)(width*0.85), Short.MAX_VALUE)  
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,(int)(width*0.85), javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addComponent(treeScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        }//if end
        else
        {
        	javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
            centerPanel.setLayout(centerPanelLayout);
            centerPanelLayout.setHorizontalGroup(
                centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(centerPanelLayout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,(int) width, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))                   
            );
            centerPanelLayout.setVerticalGroup(
                centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(centerPanelLayout.createSequentialGroup()
                    .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                         )));
        	 
        }

        mainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);
        //file.setAccelerator(KeyStroke.getKeyStroke("alt F"));
        file.setMnemonic('F');
        file.setText("File");
        file.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        file.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        file.setIconTextGap(15);
        file.setPreferredSize(new java.awt.Dimension(56, 19));
        addSong.setText("Add Song to Library");
        addSong.setVisible(true);
       if(!this.getName().equals("library"))
        //	addSong.setEnabled(false);
        addSong.setText("Add Song to "+this.getName().toString());
        addSong.setPreferredSize(new java.awt.Dimension(100, 22));
        addSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongActionPerformed(evt);
            }
        });
       
        file.add(addSong);

        openSong.setText("Play Song from My Computer");
        openSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSongActionPerformed(evt);
            }
        });
        file.add(openSong);
        
        deleteSong.setText("Delete song from Library");
        deleteSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
        deleteSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSongActionPerformed(evt);
            }
        });
        file.add(deleteSong);
        createPlayList.setText("Create Playlist");
        createPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               mainframe.createPlayListActionPerformed(evt);
            }
        });
        file.add(createPlayList);

        exit.setMnemonic('E');
        exit.setText("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke("alt VK_F4 "));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        file.add(exit);
        Menu.add(file);
        
        //ControlMenu
        control.setMnemonic('C');
        control.setText("Control");
        playsong=new JMenuItem("Play Song");
        increasevolume=new JMenuItem("Increase Volume");
        decreasevolume=new JMenuItem("Decrease Volume");
        nextsong=new JMenuItem("Next Song");
        previoussong=new JMenuItem("Previous Song");
        recentlyPlayed=new JMenu("Recently Played");
        shuffle=new JCheckBoxMenuItem("Shuffle");
        repeat=new JCheckBoxMenuItem("Repeat");
        currentsong=new JMenuItem("Go to Current Song");
        JSeparator seperator=new JSeparator();
        repeat.setMnemonic('R');
        shuffle.setMnemonic('S');
        control.add(playsong);
        control.add(nextsong);
        control.add(previoussong);
        control.add( seperator);
        control.add(increasevolume);
        control.add(decreasevolume);
        control.add(currentsong);
        if(state.recentList.size()>10)
        {
        for(int j=state.recentList.size()-1;j>=state.recentList.size()-10;j--)
        {
        	String name=state.recentList.get(j);
        	 JMenuItem songItem=new JMenuItem(state.recentList.get(j));
             recentlyPlayed.add(songItem);
            	 songItem.addActionListener(new java.awt.event.ActionListener() {
                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                         playButtonActionPerformed(evt,-1);
                     }
                 });
             
        }
        }
        else
        {
        	for(int j=state.recentList.size()-1;j>=0;j--)
            {
            	String name=state.recentList.get(j);
            	 JMenuItem songItem=new JMenuItem(state.recentList.get(j));
                 recentlyPlayed.add(songItem);
                	 songItem.addActionListener(new java.awt.event.ActionListener() {
                         public void actionPerformed(java.awt.event.ActionEvent evt) {
                             playButtonActionPerformed(evt,-2);//-2 identification for songItem
                         }
                     });
                 
            }
        	
        }
        control.add(recentlyPlayed);
        control.add(shuffle);
        control.add(repeat);
        
        //shortcut
        //increasevolume.setAction(new MenuAction("increasevolume", null, "Click to Open an Existing File.", KeyStroke.getKeyStroke("control alt P")));
        increasevolume.setAccelerator(KeyStroke.getKeyStroke("control I"));
        decreasevolume.setAccelerator(KeyStroke.getKeyStroke("control D"));
        currentsong.setAccelerator(KeyStroke.getKeyStroke("control L"));
        nextsong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,KeyEvent.CTRL_MASK));
        previoussong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.CTRL_MASK));
        playsong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,0));
        
        //
      
        	shuffle.setState(state.shuffle);
           	repeat.setState(state.repeat);     
     
       playsong.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   disableTree();
        	   //tree.setFocusable(false);
               int index=libraryTable.getSelectedRow();
               if(index<0)
            	   index=0;
        	   playButtonActionPerformed(evt,-1);
           }
       });
       increasevolume.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   disableTree();
        	   player.volumeControl(volumeSlider.getValue()*1.05F);
        	   volumeSlider.setValue(volumeSlider.getValue()+5);
           }
       });
       decreasevolume.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   disableTree();
        	   player.volumeControl(volumeSlider.getValue()*0.95F);
        	   volumeSlider.setValue(volumeSlider.getValue()-5);
           }
       });
       nextsong.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   disableTree();
        	   nextButtonActionPerformed(evt);
           }
       });
       previoussong.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   disableTree();
        	   previousButtonActionPerformed(evt);
           }
       });
       currentsong.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   disableTree();
        	   int index;
        	  if(player!=null)
        	   index=songLibrary.findRow(player.playingSong , libraryTable);//MyMP3Player.playingSongIndex;
        	  else        
        	   index=libraryTable.getSelectedRow();
        	   System.out.println("Playing index:"+index);
        	   if(index!=-1)
        	   {
        	   libraryTable.setRowSelectionInterval(index, index);
        	   libraryTable.scrollRectToVisible(new Rectangle(libraryTable.getCellRect(index,0 , true)));
        	   }
           }     
       });
       shuffle.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   
               state.shuffle=!state.shuffle;
               writeStateObject(state);
           }
       });
       repeat.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   state.repeat=!state.repeat;
        	   writeStateObject(state);
               
           }
       });
       
       
        
        Menu.add(control);
        Menu.setFocusable(true);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.getAccessibleContext().setAccessibleName("mainPanel");
        volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeSliderStateChanged(evt);
            }
        });
        
     
        this.addWindowListener(new WindowAdapter() {
        	
        	public void windowClosing(WindowEvent evt) {
        	    stopButtonActionPerformed(null);
        	    player=null;
        	  }
		});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed
    
    void disableTree()
    {
    	if(this.getName().equals("library"))
    		tree.setFocusable(false);
    	
    	
    }
    
    
    private void dropActionPerformed(DropTargetDropEvent dtde)
     {
    	System.out.println("Test................................");
         if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
             dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
             Transferable t = dtde.getTransferable();
             List fileList = null;
             try {
                 fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
                 if (fileList.size() > 0) {
                 	libraryTable.clearSelection();
                     Point point = dtde.getLocation();
                     int row = libraryTable.rowAtPoint(point);
                     DefaultTableModel model = (DefaultTableModel) libraryTable.getModel();
                     File[] file=new File[fileList.size()];
                     for(int i=0;i<fileList.size();i++)
                     {
                     	file[i]=(File)fileList.get(i);
                     	System.out.println("File:"+file[i]);
                     }
                     if(this.getName().toString().equals("library"))//case if mainFrame
                     {
                    	 DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    	 if(!selectedNode.equals(songLibrary))//if drag in playlist
                    	 {
                    	 songLibrary.addSong(file, libraryTable,row,songLibraryLabel.getText().equals("Song Library")?"library":songLibraryLabel.getText(),true);
                    	 
                    	 
                    	 }
                    	 else
                    		 songLibrary.addSong(file, libraryTable,row,"library",true);
	 
                     }
                     else //case if playlist frame
                     songLibrary.addSong(file, libraryTable,row,this.getName().toString(),true);
                  }     
             } catch (UnsupportedFlavorException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } else if(dtde.isDataFlavorSupported(DataFlavor.stringFlavor)){
             
        	 System.out.println("String accept");
        	 Transferable t=dtde.getTransferable();
        	 try {
				    String result=(String)t.getTransferData(DataFlavor.stringFlavor);
				    BufferedReader rdr = new BufferedReader(new StringReader(result));
				    List<String> lines = new ArrayList<String>();
				    for (String line = rdr.readLine(); line != null; line = rdr.readLine())
				     lines.add(line);				    
				    for(int i=0;i<lines.size();i++)
				    {
				    	String s=lines.get(i);
				        songLibrary.addSong(new File[]{new File(s.substring(s.indexOf(":")+4))}, libraryTable, libraryTable.getRowCount(),songLibraryLabel.getText() , true);
				    	//System.out.println("Lines:"+s);
				    }
				    rdr.close();
				   
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         else
         {
        	 System.out.println("nothing");
         }
     
     }

    private void deleteSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSongActionPerformed
        int[] selectedSongIndex=libraryTable.getSelectedRows();
        //System.out.println("DisplayString:"+Transition.displayString);
        String displayingSong=Transition.displayString;
        //stop delete if not selected any song
        if(selectedSongIndex.length==0)
        {
            JOptionPane.showMessageDialog(this,"You have not selected any song.Select at least one song for deletion.");
        }
        //stop delete if playing
        if(MyMP3Player.playingSongDetail!=null && libraryTable.getValueAt(selectedSongIndex[0],0).equals(MyMP3Player.playingSongDetail.getSong()))
        {
                    System.out.println("In");
                    JOptionPane.showMessageDialog(this,"You Cant delete currently playing song.\nStop playing and then try again.");
                    return;
         }
        //delete selected song
        String[][] selectedsongName=new String[selectedSongIndex.length][2];
        for(int i=0;i<selectedSongIndex.length;i++)
        {   
            selectedsongName[i][0]=(String)libraryTable.getValueAt(selectedSongIndex[i],0);//name
            selectedsongName[i][1]=(String)libraryTable.getValueAt(selectedSongIndex[i],2);//albumname
        }
        for(int i=0;i<selectedsongName.length;i++)
        {
        	if(songLibraryLabel.getText().equals("Song Library"))
        	{
        		 songLibrary.deleteSong("library",selectedsongName[i][0],selectedsongName[i][1],libraryTable);
        	}
        	else
            songLibrary.deleteSong(songLibraryLabel.getText(),selectedsongName[i][0],selectedsongName[i][1],libraryTable);
            //select row after deletion
            if(selectedSongIndex[i]!=libraryTable.getRowCount())
            libraryTable.setRowSelectionInterval(selectedSongIndex[i],selectedSongIndex[i] );
            else
            libraryTable.setRowSelectionInterval(selectedSongIndex[i]-1,selectedSongIndex[i]-1);    
        }
    }//GEN-LAST:event_deleteSongActionPerformed

    private void libraryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libraryTableMouseClicked
        
    	//libraryTable.setFocusable(true);
    	if(this.getName().equals("library"))
    	tree.setFocusable(false);
        hidePopup();
        if(evt.getClickCount()==2)
        {
            int row=libraryTable.rowAtPoint(evt.getPoint());
            System.out.println("selected Row:"+row);
            ListSelectionModel model=libraryTable.getSelectionModel();
            model.setSelectionInterval(row, row);
            playButtonActionPerformed(new ActionEvent(new Object(),0,""),row);
        }
        if(evt.getButton()==MouseEvent.BUTTON3)
        {
        	
        	
            addPopup=new JMenuItem("Add Song to Library");
            deletePopup=new JMenuItem("Delete Song from List ");
            playPopup=new JMenuItem("Play Song");
            subPopup=new JMenu("Add song to PlayList");
            popup=new JPopupMenu();
            popup.add(addPopup);
            popup.add(deletePopup);
            popup.add(playPopup);
            subPopup1=new JPopupMenu();
            List<String> playlist=util.getPlaylist();
           
            for(int i=0;i<playlist.size();i++)
            {
            	 playlist1=new JMenuItem(playlist.get(i));
                 subPopup1.add(playlist1);
                 playlist1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {              	
            	hidePopup();
            	playListMouseClicked(evt,(JMenuItem)evt.getComponent());
            	
            }});
                 
            }
            
            popup.add(subPopup);
            
           
          subPopup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {            	
            	subPopup1.setVisible(true);      
            }});
          
            
           // popup.setFocusable(true);
          
            addPopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {            	
            	
            	hidePopup();
            	addSongActionPerformed(evt);
            	
               
            }});
            
            deletePopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	hidePopup();
            	deleteSongActionPerformed(evt);
            }});
            
            playPopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt,libraryTable.getSelectedRow());
                hidePopup();
            }});  
            
           
            
           
           //code for row selection on right click
            popup.setLocation(MouseInfo.getPointerInfo().getLocation());
            subPopup1.setLocation((MouseInfo.getPointerInfo().getLocation().x)+175,MouseInfo.getPointerInfo().getLocation().y+65);
            popup.setVisible(true);
            int row=libraryTable.rowAtPoint(evt.getPoint());
            System.out.println("selected Row:"+row);
            ListSelectionModel model=libraryTable.getSelectionModel();
            model.setSelectionInterval(row, row);
            
        }
        
    }//GEN-LAST:event_libraryTableMouseClicked
    //function for adding song in playlist
    private void playListMouseClicked(java.awt.event.MouseEvent evt,JMenuItem item)
    {
    	int index[]=libraryTable.getSelectedRows();
    	File[] files=new File[index.length];
    	for(int i=0;i<index.length;i++)
    	{
    		 files[i]=new File((String)libraryTable.getValueAt(index[i],6));    		
    	}
    	//System.out.println("Item:"+item.getActionCommand());
    	
    	if(songLibrary.addSong(files, libraryTable,0,item.getActionCommand(),false)>0)
    		JOptionPane.showMessageDialog(this,"Song Successfully added to "+item.getActionCommand());
    	else
    		JOptionPane.showMessageDialog(this,"Song already exists in "+item.getActionCommand());
    }
    private void subPopupMouseClicked(java.awt.event.MouseEvent evt)
    {
    	subPopup.setVisible(true);
    }

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
     
    	hidePopup();
    }//GEN-LAST:event_jScrollPane2MouseClicked
    
    private void treeMouseClicked(java.awt.event.MouseEvent evt)
    {
    	hidePopup();
    	if(evt.getButton()==MouseEvent.BUTTON3)
    	{
    		int x = evt.getX();
			int y = evt.getY();
			JTree tree = (JTree)evt.getSource();
			TreePath path = tree.getPathForLocation(x, y);
			if (path == null)
				return;
			tree.setSelectionPath(path);
        	 DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        			if (!selectedNode.getUserObject().toString().equals("SongLibrary"))
        			{
        					playlistPopUp=new JPopupMenu();
        					if(selectedNode.getUserObject().toString().equals("PlayList"))
        					{
        						//System.out.println("in playlist");
        						JMenuItem createPlaylist=new JMenuItem("Create Playlist");
        						playlistPopUp.add(createPlaylist);
        						createPlaylist.addActionListener(new java.awt.event.ActionListener() {
            			            public void actionPerformed(java.awt.event.ActionEvent evt) {
            			            	hidePopup();
            			            	createPlayListActionPerformed(evt);
            			            	//playlistPopUp.setVisible(false);
            			            	//playlistPopUp=null;
            			            	
            			            }});
        					}
        					else
        					{
        					
        					JMenuItem openWindow=new JMenuItem("Open in new window");
        					JMenuItem deletePlayList=new JMenuItem("Delete PlayList ");
        					
        					openWindow.addActionListener(new java.awt.event.ActionListener() {
        			            public void actionPerformed(java.awt.event.ActionEvent evt) {
        			            	openWindowActionPerformed(evt);
        			            	//playlistPopUp.setVisible(false);
        			            	//playlistPopUp=null;
        			            	hidePopup();
        			            }});
        					
        					deletePlayList.addActionListener(new java.awt.event.ActionListener() {
        			            public void actionPerformed(java.awt.event.ActionEvent evt) {
        			            	deletePlayListActionPerformed(evt);
        			            	hidePopup();
        			            	addSong.setEnabled(true);
        			            }});
        					
        					playlistPopUp.add(openWindow);
        					playlistPopUp.add(deletePlayList);
        					//playlistPopUp.setLocation(MouseInfo.getPointerInfo().getLocation());
        					//playlistPopUp.setVisible(true); 
        					}//else over
        					
        					playlistPopUp.setLocation(MouseInfo.getPointerInfo().getLocation());
        					playlistPopUp.setVisible(true); 
    		
        			}//ext if
        			
        
    }
    	else if(evt.getButton()==MouseEvent.BUTTON1)//if left clicked
		{
    		    		 DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
             System.out.println(selectedNode.getUserObject().toString());

             //disable add song to library
             if(selectedNode.getUserObject().toString().equals("SongLibrary"))
             {
            	 /*addSong.setEnabled(true);//enable add song to library
            	 int no=libraryTable.getRowCount();
            	 if(no!=0)//code for erase previous table
            	 {
            		 DefaultTableModel model=(DefaultTableModel)libraryTable.getModel();
            		 model.setRowCount(0);
            	 }
            	 songLibrary.displayData(libraryTable,"library");
            	 songLibraryLabel.setText("Song Library");*/
            	 addSong.setEnabled(true);
            	 resetTable("library","Song Library");
             }
             else if(selectedNode.isLeaf()&&!selectedNode.getUserObject().toString().equals("PlayList"))               
             {
            	 addSong.setEnabled(false);
            	 int no=libraryTable.getRowCount();
            	 if(no!=0)
            	 {
            		 DefaultTableModel model=(DefaultTableModel)libraryTable.getModel();
            		 model.setRowCount(0);
            	 }
            	  //songLibrary.displayData(libraryTable,selectedNode.getUserObject().toString());
            	 // songLibraryLabel.setText(selectedNode.getUserObject().toString());
            	 resetTable(selectedNode.getUserObject().toString(), selectedNode.getUserObject().toString());
             }         
             
        
        		
		}
    }
    
    private void  tableHeaderMouseClicked(java.awt.event.MouseEvent evt)
    {
    	if(evt.getButton()==MouseEvent.BUTTON3)
    	{
    	//System.out.println("hi");
        headerPopup=new JPopupMenu();
        album=new JCheckBoxMenuItem("Album");
        artist=new JCheckBoxMenuItem("Artist");
        year=new JCheckBoxMenuItem("Year");
        duration=new JCheckBoxMenuItem("Duration");
        genre=new JCheckBoxMenuItem("Genre");
        
        album.setState(state.album);
        artist.setState(state.artist);
        genre.setState(state.genre);
        duration.setState(state.duration);
        year.setState(state.year);
        headerPopup.add(album);
        headerPopup.add(artist);
        headerPopup.add(genre);
        headerPopup.add(duration);
        headerPopup.add(year);
        headerPopup.setLocation(MouseInfo.getPointerInfo().getLocation());
        headerPopup.setVisible(true);
        
     
        album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {        	  	
            	state.album=!state.album;
            	//System.out.println(state.album);
            	writeStateObject(state);
            	headerPopup.setVisible(false);
            	setAlbumview();
            }});
   artist.addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent evt) {
            	state.artist=!state.artist;
            	//System.out.println(state.album);
            	writeStateObject(state);
            	headerPopup.setVisible(false);
            	setArtistview();
            	
            }});
   genre.addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent evt) {
    	   state.genre=genre.getState();
       	//System.out.println(state.album);
       	writeStateObject(state);
       	headerPopup.setVisible(false);
       	setGenreview();
       }});
   duration.addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent evt) {
    	   state.duration=!state.duration;
       	//System.out.println(state.album);
       	writeStateObject(state);
       	headerPopup.setVisible(false);
       	setDurationview();
       	        	
       }});

   year.addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent evt) {
    	   state.year=!state.year;
       //	System.out.println(state.album);
       	writeStateObject(state);
       	headerPopup.setVisible(false);
       	setYearview();
       }});

    	}
    }
    
    private void setAlbumview()
    {
    	if(state.album)
        {
    		 libraryTable.getColumnModel().getColumn(2).setMaxWidth(250);
    	 libraryTable.getColumnModel().getColumn(2).setMinWidth(250);
        
       //  libraryTable.getColumnModel().getColumn(1).setWidth(200);
        }
          else
          {
        	  libraryTable.getColumnModel().getColumn(2).setMinWidth(0);
              libraryTable.getColumnModel().getColumn(2).setMaxWidth(0);
          }
    }
    
    private void setArtistview()
    {
    	System.out.println("State Artist:"+state.artist);
    	int no=libraryTable.getColumnCount();
    	//state=readStateObject();
        if(state.artist)
        {
        	 libraryTable.getColumnModel().getColumn(1).setMaxWidth(250);
        	 libraryTable.getColumnModel().getColumn(1).setMinWidth(250);
        
         //libraryTable.getColumnModel().getColumn(1).setWidth(200);
        }
          else
          {
        	  
        	  libraryTable.getColumnModel().getColumn(1).setMinWidth(0);
        	  libraryTable.getColumnModel().getColumn(1).setMaxWidth(0);
             
          }
       	
    }
    private void setGenreview()
    {
    	System.out.println("State Artist:"+state.artist);
    	int no=libraryTable.getColumnCount();
    	//state=readStateObject();
          if(state.genre)
          {
        	  libraryTable.getColumnModel().getColumn(3).setMaxWidth(200);
        	  libraryTable.getColumnModel().getColumn(3).setMinWidth(200);
         
          }
          else
          {
        	  libraryTable.getColumnModel().getColumn(3).setMinWidth(0);
              libraryTable.getColumnModel().getColumn(3).setMaxWidth(0);
          }
       	
    }
    private void setDurationview()
    {
    	System.out.println("State Artist:"+state.artist);
    	int no=libraryTable.getColumnCount();
    	//state=readStateObject();
          if(state.duration)
          {
        	  libraryTable.getColumnModel().getColumn(5).setMaxWidth(90);
        	  libraryTable.getColumnModel().getColumn(5).setMinWidth(90);
        	  
         
          }
          else
          {
        	  libraryTable.getColumnModel().getColumn(5).setMinWidth(0);
              libraryTable.getColumnModel().getColumn(5).setMaxWidth(0);
          }
       	
    }
    private void setYearview()
    {
    	System.out.println("State Artist:"+state.artist);
    	int no=libraryTable.getColumnCount();
    	//state=readStateObject();
          if(state.year)
          {
        	  libraryTable.getColumnModel().getColumn(4).setMaxWidth(90);
        	  libraryTable.getColumnModel().getColumn(4).setMinWidth(90);
         
          }
          else
          {
        	  libraryTable.getColumnModel().getColumn(4).setMinWidth(0);
              libraryTable.getColumnModel().getColumn(4).setMaxWidth(0);
          }
       	
    }
    
    private void openWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongActionPerformed
       playlistFrame=getPlaylistFrame(((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject().toString());//new MainFrame(((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject().toString());
       if(playlistFrame!=null) 
       playlistFrame.dispose();
       playlistFrame=new MainFrame(((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject().toString());
        playlistFrame.setAlwaysOnTop(false);
        playlistFrame.setVisible(true);
        playlistFrame.pack();
        //songLibrary.displayData(libraryTable, "library");
        //songLibraryLabel.setText("Song Library");
        resetTable("library", "Song Library");
    	
    }//GEN-LAST:event_addSongActionPerformed

    private void deletePlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongActionPerformed
    	if(playlistPopUp!=null)
    	{
    		playlistPopUp.setVisible(false);
    		playlistPopUp=null;
    	}
    	
    	DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
    	DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    	String selectedPlaylist=selectedNode.getUserObject().toString();
    	int res=JOptionPane.showConfirmDialog(this, "Are You Sure You want to delete playlist "+selectedPlaylist+ "?");
    	if(res==JOptionPane.YES_OPTION && selectedNode.isLeaf()&&!selectedPlaylist.equals("SongLibrary"))
    	{
    		util.deletePlayList(selectedPlaylist);
    		JFrame frame=getPlaylistFrame(selectedPlaylist);
    		//System.out.println("SELECTED FRMAE:"+frame.getName());
    		if(frame!=null && frame.getName().equals(selectedPlaylist))
    		frame.dispose();
    		model.removeNodeFromParent(selectedNode);
    		model.reload();
    		for(int i=0;i<tree.getRowCount();i++)  
            {  
                tree.expandRow(i);  
            }
    		tree.setSelectionInterval(0,0);//selection for songlibrary after delete
    	  int no=libraryTable.getRowCount();
       	 if(no!=0)//code for show library after deleting playlist
       	 {
       		 DefaultTableModel tabelModel=(DefaultTableModel)libraryTable.getModel();
       		 tabelModel.setRowCount(0);
       	 }
       	 songLibrary.displayData(libraryTable,"library");
       	 songLibraryLabel.setText("Song Library");
    	}
    	
    }//GEN-LAST:event_addSongActionPerformed


    private void addSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongActionPerformed
        showMyDialogue();//this will open file dialogue box to add song
 
        int res=openFile.showOpenDialog(this);
        File[] selectedFiles=openFile.getSelectedFiles();
        int row=libraryTable.getSelectedRow(); //could be null
        String tableName=this.getName().toString();
        System.out.println("Table Name:"+tableName);
        if(res==JFileChooser.APPROVE_OPTION)
        songLibrary.addSong(selectedFiles,libraryTable,row,tableName,true);       
    }//GEN-LAST:event_addSongActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       if(evt.getButton()==MouseEvent.BUTTON1&&popup!=null)
            {
                hidePopup();
            }
    }//GEN-LAST:event_formMouseClicked
    
   
    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
  	timer.pauseTimer();
    player.pauseSong();
  	pauseButton.setEnabled(false);
  	playButton.setEnabled(true);
    }//GEN-LAST:event_pauseButtonActionPerformed

    public void playButtonActionPerformed(java.awt.event.ActionEvent evt,int index) {//GEN-FIRST:event_playButtonActionPerformed
    	 boolean flag=false;
    	 int[] songCount;
    	 if(index<0)//case except playnext and play previous 
    	 {
    		 songCount=new int[1];
    		 String path=evt.getActionCommand();//get name from recent played song menuitem
    		 if(path!=null && evt.getSource()!=playButton && evt.getSource()!=playsong)
    		 {
    			 songCount[0]=songLibrary.findRow(path, libraryTable);
    			 if(songCount[0]==-1)
    			 {
    			 JOptionPane.showMessageDialog(null,"Song is deleted or moved from library.");
    			 return;
    			 }
    		 }
    		 else
    		 {
    		 	songCount=libraryTable.getSelectedRows();
    		 	if(songCount.length>1)
    		 	{
    		 		JOptionPane.showMessageDialog(null,"Select single song for play");
    		 		return;
    		 	}
    		 	else if(songCount.length==0 && libraryTable.getRowCount()>0)
    		 	{
    		 		songCount=new int[1];
    		 		songCount[0]=0; //if selected no song then start playing from first song
        	 
    		 	}
    		 	else if(libraryTable.getRowCount()==0)//case if no song in the library
    		 	{
    		 		JOptionPane.showMessageDialog(null,"Add song to library from File Menu.");
    		 	}
    		 	else
    		 	{
    		 		songCount=new int[1];
    		 		songCount[0]=libraryTable.getSelectedRow();
    		 	}
    		 }
    	 }//main if end
    	 else//case for playnext and playprevious
    	 {
    		 songCount=new int[1];
    		 songCount[0]=index;
    	 }
    	try {
    			
            if(player!=null&&player.getStatus()==2)//case when resume song 
                {
                    System.out.println("In pause");
                    timer.resumeTimer();
                    player.resumeSong();
                    
                                 
                }         
            else //if(player!=null)//play other song while playing current
            {
            	  if(!this.getName().equals("library"))
            	  {
            		  mainframe.resetAll();
            	  }
            	  else
            	  {
            		  if(playlistFrame!=null)
            			  playlistFrame.resetAll();
            	  }
           
                System.out.println("In new song");
                //int songCount[0]=libraryTable.getSelectedRow();
                libraryTable.setRowSelectionInterval(songCount[0],songCount[0] );
                if(songCount[0]%2==0)
                flag=true;
                resetAll();
                String path=(String)libraryTable.getValueAt(songCount[0],6);
                String name=(String)libraryTable.getValueAt(songCount[0],0);
                player=new MyMP3Player(path,name,songCount[0]);
                setTimer();
                //System.out.println("timer start");
                player.playSong(this);              
               // if(this.getName().equals("library"))//start iff mainframe
                setTransition(MyMP3Player.playingSongDetail.getSong(),songLabel);  
                if(evt!=null)
                {
                	state.recentList.add(path);
                	writeStateObject(state);
                }
            }
        
        } catch (JavaLayerException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
    	
      	setAllLabel(flag);
    	playButton.setEnabled(false);
    	pauseButton.setEnabled(true);
    	
    	
      
    }//GEN-LAST:event_playButtonActionPerformed
   private void setTimer()//set slider and time both
   {
	   if(MyMP3Player.playingSongDetail!=null)
	   songSlider.setMaximum(new MyUtility().getTimeSec(MyMP3Player.playingSongDetail.getDuration()));
	   player.volumeControl(volumeSlider.getValue()*0.01F);//set current volume when start playing
       timer=new PlayingTimer(player,increasingTimeLabel,decreasingTimeLabel,songSlider,MyMP3Player.playingSongDetail.getDuration());
       timer.start();//start timer and slider
   }
    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
       resetAll();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        playNext(evt);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousActionPerformed
        playPrevious();
    }//GEN-LAST:event_PreviousActionPerformed

    private void openSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSongActionPerformed
       showMyDialogue();
        int res=openFile.showOpenDialog(this);
        if(res==JFileChooser.APPROVE_OPTION)
        {
            //FileInputStream fis=null;
            try {
                File file=openFile.getSelectedFile();
               // fis = new FileInputStream(file);
                String song=file.getName().substring(0,file.getName().lastIndexOf("."));
                resetAll();               
                player=new MyMP3Player(file.getPath(),song,-1); 
                setTimer();
                setAllLabel(true);
                player.playSong(this);
                playButton.setEnabled(false);
            	pauseButton.setEnabled(true);   
            	//if(this.getName().equals("library"))
                setTransition(song, songLabel);
               
            } catch (JavaLayerException ex) {
               System.out.println("Exception in open song:"+ex);
            } 
        }
    }//GEN-LAST:event_openSongActionPerformed

    private void createPlayListActionPerformed(java.awt.event.ActionEvent evt) {                                               
        
    	String name=JOptionPane.showInputDialog(this,"Enter Name For PlayList","PlayList");    	
    	if(name.equalsIgnoreCase("PlayList"))
    	{
    		JOptionPane.showMessageDialog(this,"Try different name.You cant give name as PlayList");
    		return;
    	}
    	boolean flag=util.iscreatePlayListTabel(name);
    	if(name!=null && name.length()>0 && flag )
    	{
    	treeControl.createNode(name, tree);
    	resetTable(name,name);
    	addSong.setEnabled(false);
    	}
    	else
    		JOptionPane.showMessageDialog(this,"Invalid name or "+name+" already exists.\nRename and Try again.");
    	
    	//System.out.println("Node Created");   	
    }
    
    private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                          
        player.volumeControl(volumeSlider.getValue()*0.01F);
        }                                   


    
    private void setAllLabel(boolean flag)
    {
    	if(player!=null && this.getName().equals("library"))
    	{
    		if(MyMP3Player.playingSongDetail!=null)
    		{
    			albumNameLabel.setText(MyMP3Player.playingSongDetail.getAlbum().length()>50?MyMP3Player.playingSongDetail.getAlbum().substring(0,50):MyMP3Player.playingSongDetail.getAlbum());
    			artistNameLabel.setText(MyMP3Player.playingSongDetail.getArtist());
    			yearNameLabel.setText(MyMP3Player.playingSongDetail.getYear());
    			genreNameLabel.setText(MyMP3Player.playingSongDetail.getGenre());
    			albumLabel.setText("Album  :");
    			artistLabel.setText("Artist   :");
    			yearLabel.setText("Year     :");
    			genreLabel.setText("Genre   :");
    			if(MyMP3Player.playingSongDetail.getImage()!=null)
    			{
    			InputStream in = new ByteArrayInputStream(MyMP3Player.playingSongDetail.getImage());
    			BufferedImage image=null;
				try {
					image = ImageIO.read(in);
					//int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
					image=MyUtility.resizeImage(image,130,130);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pictureLabel.setIcon(new ImageIcon(image));
    			}//if end
				else
				{
					if(flag==true)
						pictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/cd1.png")));
					else
						pictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/stuff/cd2.png")));
    		
				}
    		}
    	}
    	
    }
    
  

    
	private void hidePopup()
	{
		if(subPopup1!=null&&popup!=null)
		{
			subPopup1.setVisible(false);
			subPopup1=null;
			popup.setVisible(false);
			popup=null;			
		}
		if(playlistPopUp!=null)
		{
			playlistPopUp.setVisible(false);
			playlistPopUp=null;
		}
		if(headerPopup!=null)
		{
			headerPopup.setVisible(false);
			headerPopup=null;
		}
		
	}
    
	private void resetTable(String tableName,String label) //method to reset library song  table when select playlist or create playlist
	{
		 int no=libraryTable.getRowCount();
    	 if(no!=0)//code for erase previous table
    	 {
    		 DefaultTableModel model=(DefaultTableModel)libraryTable.getModel();
    		 model.setRowCount(0);
    	 }
    	 songLibrary.displayData(libraryTable,tableName);
    	 songLibraryLabel.setText(label);
	}
    
	public void showMyDialogue()
    {      
        FileFilter wavFilter = new FileFilter() {
			@Override
			public String getDescription() {
				return "Audio file (*.mp3)";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					return file.getName().toLowerCase().endsWith(".mp3");
				}
			}
		};

		
		openFile.setFileFilter(wavFilter);
		openFile.setDialogTitle("Open Audio File");
		openFile.setAcceptAllFileFilterUsed(false);
    }
    //this method will play a next song
    public void playNext(ActionEvent evt)
    {
        try {        		
            int nextIndex=MyMP3Player.playingSongIndex+1;          
            if(nextIndex>libraryTable.getRowCount()-1)
                nextIndex=0;           
            libraryTable.setRowSelectionInterval(nextIndex,nextIndex);
            playButtonActionPerformed(evt,nextIndex);
        }catch (Exception ex) {
            System.out.println("Exception in nextsong:"+ex);
        }       
    }
    
  
    
    
    public void playPrevious()
    {
        try {
            //player.close();
            int previousIndex=MyMP3Player.playingSongIndex-1;
            System.out.println("PreviousIndex:"+previousIndex);
            if(previousIndex<0)
                previousIndex=libraryTable.getRowCount()-1;            
            //resetAll();          
            libraryTable.setRowSelectionInterval(previousIndex,previousIndex);
           playButtonActionPerformed(null,previousIndex);
                //player=new MyMP3Player(libraryTable.getValueAt(previousIndex,6).toString(),(String)libraryTable.getValueAt(previousIndex,0),previousIndex);
               // setTimer();
               // player.playSong();                
                     
               // setTransition(MyMP3Player.playingSongDetail.getSong(),songLabel);
               // setincreasingTimeLabel(MyMP3Player.playingSongDetail.getDuration());    
            // playButton.setEnabled(false);
            // pauseButton.setEnabled(true);           
        } catch (Exception ex) {
            System.out.println("Exception in previoussong:"+ex);
        }       
    }
    
    
    public void resetAll()
    {
        if(player!=null)
        player.stopSong();
        player=null;
        if(transition!=null)
        transition.setFlagContinue(false);
        transition=null;
        increasingTimeLabel.setText("");
        decreasingTimeLabel.setText("");
        songLabel.setText("");
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);    
        MyMP3Player.playingSongDetail=null;
        Transition.displayString="";
        if(timer!=null)
        timer.reset();
        songSlider.setValue(0);
        albumLabel.setText("");
        artistLabel.setText("");
        yearLabel.setText("");
        genreLabel.setText("");
        albumNameLabel.setText("");
        artistNameLabel.setText("");
        yearNameLabel.setText("");
        genreNameLabel.setText("");
        pictureLabel.setIcon(null);
    }
   
    public void setTransition(String s,JLabel label)
    {
    	//if(this.getName().equals("library")) 
    	//{
    		transition=new Transition(s, label);
      	    transition.start();
    	//}
    }  
    
    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
         
              mainframe=new MainFrame("library");//library is a main frame
              mainframe.setVisible(true);
               
               
        }});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static MainFrame mainframe;
    public static MainFrame playlistFrame;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JButton previousButton;
    private javax.swing.JMenuItem addSong;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JMenuItem deleteSong;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu file;
    private javax.swing.JProgressBar songSlider;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree tree;
    private javax.swing.JTable libraryTable;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel northPanel;
    private javax.swing.JMenuItem openSong;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JPanel sidePanel;
    public javax.swing.JLabel songLabel;
    private javax.swing.JLabel songLibraryLabel;
    private javax.swing.JPanel southPanel;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel increasingTimeLabel;
    private javax.swing.JScrollPane treeScroll;
    private javax.swing.JSlider volumeSlider;
	private JFileChooser openFile;
	private JMenuItem createPlayList;
    // End of variables declaration//GEN-END:variables
    private JPopupMenu popup,headerPopup; 
    private JPopupMenu playlistPopUp;
    private JMenu subPopup; 
   // private JMenuItem addPopup;
    private JMenuItem deletePopup,playPopup,addPopup;
    //private JMenuItem playPopup;
    private JMenuItem playlist1;
    private JMenuItem playsong,increasevolume,decreasevolume,nextsong,previoussong,currentsong;//menu item for control menu
    private JCheckBoxMenuItem shuffle,repeat,album,artist,genre,year,duration;

	public MainFrame getMainframe() {
		return mainframe;
	}
	
	public MainFrame getPlaylistFrame(String name) {
		// Collect all active frames in a array
	    Frame[] activeframes = Frame.getFrames();
	    System.out.println("SIZE OF FRAMES:"+activeframes.length);
	 
	    for (int i=0; i<activeframes.length; i++) {
	        // Get frame's title
	        String title = activeframes[i].getName();
	        if(title.equals(name))
	        {
	        	return (MainFrame)activeframes[i];
	        }
	    }
	       return null;
	}
	
	public int getRowcount()
	{
		return libraryTable.getRowCount();
	}
	
    
    
}
