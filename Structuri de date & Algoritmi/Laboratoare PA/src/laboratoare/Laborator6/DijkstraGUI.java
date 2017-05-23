package laboratoare.Laborator6;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import laboratoare.Laborator4.Tuple;

public class DijkstraGUI extends JPanel {

	/*
	 * GOAL : Draw the resulting shortest paths tree from the Dijkstra algorithm
	 * Vertices will be drawn on the outer edge of a bigger circle and they will
	 * be represented as a smaller circle with size proportionate to the outer
	 * circle . They will be linked by the according edges as per the resulting
	 * paths obtained from applying Dijkstra .
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<Integer>> paths;
	private ArrayList<ArrayList<Integer>> shortestPaths;

	private int numberOfVertices;

	private int smallSquareSide;

	public DijkstraGUI(ArrayList<ArrayList<Integer>> shortestPaths, ArrayList<ArrayList<Integer>> paths,
			int numberOfVertices) {

		this.paths = paths;
		this.shortestPaths = shortestPaths;
		this.numberOfVertices = numberOfVertices;
	}

	public void paintComponent(Graphics g) {

		Tuple<Tuple<Integer>> requirements = computeRequeirements();

		smallSquareSide = requirements.getSecondElement().getSecondElement();

		List<Tuple<Integer>> smallCirclesCoordinates = computeSmallCirclesCoordinates(requirements);
		Object[][] sPaths = computeLinesCoordinates(smallCirclesCoordinates, shortestPaths);
		Object[][] gPaths = computeLinesCoordinates(smallCirclesCoordinates, paths);

		g.setColor(Color.RED);
		drawEdges(gPaths, g);

		g.setColor(Color.GREEN);
		drawEdges(sPaths, g);

		drawVertices(smallCirclesCoordinates, g, smallSquareSide, 1);

	}

	public void drawVertices(List<Tuple<Integer>> smallCirclesCoords, Graphics g, int smallSquareSize, int startingVertex) {

		int x, y;

		for (int i = 0; i < smallCirclesCoords.size(); i++) {

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(Color.YELLOW);

			x = smallCirclesCoords.get(i).getFirstElement();
			y = smallCirclesCoords.get(i).getSecondElement();
			g.fillOval(x, y, smallSquareSize, smallSquareSize);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", Font.BOLD, 18));

			FontMetrics fm = g.getFontMetrics();
			double textWidth = fm.getStringBounds(Integer.toString(i + 1), g).getWidth();

			g.drawString(Integer.toString(i + 1), (int) (x - textWidth / 2 + smallSquareSide / 2),
					(int) (y + fm.getMaxAscent() / 2 + smallSquareSide / 2));
			
			if (i + 1 == startingVertex) {

				g.setColor(Color.WHITE);
				g.drawString("Start", x + smallSquareSide, y + smallSquareSide);
			}
			
		}
	}

	public void drawEdges(Object[][] linesCoordinates, Graphics g) {

		int xStart, yStart, xEnd, yEnd;

		for (int i = 0; i < numberOfVertices; i++) {

			for (int j = 0; j < numberOfVertices; j++) {

				if (linesCoordinates[i][j] != null) {

					xStart = ((Tuple<Tuple<Integer>>) linesCoordinates[i][j]).getFirstElement().getFirstElement();
					yStart = ((Tuple<Tuple<Integer>>) linesCoordinates[i][j]).getFirstElement().getSecondElement();
					xEnd = ((Tuple<Tuple<Integer>>) linesCoordinates[i][j]).getSecondElement().getFirstElement();
					yEnd = ((Tuple<Tuple<Integer>>) linesCoordinates[i][j]).getSecondElement().getSecondElement();

					g.drawLine(xStart, yStart, xEnd, yEnd);

				}
			}
		}

	}

	public Object[][] computeLinesCoordinates(List<Tuple<Integer>> smallCircleCoords, ArrayList<ArrayList<Integer>> edges) {

		Object[][] linesCoordinates = new Object[numberOfVertices][numberOfVertices];

		List<Integer> path;
		int x, y, startVertex, endVertex;
		Tuple<Integer> lineStartCoordinates, lineEndCoordinates, temp;

		for (int i = 0; i < edges.size(); i++) {

			path = edges.get(i);

			for (int j = 0; j < path.size() - 1; j++) {

				startVertex = path.get(j);
				endVertex = path.get(j + 1);

				temp = smallCircleCoords.get(startVertex - 1);
				x = temp.getFirstElement() + smallSquareSide / 2;
				y = temp.getSecondElement() + smallSquareSide / 2;
				lineStartCoordinates = new Tuple<Integer>(x, y);

				temp = smallCircleCoords.get(endVertex - 1);
				x = temp.getFirstElement() + smallSquareSide / 2;
				y = temp.getSecondElement() + smallSquareSide / 2;
				lineEndCoordinates = new Tuple<Integer>(x, y);

				linesCoordinates[startVertex - 1][endVertex- 1] = 
						(Object) new Tuple<Tuple<Integer>>(lineStartCoordinates, lineEndCoordinates);
			}
		}

		return linesCoordinates;
	}

	public List<Tuple<Integer>> computeSmallCirclesCoordinates(Tuple<Tuple<Integer>> requirements) {

		List<Tuple<Integer>> coordinates = new ArrayList<Tuple<Integer>>();

		int bigX = requirements.getFirstElement().getFirstElement();
		int bigY = requirements.getFirstElement().getSecondElement();
		int bigRadius = requirements.getSecondElement().getFirstElement();

		for (int i = 0; i < numberOfVertices; i++) {

			double theta = 2 * Math.PI * i / numberOfVertices;
			int smallX = (int) Math.round(bigX + bigRadius * Math.cos(theta));
			int smallY = (int) Math.round(bigY + bigRadius * Math.sin(theta));
			coordinates.add(new Tuple<Integer>(smallX, smallY));
		}

		return coordinates;
	}

	public Tuple<Tuple<Integer>> computeRequeirements() {

		// getting half of the width or the height of the pannel in order to
		// compute the radius of the outer circle ; will compute it using the
		// smallest of the two .
		int x = getWidth() / 2;
		int y = getHeight() / 2;

		int m = Math.min(x, y); // we chose the least of these , in case we have
								// a rectangle , the radius ,
								// to fit inside the actual panel .

		int r1 = 4 * m / 5; // 80 % of half of the least of the two sides of the
							// rectangle will be the
							// radius of the outer circle

		int r2 = Math.abs(m - r1) / 2; // this will be the radius of the smaller
										// circles -- the actual vertices

		Tuple<Integer> t1 = new Tuple<Integer>(x, y);
		Tuple<Integer> t2 = new Tuple<Integer>(r1, r2);

		return new Tuple<Tuple<Integer>>(t1, t2);

	}

}
