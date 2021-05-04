import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
 
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Timer;


public class GUI extends JFrame{
	
	//the number of rows and columns
	//this will prompt asking the user for the n value
	static int n = Test.getQueenValue();
	
	
	//width for the window of GUI
    public static int WIDTH = 1200;
    //height for the window of GUI
    public static int HEIGHT = 1200;
    //size of the squares for the grid
    public static int SQUARE_SIZE = HEIGHT/n;
    //Grid line width
    public static final int LineWidth = WIDTH;
    //Grid line height
    public static final int LineHeight = HEIGHT;
    //Y coord to finish line at
    public static final int ENDLINEPOINT_WIDTH = WIDTH+SQUARE_SIZE;
    //Y coord to finish line at
    public static final int ENDLINEPOINT_HEIGHT = HEIGHT+SQUARE_SIZE;
    //Grid rectangle width
    public static final int recWidth = WIDTH;
    //Grid rectangle height
    public static final int receHeight = HEIGHT;
    
    //max steps taken to solve for each puzzle
    static int max_steps;
    //Start time to solve each puzzle
    static long startTime;
    //end time to solve each puzzle
    static long stopTime;
	
	
    //declaring GUI Buttons, Panels, scrollbar, optionPanel
    static JButton button1,button2;
	JPanel container, Panel1, Panel2;
	JScrollPane scrollPane,s1,s2;
	JOptionPane qVals;
	
	//incrementing to add GUI queens
	static int x= 0;
	
	//Storing all (x,y) coord to be placed on the board
	private static List<Point> fillCells;

	//GRID for creating each puzzle and posting queens on the puzzle
	public static class Grid extends JPanel implements ActionListener{

		//declaring image variable
        private BufferedImage image;
        
        //timer to start the speed to add the queens
        Timer t = new Timer(20,this);
        
        
        //grid constructor, declaring fillcells arrayList to add queens
        public Grid() {
            fillCells = new ArrayList<>(n);
        }

        //a method that allows the creation of the chess board
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int count = 1;
            for (int i= 0; i < n; i++){
            	for (int k= 0; k < n; k++){
            		int cellX = SQUARE_SIZE + (i * SQUARE_SIZE);
                    int cellY = SQUARE_SIZE + (k * SQUARE_SIZE);
	            	if(count%2 == 0){
	            		g.setColor(Color.YELLOW);
	            		g.fillRect(cellX, cellY, SQUARE_SIZE, SQUARE_SIZE);
	  				}else{
	  					g.setColor(Color.WHITE);
	  					g.fillRect(cellX, cellY, SQUARE_SIZE, SQUARE_SIZE);
	
	  				}
	            	count++;
	            }
            	count++;
            }
            
            try {                
                image = ImageIO.read(new File("queen.png"));
             } catch (IOException ex) {
                  // handle exception...
             }
            
            
            for (Point fillCell : fillCells) {
                int cellX = SQUARE_SIZE + (fillCell.x * SQUARE_SIZE);
                int cellY = SQUARE_SIZE + (fillCell.y * SQUARE_SIZE);
                g.drawImage(image, cellX, cellY,SQUARE_SIZE,SQUARE_SIZE, this);
                
            }
            
            g.setColor(Color.BLACK);
            g.drawRect(SQUARE_SIZE, SQUARE_SIZE, recWidth, receHeight);
                    
            for (int i = SQUARE_SIZE; i <= LineWidth; i += SQUARE_SIZE) {
                g.drawLine(i, SQUARE_SIZE, i, ENDLINEPOINT_HEIGHT);
            }

            for (int i = SQUARE_SIZE; i <= LineHeight; i += SQUARE_SIZE) {
                g.drawLine(SQUARE_SIZE, i, ENDLINEPOINT_WIDTH, i);
            }
            
            
        }
        //a method that allows a pause before printing the queens upon the chessboard
        
        public void doNothing(int milliseconds)
        {
            try
            {
                Thread.sleep(milliseconds);
            }
            catch(InterruptedException e)
            {
                System.out.println("Unexpected interrupt"); 
                System.exit(0);
            }
        }

