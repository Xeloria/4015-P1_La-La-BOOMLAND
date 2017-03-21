import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	
	
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
			myPanel.repaint();
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
		
		Color[] colorBank = new Color[11];
		colorBank[0] = Color.CYAN; 					// No mines
		colorBank[1] = Color.GREEN;					// 1 mine
		colorBank[2] = new Color(154, 255, 0);		// 2 mines
		colorBank[3] =Color.YELLOW;					// 3 mines
		colorBank[4] =Color.ORANGE;					// 4 mines
		colorBank[5] = new Color(255, 255, 0);		// 5 mines
		colorBank[6] = new Color(200,200,0);					// 6 mines
		colorBank[7] = new Color(153, 0, 0);		// 7 mines
		colorBank[8] = new Color(102, 0, 0);		// 8 mines
		colorBank[9] = Color.BLACK;					// BOOM!
		colorBank[10] = Color.RED;					// Flagged mine
		
		
		int randomColorInt = generator.nextInt(5);
		
		
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
									
										myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = colorBank[9];
										Boomland.mineList[gridX][gridY][2]=true;
						
										for(int i = 0; i<9;i++){
											for(int j =0;j<10;j++){
												if(Boomland.mineList[i][j][0]==true){
													myPanel.colorArray[i][j] = colorBank[9];
													Boomland.mineList[i][j][2]=true;
													Boomland.setGameEnd(true);
												}	
											}
										}
									
									}else {
									
									// Open surrounding squares if they do not have a mine nearby
									// Assing respective colors if a mine is nearby
									
										myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = colorBank[0];
										Boomland.mineList[gridX][gridY][2]=true;
										
										
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
							System.out.println("Unflagged!");
							myPanelR.repaint();
							
						}else{
							
						myPanelR.colorArray[gridXR][gridYR] = colorBank[10];
						Boomland.mineList[gridXR][gridYR][1] = true;
						System.out.println("Flagged");
						myPanelR.repaint();
						
						}
					}
				}
			}
				
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}

