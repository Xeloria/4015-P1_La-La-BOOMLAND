import java.util.Random;

public class Boomland {
	
	static boolean[][][] mineList = new boolean[9][10][3]; 
	//mineList[x][y][0] = mine/no mine
	//mineList[x][y][1] = flag/no flag
	//mineList[x][y][2] = clicked/not clicked
	private static Random randNum = new Random();
	private static Boolean gameEnd;

	public static Boolean getGameEnd() {
		return gameEnd;
	}

	public static void setGameEnd(Boolean gameEnd) {
		Boomland.gameEnd = gameEnd;
	}

	public static void newGame(){
			int mineX, mineY;
			gameEnd = false;
			
			
			for(int i=0;i<9;i++){
				for(int j=0; j<10;j++){
					for(int k = 0; k<3;k++){
						
					mineList[i][j][k]=false;
					System.out.println("mineList["+i+"]["+j+"]["+k+"] = "+mineList[i][j][k]);
					
					}
				}
			}
			
			for(int m = 0; m<10; m++){
				
				mineX = randNum.nextInt(9);
				mineY = randNum.nextInt(9)+1;
				mineList[mineX][mineY][0]=true;	
				
			}
			
		 System.out.println("New game!");
	}
	
	

}