package laboratoare.Laborator6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DijkstraGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<String>> paths;
	private int x, y;

	public DijkstraGUI(ArrayList<ArrayList<String>> paths, int x, int y) {

		this.paths = paths;
		this.x = x;
		this.y = y;
		this.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		
		g.setFont(new Font("Serif", Font.PLAIN, 50));
		g.setColor(Color.WHITE);
		
		int shiftX = 0; 
		int shiftY;
		
		for(int i = 0 ; i < paths.size() ; i ++){
			
			shiftX = shiftX + 100;
			shiftY = 0;
			
			for(int j = 0 ; j < paths.get(i).size() ; j ++){
				
				g.drawString(paths.get(i).get(j), x + shiftX, y + shiftY);
				shiftY = shiftY + 100;
			}
		}
	}
}
