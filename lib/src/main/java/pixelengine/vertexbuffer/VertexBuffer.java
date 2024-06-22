package pixelengine.vertexbuffer;

import pixelengine.PixelDrawer;
import pixelengine.util.Triangle;
import pixelengine.util.Vector2Int;

import java.util.ArrayList;
import java.util.List;

public class VertexBuffer {
	private final List<Vector2Int> vertexBuffer = new ArrayList<>();
	private final List<Triangle> finalizedTriangles = new ArrayList<>();
	private final DrawMode drawMode;
	private final PixelDrawer pixelDrawer;
	
	public VertexBuffer(PixelDrawer pixelDrawer, DrawMode drawMode) {
		this.pixelDrawer = pixelDrawer;
		this.drawMode = drawMode;
	}
	
	public void vertex(int x, int y) {
		vertexBuffer.add(new Vector2Int(x, y));
	}
	
	/**
	 * Constructs triangles from the vertices given and renders each triangle
	 */
	public void finish() {
		// You can't make a triangle from 2 vertices, so we can skip it.
		if (vertexBuffer.size() < 3) {
			return;
		}
		
		switch (drawMode) {
			case TRIANGLE_FANS -> constructTriangleFans();
		}
		
		renderTriangles();
	}
	
	private void constructTriangleFans() {
		Vector2Int centerVertex = vertexBuffer.get(0);
		
		for (int i = 1; i < vertexBuffer.size() - 1; i++) {
			Vector2Int v1 = vertexBuffer.get(i);
			Vector2Int v2 = vertexBuffer.get(i + 1);
			
			finalizedTriangles.add(new Triangle(centerVertex, v1, v2));
		}
	}
	
	private void renderTriangles() {
		if (finalizedTriangles.isEmpty()) {
			return;
		}
		
		for (Triangle triangle : finalizedTriangles) {
			pixelDrawer.fillTriangle(triangle);
		}
	}
}