 	   public void actionPerformed(ActionEvent e){
 		   
 		   try {
 			   
		  	int[] q=queenList(NQueens.queens);
			GUI.fillCell(x, q[x]);
 			

			//doNothing(100);
			repaint();
			x+=1;
			
 		   }catch(Exception e1){
 			   
 		   }
 		  
 	   }   
 	   


	};
	
	public static int[] queenList(int[] queenlist){
		return queenlist;
		
	}

	
       public static void fillCell(int x, int y) {
           fillCells.add(new Point(x, y));
           
       }
       
       public static void fillCellRemove(int x, int y) {
           fillCells.remove(new Point(x, y));
           
       }
	   

	public GUI(){
	
		Grid grid = new Grid();
		
	    Panel1 = new JPanel(new BorderLayout());
	    Panel2 = new JPanel();
	    scrollPane = new JScrollPane(Panel2);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setPreferredSize(new Dimension(150,100));
	    button1 = new JButton();
	    button2 = new JButton();

	    setSize(WIDTH+SQUARE_SIZE*4, HEIGHT+SQUARE_SIZE*3);
	    
	    button1.setText("Start");
	    button2.setText("Solve");
    	
	    add(button1, BorderLayout.NORTH);

		Panel1.add(grid);
	    Panel2.setPreferredSize(new Dimension(100, 100));
	    Panel2.setBackground(Color.gray);
	  
	    
		add(Panel1);
  		add(scrollPane, BorderLayout.EAST);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	
	button1.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			
	
			grid.t.start();
			
			if (!fillCells.isEmpty()){
				
			   for(int i=0; i <n;i++){
				   fillCellRemove(i,NQueens.queens[i]);
			   }
			   
			   Panel2.removeAll();
			   repaint();
			}
	    
		    int[] queens=Test.MakeRandomNQueen(n);
		    NQueens nqueen =new NQueens(n,queens);
		    fillCells.removeAll(fillCells);
		    button2.show();
		   for(int i=0; i <n;i++){
			   fillCell(i,NQueens.queens[i]);
			   repaint();
		   }
		   
		    
		    Panel2.add( new JLabel("Conflicts",
		            JLabel.CENTER));
			for(int i=0;i<n;i++) {
				JLabel label2 = new JLabel("col "+i+" : conflicts: "+NQueens.CountConflicts(i)+"   ", SwingConstants.CENTER);
				Panel2.add(label2);
				
			}
			JLabel label3 = new JLabel("# Steps: "+max_steps);
			Panel2.add(label3);
			
			JLabel label4 = new JLabel("Time: "+ ((double)(stopTime-startTime))/1000 + "s.");
			Panel2.add(label4);
			
			add(button2, BorderLayout.SOUTH);
			System.out.println("Random Queens:");
			NQueens.printCoords(n, NQueens.queens);
			setVisible(true);
		};   
	            
	      
	});
	
	button2.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(x==n){
				x=0;   
				fillCells.removeAll(fillCells);
				long startTime=System.currentTimeMillis();
			}
			long startTime=System.currentTimeMillis();
			NQueens nqueen =new NQueens(n,NQueens.queens);
			int max_steps = nqueen.MinConflicts();
			
			
	  		Panel2.removeAll();
		    Panel2.add( new JLabel("Conflicts",
		            JLabel.CENTER));
		    
			for(int i=0;i<n;i++) {
				JLabel label2 = new JLabel("col "+i+" : conflicts: "+nqueen.CountConflicts(i)+"   ", SwingConstants.CENTER);
				Panel2.add(label2);
			}
			JLabel label3 = new JLabel("# Steps: "+max_steps);
			Panel2.add(label3);
			
			JLabel label4 = new JLabel("Time: "+ ((double)(System.currentTimeMillis()-startTime))/1000 + "s.");
			Panel2.add(label4);
			setVisible(true);
			button2.hide();
			System.out.println("Solved Queens:");
			NQueens.printCoords(n, NQueens.queens);
			System.out.println("-----------------------------");
			button1.setText("Solve a New Puzzle");
			
			for(int i=0;i<n;i++) {
				System.out.println("col "+i+" : conflicts: "+nqueen.CountConflicts(i)+"   ");
			}
		}
		});
	}}
			
			         