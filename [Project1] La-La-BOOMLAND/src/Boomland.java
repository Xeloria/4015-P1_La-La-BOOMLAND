import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class Boomland extends MouseAdapter{
	
	static boolean[][][] mineList = new boolean[9][10][3]; 
	//mineList[x][y][0] = mine/no mine
	//mineList[x][y][1] = flag/no flag
	//mineList[x][y][2] = clicked/not clicked
	static int[][] nearbyMines = new int[9][10];
	//nearbyMines[x][y] will hold a value for how many mines are around [x][y] slot.
	
	private static Random randNum = new Random();
	private static Boolean gameEnd;
	private static int neighborX;
	private static int neighborY;
	// GETTERS & SETTERS
		public static int getNeighborX(){return neighborX;}
		public static int getNeighborY(){return neighborY;}
		public static Boolean getGameEnd(){return gameEnd;}
		public static void setGameEnd(Boolean gameEnd) {Boomland.gameEnd = gameEnd;}
	
	//
	public static void newGame(){
			int mineX, mineY;
			gameEnd = false;
			
			//Clearing data
			for(int i=0;i<9;i++){
				for(int j=0; j<10;j++){
					
					nearbyMines[i][j]=0;
					
					for(int k = 0; k<3;k++){
					mineList[i][j][k]=false;
					System.out.println("mineList["+i+"]["+j+"]["+k+"] = "+mineList[i][j][k]);
					}
				}
			}
			//Setting up the mines
			for(int m = 0; m<10; m++){
				mineX = randNum.nextInt(9);
				mineY = randNum.nextInt(9)+1;
				mineList[mineX][mineY][0]=true;	
				System.out.println(mineX+" "+ mineY+" has a mine");
			}
			
			minesNearby();
	
		 System.out.println("New game!");
	}
	
	public static void minesNearby(){
		
		for(int i = 0;i<9;i++){
			for(int j = 1; j<10; j++){
				
				if(mineList[i][j][0]){
					nearbyMines[i][j]=99;
				}else{
					//Center
					if(i>0&&i<8&&j>1&&j<9){
						for(int m = i-1;m<i+2;m++){
							for(int n = j-1;n<j+2;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");
								}
							}
						}
					}
					
					//Left
					if(i==0&&(j!=1&&j!=9)){
						for(int m = i;m<i+1;m++){
							for(int n = j-1;n<j+2;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");								}
							}
						}
					}
					
					//Right
					if(i==8&&(j!=1&&j!=9)){
						for(int m = i-1;m<i;m++){
							for(int n = j-1;n<j+2;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");								}
							}
						}
					}
					
					//Top
					if(j==1&&(i!=0&&i!=8)){
						for(int m = i-1;m<i+2;m++){
							for(int n = j;n<j+1;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");
								}
							}
						}
					}
					
					//Bottom
					if(j==1&&(i!=0&&i!=8)){
						for(int m = i-1;m<i+2;m++){
							for(int n = j-1;n<j+1;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");								}
							}
						}
					}
					
					//Top left
					if(i==0&&j==1){
						for(int m = i;m<i+1;m++){
							for(int n = j;n<j+1;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");								}
							}
						}
					}
					
					//Bottom left
					if(i==0&&j==9){
						for(int m = i;m<i+1;m++){
							for(int n = j-1;n<j+1;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");								}
							}
						}
					}
					
					//Top right
					if(i==8&&j==1){
						for(int m = i-1;m<i+1;m++){
							for(int n = j;n<j+1;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");								}
							}
						}
					}
					
					//Bottom right
					if(i==0&&j==1){
						for(int m = i;m<i+1;m++){
							for(int n = j-1;n<j+1;n++){
								if((mineList[m][n][0])){
									nearbyMines[i][j]++;
									System.out.println("gridX = "+i+"; gridY = "+j+"; has "+nearbyMines[i][j]+" nearbyMines");								}
							}
						}
					}
					
				
					
					
					
				}
				
			}
		}
	}
		
	
	
	
	public static void neighbors(MouseEvent e, int mineX, int mineY){
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {return;}}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel

		
		Color[] colorBank = new Color[3];
		colorBank[0] = Color.LIGHT_GRAY; 			// No mines	
		colorBank[1] = Color.RED;					// Flagged mine
		colorBank[2] = Color.BLACK;					// BOOM!	
		
		int left = mineX-1;
		int right = mineX+1;
		int up = mineY-1;
		int down = mineY+1;

		if(nearbyMines[mineX][mineY]!=0&&(!mineList[mineX][mineY][2])&&(!mineList[mineX][mineY][0])){
			return;
		}else{
			
			//Center
			if(mineX>0&&mineX<8&&mineY>1&&mineY<9){			
				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][up] = colorBank[0];
					mineList[mineX][up][2]=true;
					neighbors(e, mineX, up);
				}
				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][down] = colorBank[0];
					mineList[mineX][down][2]=true;
					neighbors(e, mineX, down);
				}
				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[left][mineY] = colorBank[0];
					mineList[left][mineY][2]=true;
					neighbors(e, left, mineY);
				}			
				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[right][mineY] = colorBank[0];
					mineList[right][mineY][2]=true;
					neighbors(e, right, mineY);
					}
					return;
			}
			
			//Left
			if(mineX==0&&(mineY>1&&mineY<9)){
				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][up] = colorBank[0];
					mineList[mineX][up][2]=true;
					neighbors(e, mineX, up);
				}			
				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][down] = colorBank[0];
					mineList[mineX][down][2]=true;
					neighbors(e, mineX, down);
				}		
