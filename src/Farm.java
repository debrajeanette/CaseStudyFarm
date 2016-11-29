
import java.util.Scanner;
import java.util.Arrays;

public class Farm {

	int cols = 400;
	int rows = 600;		
	
//	int cols = 5;
//	int rows = 10;
	
	char square [] [] = new char [rows] [cols];   	// the farm as an array of squares
		
	int fertileAreaSize [] = new int [100];  		// array of fertile areas found
	
	int XCount = 0; // running count of all farm squares that haven't yet been marked as barren or fertile
	
	
// CONSTRUCTOR 
//-----
public Farm () {
// initialize all farm squares to X
		
	for (int r = 0; r < rows ; r++) {    	 //loop thru rows
		for(int c=0; c< cols; c++) {		// loop thru columns
				square[r] [c] = 'X';
				XCount ++;
		}
	}	
	
	System.out.println("Welcome to the farm! Total number of farm squares is " + XCount + ".");
	
}
	
 public void markBarrenAreas(){
	
//  example 1	
//	0 292 399 307
	
//	example 2
//	48 192 351 207
//	48 392 351 407
//	120 52 135 547
//	260 52 275 547
			
//  for debugging with 5 X 10 size farm
//	0 7 4 7
//	1 0 1 9
	
	
	Scanner scan = new Scanner (System.in);	

	System.out.println("How many barren areas do you want to mark?");
	int b = scan.nextInt();

	for (int i = 1; i <= b ; i++) {
		System.out.println("For barren area #" + i + ": Key in 4 integers separated by spaces and press enter");
		int x1 = scan.nextInt();
		int y1 = scan.nextInt();
		int x2 = scan.nextInt();
		int y2 = scan.nextInt();	
		
		markBarrenArea(x1, y1, x2, y2);
	}
	
}


 private void markBarrenArea(int x1, int y1, int x2, int y2) {
// set rectangle of barren squares to 'B'
	
		for (int r = 0; r < rows ; r++) {   	//loop thru rows	
			for(int c=0; c< cols; c++) {  		//loop thru columns
				if (r >= y1 && r <= y2  && c >= x1 && c <= x2) {			
					if (square[r] [c] != 'B'){
						square[r] [c] = 'B';
						XCount --;
					}
				}		
			}
		}			
}
			


public void findFertileAreas() {
// find all the separate fertile areas and add size of each one into an array
	
	int fertileAreaCount = 0;   // count of fertile areas
	
	while (XCount > 0) {
		
		int FCount = findAndCountNextFertileArea();  		//find and mark the next fertile area, return the number of fertile squares
		putInFertileAreaArray(FCount, fertileAreaCount);  	// update array of fertile areas with this square count
		
		fertileAreaCount ++;
		
	}
}


private int findAndCountNextFertileArea(){
	
	int FCount = 0; 			//count of squares for this fertile area
	int TCount = 0;      		//count of squares temporarily marked on their way to being marked fertile
	System.out.println("Looking for fertile area...");
	
// Find the first undiscovered fertile square, ie first square that is X and set it to T for Temp. 
// Temp means this square is fertile and there may be other undiscovered fertile squares in the same area. 	
	
		int r = 0;
		int c = 0;
		
		while ( TCount == 0  && r < rows  ) {   //loop thru rows     
			c = 0;
			while (TCount == 0  && c < cols) {	// loop thru columns		
				if (square [r] [c] == 'X' )
				{ 	square[r] [c] = 'T';
					TCount ++;	
					XCount --;
				}
				c++;
			}
			r++;							
		}

// Now find all squares in the same fertile area as the one marked T. This is a bit tricky. 
// Value of squares will go from X to T to F		
// As long as there is at least one square marked T we are not done identifying this fertile area.  
// So we loop until there are no more squares marked T
		
// For each time through the loop:	
//	find first square marked T and change it to F for Fertile 		
// 	look at the 8 squares around this square see if they are X which means they are in the same area, fertile and not yet counted.  
//	mark them as T	

	while (TCount > 0) {		//loop while there are some squares set to T
		boolean foundT = false;
		r = 0;
		c = 0;
		while ( foundT != true  && r < rows      ) {    //loop thru rows
			c = 0;
			while (foundT != true  && c < cols) {		//loop thru columns		
				if (square [r] [c] == 'T' )  {
					foundT = true;
					square[r] [c] = 'F';   
					FCount++;
					TCount--;
										
					if (r > 0 && square [r - 1] [c] == 'X' ) 			{square[r - 1] [c] = 'T';TCount++;XCount --;} 	// top
					
					if (c < (cols - 1) && square [r] [c + 1] == 'X' ) 	{square[r] [c + 1] = 'T';TCount++;XCount --;} 	// right	
					
					if (r < (rows - 1) && square [r + 1] [c] == 'X' ) 	{square[r + 1] [c] = 'T';TCount++;XCount --;} 	// bottom
					
					if (c > 0 && square [r] [c - 1 ] == 'X' ) 			{square[r] [c - 1] = 'T';TCount++;XCount --;} 	// left
									
					
				}	
			
				c++;
			}
			r++;	
		}
	}
		System.out.println("Found a fertile area. Number of squares is " + FCount);
		
		return FCount;
		
		
		
}

		
		
private void putInFertileAreaArray (int size, int row) {
	
	
	fertileAreaSize[row] = size;
	
}
	

public void displayFertileArray () {
// display values of fertile areas, from smallest to highest	
	
	
	System.out.println("**********************************************************");
	System.out.println("The fertile areas from smallest to largest are as follows:");
	System.out.println("**********************************************************");
	Arrays.sort(fertileAreaSize);
	
	for (int i = 0; i < 100 ; i++ ){ 
		if (fertileAreaSize[i] > 0) {
		System.out.println( "Fertile area size "  +  fertileAreaSize [i]);}
	}
		
	
}



public void displayFarm(){
// display values of first 10 rows and first 5 columns	
	for (int r = 0; r < 10 ; r++) {				
		System.out.println(  " row " + r  + "            "  
	+ square[r] [0]  
		+ " " + square [r] [1]	
			+ " " + square [r] [2]
					+ " " + square [r] [3]	
							+ " " + square [r] [4]


		);
		}
		
}
	
}// end of class