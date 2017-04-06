

import javax.swing.JFrame;

import javax.swing.JLabel;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("La La Boomland");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(350, 410);
		
		
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
		+ "When you lose the top button turns RED." + "<br>"
		+ "and BLUE when you win." + "<br>"
		+ "Click the top button to begin a new game!" + "<br>"
		+ "----------------------------------" + "<br>"
		+ "No mines = Light CYAN" + "<br>"
		+ "1 mine = BLUE" + "<br>"
		+ "2 mines = INDIGO" + "<br>"
		+ "3 mines = YELLOW" + "<br>"
		+ "4 mines = PINK" + "<br>"
		+ "5 mines = ORANGE" + "<br>"
		+ "6 mines = PURPLE" + "<br>"
		+ "7 mines = LIME" + "<br>"
		+ "8 mines = DARK MAGENTA" + "<br>";
	
		JLabel Legend = new JLabel ("<html><div style='text-align: center;'>" + Title + "</div></html>");
		
		
		JFrame myLegend = new JFrame("La-La-Legend");
		myLegend.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myLegend.setLocation(800, 150);
		myLegend.setSize(250, 450);
		myLegend.add(Legend);
		myLegend.setVisible(true);
	
		
		
	}

	
	
	
	
}