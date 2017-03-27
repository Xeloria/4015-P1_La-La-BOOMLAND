import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import java.awt.Graphics;
//	import java.awt.event.WindowAdapter;
//	import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	
	
public class NumberDisplay extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int mineX;
	public static int getMineX() {return mineX;}
	public static void setMineX(int mineX) {NumberDisplay.mineX = mineX;}
	
	private static int mineY;
	public static int getMineY() {return mineY;}
	public static void setMineY(int mineY) {NumberDisplay.mineY = mineY;}


		public void paintComponent (Graphics g) {
			super.paintComponents(g);
			g.setFont(new Font("Arial",Font.BOLD,50));
			for(int i = 0;i<8;i++){
				for(int z = 0;z<8;z++){
					
					
					int nearbyMines = Boomland.nearbyMines[mineX][mineY];
					boolean testnumber = Boomland.mineList[mineX][mineY][2];
					
					if(testnumber){
						
					}
					else{
						switch(nearbyMines){
							case 1:
								g.setColor(Color.BLUE);
								g.drawString("1",i*50,z*50);	
								break;
							case 2:
								g.setColor(Color.GREEN);
								g.drawString("2",i*50,z*50);	
								break;
							case 3:
								g.setColor(Color.MAGENTA);
								g.drawString("3",i*50,z*50);	
								break;
							case 4:
								g.setColor(Color.RED);
								g.drawString("4",i*50,z*50);	
								break;
							case 5:
								g.setColor(Color.YELLOW);
								g.drawString("5",i*50,z*50);	
								break;
							case 6:
								g.setColor(Color.CYAN);
								g.drawString("6",i*50,z*50);	
								break;
							case 7:
								g.setColor(Color.GRAY);
								g.drawString("7",i*50,z*50);	
								break;
							case 8:
								g.setColor(Color.BLACK);
								g.drawString("8",i*50,z*50);	
								break;
							default:
								break;
						}
					}
				}
			}
		}

//	@SuppressWarnings("deprecation")
//	public static void main(String[] args) {
//	    JFrame frame = new JFrame();
//		frame.setTitle("Practice");
//
//	    frame.setSize(400, 400);
//	    frame.addWindowListener(new WindowAdapter() {
//	    	public void windowClosing(WindowEvent e) {
//	            System.exit(0);
//	          }
//	    });
//	    Container contentPane = frame.getContentPane();
//	    contentPane.add(new NumberDisplay());
//
//	    frame.show();
//	}

		


			
}