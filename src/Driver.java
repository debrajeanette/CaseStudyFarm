import java.util.Scanner;

public class Driver {
//
	public static void main(String[] args) {
		
		Farm myFarm = new Farm ();	
		
		myFarm.markBarrenAreas();
		
		myFarm.findFertileAreas();
			
		myFarm.displayFertileArray();
		
 // for debugging		myFarm.displayFarm();
		
	}

}
