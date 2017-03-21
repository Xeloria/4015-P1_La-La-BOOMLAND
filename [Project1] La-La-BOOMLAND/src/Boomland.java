import java.awt.Color;
import java.util.Random;

public class Boomland {
	
	static int[][][] mineList = new int[9][11][3];
	
	private static Random randNum = new Random();

	public static boolean hitMine(int slotX, int slotY){
		// Determines if the space clicked contins a mine or not.
		if(mineList[slotX][slotY][0]==1){
			return true;
		}
		return false;
	}
	
	public static void newGame(){
			
			int mineX, mineY;
			
			for(int i=0;i<9;i++){
				for(int j=0; i<11;j++){
					mineList[i][j][0]=0;
					
				}
			}
			
			for(int m = 0; m<10; m++){
				
				mineX = randNum.nextInt(9);
				mineY = randNum.nextInt(9)+1;
				
				mineList[mineX][mineY][0]=1;		
			}
			 System.out.println("New game!");
	}
	
	

}