//				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[left][mineY] = colorBank[0];
//					mineList[left][mineY][2]=true;
//					neighbors(e, left, mineY);
//				}			
				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[right][mineY] = colorBank[0];
					mineList[right][mineY][2]=true;
					neighbors(e, right, mineY);
					}
					return;
			}
			
			//Right
			if(mineX==8&&(mineY>1&&mineY<9)){
				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][up] = colorBank[0];
					mineList[mineX][up][2]=true;
					neighbors(e, mineX, up);
				}
				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][down] = colorBank[0];
					mineList[mineX][down][2]=true;
					neighbors(e, mineX, down);
				}
				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[left][mineY] = colorBank[0];
					mineList[left][mineY][2]=true;
					neighbors(e, left, mineY);
				}
//				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[right][mineY] = colorBank[0];
//					mineList[right][mineY][2]=true;
//					neighbors(e, right, mineY);
//					}
					return;
			}
			
			//Top
			if((mineX>0&&mineX<8)&&mineY==1){
//				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[mineX][up] = colorBank[0];
//					mineList[mineX][up][2]=true;
//					neighbors(e, mineX, up);
//				}
				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][down] = colorBank[0];
					mineList[mineX][down][2]=true;
					neighbors(e, mineX, down);
				}
				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[left][mineY] = colorBank[0];
					mineList[left][mineY][2]=true;
					neighbors(e, left, mineY);
				}
				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[right][mineY] = colorBank[0];
					mineList[right][mineY][2]=true;
					neighbors(e, right, mineY);
					}
					return;
			}
			
			//Bottom
			if((mineX>0&&mineX<8)&&mineY==9){
				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][up] = colorBank[0];
					mineList[mineX][up][2]=true;
					neighbors(e, mineX, up);
				}
//				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[mineX][down] = colorBank[0];
//					mineList[mineX][down][2]=true;
//					neighbors(e, mineX, down);
//				}
				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[left][mineY] = colorBank[0];
					mineList[left][mineY][2]=true;
					neighbors(e, left, mineY);
				}
				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[right][mineY] = colorBank[0];
					mineList[right][mineY][2]=true;
					neighbors(e, right, mineY);
					}
					return;
			}
			
			//Top Left
			if(mineX==0&&mineY==1){
//				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[mineX][up] = colorBank[0];
//					mineList[mineX][up][2]=true;
//					neighbors(e, mineX, up);
//				}
				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][down] = colorBank[0];
					mineList[mineX][down][2]=true;
					neighbors(e, mineX, down);
				}
//				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[left][mineY] = colorBank[0];
//					mineList[left][mineY][2]=true;
//					neighbors(e, left, mineY);
//				}
				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[right][mineY] = colorBank[0];
					mineList[right][mineY][2]=true;
					neighbors(e, right, mineY);
					}
					return;
			}
			
			//Top Right
			if(mineX==8&&mineY==1){
//				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[mineX][up] = colorBank[0];
//					mineList[mineX][up][2]=true;
//					neighbors(e, mineX, up);
//				}
				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][down] = colorBank[0];
					mineList[mineX][down][2]=true;
					neighbors(e, mineX, down);
				}
				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[left][mineY] = colorBank[0];
					mineList[left][mineY][2]=true;
					neighbors(e, left, mineY);
				}
//				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[right][mineY] = colorBank[0];
//					mineList[right][mineY][2]=true;
//					neighbors(e, right, mineY);
//					}
					return;
			}
			
			//Bottom Left
			if(mineX==0&&mineY==9){
				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][up] = colorBank[0];
					mineList[mineX][up][2]=true;
					neighbors(e, mineX, up);
				}
//				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[mineX][down] = colorBank[0];
//					mineList[mineX][down][2]=true;
//					neighbors(e, mineX, down);
//				}
//				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[left][mineY] = colorBank[0];
//					mineList[left][mineY][2]=true;
//					neighbors(e, left, mineY);
//				}
				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[right][mineY] = colorBank[0];
					mineList[right][mineY][2]=true;
					neighbors(e, right, mineY);
					}
					return;
			}
			
			//Bottom Right
			if(mineX==8&&mineY==9){
				if(!mineList[mineX][up][0]&&!mineList[mineX][up][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[mineX][up] = colorBank[0];
					mineList[mineX][up][2]=true;
					neighbors(e, mineX, up);
				}
//				if(!mineList[mineX][down][0]&&!mineList[mineX][down][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[mineX][down] = colorBank[0];
//					mineList[mineX][down][2]=true;
//					neighbors(e, mineX, down);
//				}
				if(!mineList[left][mineY][0]&&!mineList[left][mineY][2]&&nearbyMines[mineX][mineY]==0){
					myPanel.colorArray[left][mineY] = colorBank[0];
					mineList[left][mineY][2]=true;
					neighbors(e, left, mineY);
				}
//				if(!mineList[right][mineY][0]&&!mineList[right][mineY][2]&&nearbyMines[mineX][mineY]==0){
//					myPanel.colorArray[right][mineY] = colorBank[0];
//					mineList[right][mineY][2]=true;
//					neighbors(e, right, mineY);
//					}
					return;
			}
			
			
		}
	}
}
		