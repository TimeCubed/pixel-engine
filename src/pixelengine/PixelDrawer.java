package pixelengine;

import javax.swing.*;
import pixelengine.util.*;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

@SuppressWarnings("unused")
public class PixelDrawer extends JComponent {
	private int[][] pixels;
	private Color drawColor;
	private final int width, height, pixelSize;
	
	/**
	 * Creates a new <code>PixelDrawer</code> object and initializes internal variables
	 */
	public PixelDrawer(int width, int height, int pixelSize) {
		pixels = new int[width][height];
		
		this.width = width;
		this.height = height;
		this.pixelSize = pixelSize;
		
		drawColor = Color.BLACK;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				if (pixels[x][y] != 0) {
					g.setColor(drawColor);
					g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
				}
			}
		}
	}
	
	/**
	 * Sets the current draw color to the given color
	 */
	public void setDrawColor(Color c) {
		drawColor = c;
	}
	
	/**
	 * Draws a pixel at the given coordinates
	 */
	public void drawPixel(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			pixels[x][y] = drawColor.getRGB();
		}
	}
	
	/**
	 * Draws a line from a point at x1, y1 to a point at x2, y2
	 */
	private void drawLine(int x1, int y1, int x2, int y2) {
		float slope = (float) (y2 - y1) / (x2 - x1);
		int prev_y = (int) (y1 - slope);
		
		for (int x = 0; x <= x2 - x1; x++) {
			int y = (int) (slope + prev_y);
			
			double dA = Math.sqrt(Math.pow((y + 0.5) - (x * slope + 0.5), 2));
			double dB = Math.sqrt(Math.pow((y + 1.5) - (x * slope + 0.5), 2));
			
			if (dA < dB) {
				drawPixel(x + x1, y);
			} else {
				drawPixel(x + x1, y + 1);
			}
			
			prev_y = y;
		}
	}
	
	/**
	 * Draws a line from a vector A to another vector B
	 *
	 * @param a The first vector to draw from
	 * @param b The second vector to draw to
	 */
	public void drawLine(Vector2Int a, Vector2Int b) {
		if (b.x < a.x) {
			Vector2Int temp = new Vector2Int(b.x, b.y);
			
			b.x = a.x;
			b.y = a.y;
			
			a.x = temp.x;
			a.y = temp.y;
		}
		
		drawLine(a.x, a.y, b.x, b.y);
	}
	
	public void drawVerticalLine(int x, int y1, int y2) {
		// Swap y1 and y2 if y1 > y2, to always draw from top to bottom
		if (y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		for (int y = y1; y <= y2; y++) {
			drawPixel(x, y);
		}
	}
	
	public int edgeFunction(Vector2Int a, Vector2Int b, Vector2Int c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}
	
	// Code from:
	// https://jtsorlinis.github.io/rendering-tutorial/ (Thanks for the tutorial!)
	public void fillTriangle(Vector2Int v0, Vector2Int v1, Vector2Int v2) {
		// 1 for clockwise, -1 for counter-clockwise
		int windingOrder = 1;
		
		// Correct the winding order if it is not clockwise
		if (edgeFunction(v0, v1, v2) < 0) {
			windingOrder = -1;
		}
		
		// Grab the minimum X and Y values
		int minX = Math.min(Math.min(v0.x, v1.x), v2.x);
		int minY = Math.min(Math.min(v0.y, v1.y), v2.y);
		
		// Grab the maximum X and Y values
		int maxX = Math.max(Math.max(v0.x, v1.x), v2.x);
		int maxY = Math.max(Math.max(v0.y, v1.y), v2.y);
		
		// The top-left corner of the bounding box that contains the triangle
		// will be at (minX, minY), while the bottom-right corner will be at
		// (maxX, maxY), and thus we can loop over each pixel starting from
		// minX, minY and run checks for each pixel.
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				Vector2Int p = new Vector2Int(x, y);
				
				int edge1 = edgeFunction(v0, v1, p);
				int edge2 = edgeFunction(v1, v2, p);
				int edge3 = edgeFunction(v2, v0, p);
				
				if (edge1 * windingOrder >= 0 && edge2 * windingOrder >= 0 && edge3 * windingOrder >= 0) {
					drawPixel(x, y);
				}
			}
		}
	}
	
	/**
	 * Renders a quadrilateral shape using the four vertices given, regardless of their order
	 */
	public void fillQuad(Vector2Int v0, Vector2Int v1, Vector2Int v2, Vector2Int v3) {
		// Calculate the centroid of the quad
		Vector2Int centroid = new Vector2Int((v0.x + v1.x + v2.x + v3.x) / 4, (v0.y + v1.y + v2.y + v3.y) / 4);
		
		// Sort the vertices based on angles in clockwise order
		List<Vector2Int> vertices = Arrays.asList(v0, v1, v2, v3);
		vertices.sort(Comparator.comparingDouble(ve -> Math.atan2(ve.y - centroid.y, ve.x - centroid.x)));
		
		// Fill the quad with the sorted vertices
		fillTriangle(vertices.get(0), vertices.get(1), vertices.get(2));
		fillTriangle(vertices.get(0), vertices.get(2), vertices.get(3));
	}
	
	public void clearPixels() {
		pixels = new int[width][height];
	}
}