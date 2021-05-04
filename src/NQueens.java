import java.util.ArrayList;


import javax.swing.JPanel;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;




public class NQueens extends JPanel{
	
	
	//ArrayList<Integer> queens=new ArrayList<Integer>();
	
	//the number of rows and columns
	private static int N;
	//Each index in the list queens represent the column and the value at each index represents the row that queen is placed at.
	static int[] queens;
	//the n-queen puzzle
	private String[][] csp;
	
	//the constructor of the NQueens class
	public NQueens(int n,int[] queens) {
		this.N=n;
		this.queens=queens;
	}
	
	
	public void updateList(int[] queensList){
		queensList = NQueens.queens;
	}
	
	
	//find the number of conflicts that each queen of the column col has
	public static int CountConflicts(int col) {
		int ConflictNums=0;
		int row=queens[col];
		
		//for each column c in the puzzle:
		for(int c=0;c<N;c++) {
			//if the column c it is not the same as the column of the queen which is col then continue:
			if(c!=col) {
				//get the row of the column c that the queen is positioned
				int r=queens[c];
				//if row r is the same as row row, it means the queen of column col and the queen of column c are positioned in the same row
				//if the queen for column col and the queen for column c are positioned diagonally
				if((r==row) || (Math.abs(r-row)==Math.abs(c-col))) {
					ConflictNums++;
					
				}
			}
		}
		return ConflictNums;
	}
	
	//returns false if finds some conflicts for the col and true if no conflict was found
		public Boolean FoundConflict(int col) {
			
			int row=queens[col];
			
			//for each column c in the puzzle:
			for(int c=0;c<N;c++) {
				//if the column c it is not the same as the column of the queen which is col then continue:
				if(c!=col) {
					//get the row of the column c that the queen is positioned
					int r=queens[c];
					//if row r is the same as row row, it means the queen of column col and the queen of column c are positioned in the same row
					//if the queen for column col and the queen for column c are positioned diagonally
					if((r==row) || (Math.abs(r-row)==Math.abs(c-col))) {
						return false;
						
					}
				}
			}
			return true;
		}
	
	
		//checks to see if there is a queen with at least one conflict
		public Boolean CheckForNoConflict(){
		
			//for each column c:
			for (int c=0;c<N;c++) {
				//if the function FoundConflict returns false,it means the column c has conflict and the function doesn't continue with other columns and just returns false.
				if(!FoundConflict(c)) {
					return false;
				}
			}
			return true;
		}
	
	
		//this function applys the algorithm of minConflict to find a solution for puzzle
		public int MinConflicts() {
			
			//the maximum of steps to do the process of solving the n-queen puzzle
			int max_steps=0;
			Random rand=new Random();
			
			//the arraylist that includes the conflicts for each row in the column that the queen is positioned
			ArrayList<Integer> ConflictList=new ArrayList<Integer>();
			
			
			//rows of the same minimum conflicts in the list ConflictList are put in the minConflicts
			ArrayList <Integer> minConflicts=new ArrayList<Integer>();
			
			
			while(max_steps<N*N ) {
				
				//check to see if the puzzle still has conflicts
				if(CheckForNoConflict()) {
					//setCSP(queens,N);
					return max_steps;
				}
//				else{
//					printCoords(N,  queens);
//				}			
				//get a random column to start with
				int ConflictCol=rand.nextInt(N);
				//count the # of conflicts for the queen at that column
				int ConflictVar=CountConflicts(ConflictCol);
				//if there are no conflicts for the current position of queen at that column we need to choose another column
				while(ConflictVar==0) {
					ConflictCol=rand.nextInt(N);
					ConflictVar=CountConflicts(ConflictCol);
				}
				
				
				
				//change the position of the queen in its column from row 0 to row n-1 to find the position with the lowest conflict
				for(int r=0;r<N;r++) {
					//set the row of the queen at the column ConflictCol to the row r
					queens[ConflictCol]=r;
				
					//now count the number of conflicts for the ConflictCol
					int count_conflicts=CountConflicts(ConflictCol);
					
					//add the number of conflicts to the arraylist ConflictList
					ConflictList.add(count_conflicts);
					
				}
			
				//get the minimum conflict variable
				
				int min=Collections.min(ConflictList);
				
				//this for loop goes through each conflict in the ConflictList and if that conflict is equal to the min we add that row to minConflicts
				for(int r=0;r<N;r++) {
					int var=ConflictList.get(r);
					if(var==min) {
						minConflicts.add(r);
					}
					
				}
				
				if(!minConflicts.isEmpty()) {
					//get a random index R of minConflict
					int R=rand.nextInt(minConflicts.size());
					//get the row at index R of minConflicts
					int MinRowIndex=minConflicts.get(R);
					//fix that row as hour new position of queen at the column ConflictCol
					queens[ConflictCol]=MinRowIndex;
					
					
				}
				
				
		
				max_steps++;
				
				if(max_steps==(N*2)+1) {   //going too far ; start over
					this.queens=Test.MakeRandomNQueen(N);
					max_steps=0;
				}
				
				//reset the arraylists
				minConflicts=new ArrayList<>();
				ConflictList=new ArrayList<>();
			}
						
			return max_steps;
			
		}
	
		//create a puzzle by putting "Q" at the position of queens and "_" at the empty position
		public void setCSP(int [] queens,int N) {
			for(int col=0;col<N;col++) {
				for(int row=0;row<N;row++) {
					if(queens[col]==row) {
						csp[row][col]=Queen();
					}else {
						csp[row][col]=empty();
					}
				}
			}
		}
				
		//method that handles the printing of the puzzle 
		public void PrintCSP(){
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					System.out.print(csp[r][c]+" ");
				}
				System.out.println();
			}
		}
	
	//a method to print the location of each queen, rows and column will be printed
	public static void printCoords(int n, int[] queens){
		for(int i=0;i<n;i++) {
			System.out.println("col: "+i+ " row: " + queens[i]);
	
		}
		System.out.println();
	}
	
	//method used to print _ which represents an empty place holder within a puzzle
	public static String empty() {
		return "_";
	}
	
	//method used to print Q which represents a queen that is present within a puzzle
	public static String Queen() {
		return "Q";
	}

	
	public class MinRowConflict implements Callable<Integer> {
	       Integer row;
	       Integer col;
	       MinRowConflict(Integer theRow,Integer theCol)
	       {
	             this.row=theRow;
	             this.col=theCol;             
	       }          
	       public Integer call() throws Exception {
	              // TODO Auto-generated method stub
	    	   queens[col]=row;
	    	   int conflicts=CountConflicts(col);
	             // System.out.println(Thread.currentThread().getName()+" says : the conflicts for col: "+col+" and row: "+row+" is "+conflicts);
	              return conflicts;
	       }
	}
	
	public ArrayList<Integer> FindMinConflicts(int ConflictCol) {
		ArrayList<Integer> ConflictList=new ArrayList<Integer>();
		ExecutorService executor = Executors.newFixedThreadPool(N);
        List <Future<Integer>> list = new ArrayList<Future<Integer>>();
                              
        for(int row=0;row<N;row++) {
        	Future<Integer> future = executor.submit(new MinRowConflict(row,ConflictCol));
        	 try {
        		 int conflictNum=future.get();
        		 ConflictList.add(row, conflictNum);
             	 //System.out.println(conflictNum);
             	 
             } catch (InterruptedException e) {                                               
                 e.printStackTrace();
                 e.printStackTrace();
             } catch (ExecutionException e) {
                 e.printStackTrace();
             }
                                                                          
                                         
        }
        
		return ConflictList;
	}

}

