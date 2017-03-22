import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	



	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			
			if(gridX == 4 && gridY == 0){
				myPanel.colorArray[gridX][gridY]=Color.BLUE;
			}
			
			
			break;
		case 3:		//Right mouse button
			//Do nothing
			Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame) cR;
			MyPanel myPanelR = (MyPanel) myFrameR.getContentPane().getComponent(0);
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			myPanelR.mouseDownGridX = myPanelR.getGridX(xR, yR);
			myPanelR.mouseDownGridY = myPanelR.getGridY(xR, yR);
			myPanelR.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	
	
	
	public void mouseReleased(MouseEvent e) {
		
		
		
		Color[] colorBank = new Color[3];
		colorBank[0] = Color.LIGHT_GRAY; 			// No mines	
		colorBank[1] = Color.RED;					// Flagged mine
		colorBank[2] = Color.BLACK;					// BOOM!		
		
		String[] nearbyMines = new String[9];
		nearbyMines[0]=" ";
		nearbyMines[1]="1";
		nearbyMines[2]="2";
		nearbyMines[3]="3";
		nearbyMines[4]="4";
		nearbyMines[5]="5";
		nearbyMines[6]="6";
		nearbyMines[7]="7";
		nearbyMines[8]="8";
		
		

		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
			
						if(gridX==4 && gridY==0){
							// New game button
							myPanel.colorArray[gridX][gridY]=Color.GREEN;
									Boomland.newGame();
									for (int i = 0; i < 9; i++) {   
										for (int j = 1; j < 10; j++) {
											myPanel.colorArray[i][j] = Color.WHITE;
										}
									}
									
									
						} else {
							
							if(!(Boomland.getGameEnd())){
							
							if(gridY!=0){
								
								if(!(Boomland.mineList[gridX][gridY][1])){
								
									System.out.println("gridX = " + gridX + "; gridY = " + gridY);
								
									if(Boomland.mineList[gridX][gridY][0]){
										myPanel.colorArray[4][0]=Color.RED;
										myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = colorBank[2];
										Boomland.mineList[gridX][gridY][2]=true;
										System.out.println("gridX = " + gridX + "; gridY = " + gridY + " Mine!");
						
										for(int i = 0; i<9;i++){
											for(int j =0;j<10;j++){
												if(Boomland.mineList[i][j][0]){
													myPanel.colorArray[i][j] = colorBank[2];
													Boomland.mineList[i][j][2]=true;
													Boomland.setGameEnd(true);
												}	
											}
										}
									
									}else {
									
									// Open surrounding squares if they do not have a mine nearby
									// Assing respective numbers if a mine is nearby
										
										myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = colorBank[0];
										Boomland.mineList[gridX][gridY][2]=true;
										
										if((((gridX!=0)||(gridX!=8))&&((gridY!=1)||(gridY!=9)))){
											
											for(int i = gridX-1; i<gridX+2;i++){
												for(int j = gridY-1; j<gridY+2;j++){
													
													if(!(Boomland.mineList[i][j][0])&&((i!=-1)||(j!=0))){
														myPanel.colorArray[i][j] = colorBank[0];
														Boomland.mineList[i][j][2]=true;
														System.out.println("gridX = " + i + "; gridY = " + j + " Uncovered!");
														if(!(Boomland.mineList[i][j][0])&&(((i!=0)||(i!=8))&&((j!=1)||(j!=9)))){
															for(int m = i-1;m<i+2;m++){
																for(int n = j-1;n<i+2;n++){
																	if(!(Boomland.mineList[m][n][0])){
																		if(m!=-1||n!=0){
																		myPanel.colorArray[m][n] = colorBank[0];
																		Boomland.mineList[m][n][2]=true;
																		System.out.println("gridX = " + m + "; gridY = " + n + " Uncovered!");
																		}
																	}else{
																		System.out.println("Mine nearby!");
																	}
																}
															}
														}
													}else{
														System.out.println("Mine nearby!");
													}
													
												}
											}	
										}
										
										

										
										}
									}
								}
							}
							myPanel.repaint();
							
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame) cR;
			MyPanel myPanelR = (MyPanel) myFrameR.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			int gridXR = myPanelR.getGridX(xR, yR);
			int gridYR = myPanelR.getGridY(xR, yR);
			
			if(gridYR != 0){
				
				// Flags
				if(!(Boomland.getGameEnd())){
				
					if(myPanelR.colorArray[gridXR][gridYR].equals(Color.WHITE)||myPanelR.colorArray[gridXR][gridYR].equals(Color.RED)){
						
						if(Boomland.mineList[gridXR][gridYR][1]){
							
							myPanelR.colorArray[gridXR][gridYR] = Color.WHITE;
							Boomland.mineList[gridXR][gridYR][1] = false;
							System.out.println("gridX = " + gridXR + "; gridY = " + gridYR + " Unflagged!");
							myPanelR.repaint();
							
						}else{
							
						myPanelR.colorArray[gridXR][gridYR] = colorBank[1];
						Boomland.mineList[gridXR][gridYR][1] = true;
						System.out.println("gridX = " + gridXR + "; gridY = " + gridYR + " Flagged!");
						myPanelR.repaint();
						
						}
					}
				}
			}			
			break;
		default:    
			//Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}

