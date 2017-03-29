

import javax.swing.JFrame;

import javax.swing.JLabel;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("La-La-Boomland");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(350, 410);
		
		
		String welcome = "Welcome to La-La-BOOMLAND!";
		String dash = "\n-----------------------------";
		String instructions = "\nClick inside the grid to clear it.\nBe careful not to hit a mine!\nFlag a square you suspect has a mine\nwith the mouse right button.\nFlag all 10 mines to win!";
		String colorCode = "\nColor code for nearby mines:\nNo mines: Light Cyan\n1 mine: Blue\n2 mines: Light Puerple\n3 mines: Purple\n4 mines: pink\n5 mines: Orange\n6 mines: Yellow\n7 mines: Lime\n8 mines: Dark Magenta";
		
		
		
//		JLabel legend = new JLabel(welcome+"\n"+dash+"\n"+instructions+"\n"+dash+"\n"+colorCode);
//		legend.setBackground(Color.BLACK);
//		legend.setForeground(Color.WHITE);
////		legend.setLocation(400, 150);
//		JFrame instructionFrame = new JFrame("Instructions");
//		instructionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		instructionFrame.setLocation(500, 150);
//		instructionFrame.setSize(350, 410);
//		instructionFrame.add(legend);
//		instructionFrame.setVisible(true);
		
		
		
		
		
		
		
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		myFrame.setVisible(true);
		
		
		Boomland.newGame();
		
		
		String Title = "Welcome to La-La-BOOMLAND!" + "<br>"
		+ "----------------------------------" + "<br>"
		+ "Click inside the grid to clear it." + "<br>" 
		+ "Be careful not to hit a mine!" + "<br>" 
		+ "Flag a square you suspect has a " + "<br>" 
		+ "mine with the mouse right button." + "<br>" 
		+ "Flag all 10 mines to win!" + "<br>"
		+ "----------------------------------" + "<br>"
		+ "No mines = Light Cyan" + "<br>"
		+ "1 mine = Blue" + "<br>"
		+ "2 mines = Indigo" + "<br>"
		+ "3 mines = Yellow" + "<br>"
		+ "4 mines = Pink" + "<br>"
		+ "5 mines = Orange" + "<br>"
		+ "6 mines = Purple" + "<br>"
		+ "7 mines = Lime" + "<br>"
		+ "8 mines = Dark Magenta" + "<br>";
	
		JLabel Legend = new JLabel ("<html><div style='text-align: center;'>" + Title + "</div></html>");
		
		
		JFrame myLegend = new JFrame("La-La-Legend");
		myLegend.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myLegend.setLocation(800, 150);
		myLegend.setSize(200, 400);
		myLegend.add(Legend);
		myLegend.setVisible(true);
	
		
		
	}

	
	
	
	
}