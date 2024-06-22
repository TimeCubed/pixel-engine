package pixelengine.util;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
	private final Vector2Int v0, v1, v2;
	
	public Triangle(Vector2Int v0, Vector2Int v1, Vector2Int v2) {
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public List<Vector2Int> getVertices() {
		List<Vector2Int> vertices = new ArrayList<>();
		
		vertices.add(v0);
		vertices.add(v1);
		vertices.add(v2);
		
		return vertices;
	}
}