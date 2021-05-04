import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Test {

    static NQueens nqueen;
    static int max_steps;
    static long startTime;
    static long stopTime;
    static int queenVals;
    static JOptionPane qVals;

	public static void main(String[] args) {
		//a JOptionPane is made to get user to enter values for the number of queens 
		qVals = new JOptionPane();
		//the string value is taken from the user input
		String value = JOptionPane.showInputDialog("Enter Number of queens: ");
		//string value from above has been parsed into an integer
		queenVals = Integer.parseInt(value);
				
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                
                GUI gui = new GUI();
                
               
            }

        });
		
	}

	//method used to print _ which represents an empty place holder within a puzzle
	public static String empty() {
		return "_";
	}
	//method used to print Q which represents a queen that is present within a puzzle
	public static String Queen() {
		return "Q";
	}
	//make random puzzle 
	 public static int[] MakeRandomNQueen(int n) {
		 
		 Random rand=new Random();
		 int queens[]=new int[n];
		// System.out.print("[");
        for (int i = 0;i < n; i++) {
            queens[i] = rand.nextInt(n);
        //    System.out.print(" "+queens[i]);
        }
       // System.out.println(" ]");
       
		return queens;
    }

	 //Getter method for returning values of queens, used in order to set up gui depending on value of queens
	 public static int getQueenValue(){
		return queenVals;
		 
	 }

}
