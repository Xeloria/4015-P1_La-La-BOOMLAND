import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	
	int pGridX;
	int pGridY;


	public void mousePressed(MouseEvent e) {
		try {
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
			MyPanel myPanelR = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanelR.x = x;
			myPanelR.y = y;
			myPanelR.mouseDownGridX = myPanelR.getGridX(x, y);
			myPanelR.mouseDownGridY = myPanelR.getGridY(x, y);
			int gridXR = myPanelR.getGridX(x, y);
			int gridYR = myPanelR.getGridY(x, y);
			myPanelR.repaint();
			
			pGridX = gridXR;
			pGridY = gridYR;
			
			
							
			if(gridXR == 4 && gridYR == 0){
				if(!(Boomland.getGameEnd())){
					myPanelR.colorArray[gridXR][gridYR]= new Color(0,204,0);
				}else{
					myPanelR.colorArray[gridXR][gridYR]= new Color(204,0,0);
				}
			}
			if(gridYR!=0){
				if(!(Boomland.getGameEnd())){
					if(!Boomland.mineList[gridXR][gridYR][2]&&!Boomland.mineList[gridXR][gridYR][1]){
						myPanelR.colorArray[gridXR][gridYR] = new Color(224, 224, 224);
					}
				}	
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
			MyPanel myPanelR1 = (MyPanel) myFrameR.getContentPane().getComponent(0);
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR1.x = xR;
			myPanelR1.y = yR;
			myPanelR1.mouseDownGridX = myPanelR1.getGridX(xR, yR);
			myPanelR1.mouseDownGridY = myPanelR1.getGridY(xR, yR);
			myPanelR1.repaint();
			
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}catch(Exception exc) {/*User clicked outside the grid; do nothing.*/}
}
	
	
	
	public void mouseReleased(MouseEvent e) {
		
		
		Color[] colorBank = new Color[11];
		colorBank[0] = new Color(204,255,255);		//Light Cyan
		colorBank[1] = new Color(51,153,255);		//Blue
		colorBank[2] = new Color(102,102,255);		//Indigo
		colorBank[3] = new Color(255, 255, 102);	//Yellow
		colorBank[4] = new Color(255, 102, 255);	//Pink	
		colorBank[5] = new Color(255, 178, 102);	//Orange
		colorBank[6] = new Color(178, 102, 255);	//Purple
		colorBank[7] = new Color(204,255,153);		//Lime	
		colorBank[8] = new Color(102, 0, 51);		//Dark Magenta
		colorBank[9] = Color.RED;					
		colorBank[10] = Color.BLACK;
			
		
		
		
		try {

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
			MyPanel myPanelR = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanelR.x = x;
			myPanelR.y = y;
//			int gridX = myPanel.getGridX(x, y);
//			int gridY = myPanel.getGridY(x, y);
			
			int gridXR = pGridX;
			int gridYR = pGridY;
			
			if ((myPanelR.mouseDownGridX == -1) || (myPanelR.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridXR == -1) || (gridYR == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanelR.mouseDownGridX != gridXR) || (myPanelR.mouseDownGridY != gridYR)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
			
						if(gridXR==4 && gridYR==0){
							// New game button
							myPanelR.colorArray[gridXR][gridYR]=Color.GREEN;
									Boomland.newGame();
									for (int i = 0; i < 9; i++) {   
										for (int j = 1; j < 10; j++) {
											myPanelR.colorArray[i][j] = Color.WHITE;
										}
									}
						
						} else {
							
							if(!(Boomland.getGameEnd())){
							
							if(gridYR!=0){
								
								if(!(Boomland.mineList[gridXR][gridYR][1])){
									
										if(Boomland.mineList[gridXR][gridYR][0]){
											myPanelR.colorArray[4][0]=Color.RED;
											myPanelR.colorArray[myPanelR.mouseDownGridX][myPanelR.mouseDownGridY] = colorBank[10];
											//Boomland.mineList[gridX][gridY][2]=true;
											System.out.println("gridX = " + gridXR + "; gridY = " + gridYR + "; You hit a Mine!");
							
											for(int i = 0; i<9;i++){
												for(int j =0;j<10;j++){
													if(Boomland.mineList[i][j][0]){
														myPanelR.colorArray[i][j] = colorBank[10];
														Boomland.mineList[i][j][2]=true;
														Boomland.setGameEnd(true);
													}	
												}
											}
										
										}else {
				
										// Open surrounding squares if they do not have a mine nearby
										// Assign respective numbers if a mine is nearby
				
											
											myPanelR.colorArray[gridXR][gridYR]=colorBank[Boomland.nearbyMines[gridXR][gridYR]];
											Boomland.mineList[gridXR][gridYR][2]=true;
											System.out.println("gridX = " + gridXR + "; gridY = " + gridYR+"; has "+Boomland.nearbyMines[gridXR][gridYR]+" nearbyMines");
											Boomland.neighbors(e,gridXR,gridYR);
											
											for(int i = 0; i<9;i++){
												for(int j = 1; j<10;j++){	
													if(!Boomland.mineList[i][j][0]&&Boomland.mineList[i][j][2])
																myPanelR.colorArray[i][j]=colorBank[Boomland.nearbyMines[i][j]];
																
												}									
											}
										}
									}
								}
							}
						}
					}
				}								
			}
			Boomland.gameWin(e);		
			myPanelR.repaint();
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
			MyPanel myPanelR1 = (MyPanel) myFrameR.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR1.x = xR;
			myPanelR1.y = yR;
			int gridXR1 = myPanelR1.getGridX(xR, yR);
			int gridYR1 = myPanelR1.getGridY(xR, yR);
			
			
			
			
			
			
				if(Boomland.mineList[gridXR1][gridYR1][1]&&!Boomland.getGameEnd()){
					
					myPanelR1.colorArray[gridXR1][gridYR1] = Color.WHITE;
					Boomland.mineList[gridXR1][gridYR1][1] = false;
					System.out.println("gridX = " + gridXR1 + "; gridY = " + gridYR1 + "; Unflagged!");
					myPanelR1.repaint();
					
				}else{
					if(!Boomland.mineList[gridXR1][gridYR1][2]&&!Boomland.getGameEnd()){
					
						myPanelR1.colorArray[gridXR1][gridYR1] = colorBank[9];
						Boomland.mineList[gridXR1][gridYR1][1] = true;
						System.out.println("gridX = " + gridXR1 + "; gridY = " + gridYR1 + "; Flagged!");
						Boomland.gameWin(e);
						myPanelR1.repaint();
					}
				}
							
			break;
		default:    
			//Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
		}catch(Exception exc) {/*User clicked outside the grid; do nothing.*/}
	}
}

