package pixelengine.util;

@SuppressWarnings("unused")
public class Vector2Int {
	public int x, y;
	
	public Vector2Int(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * May not provide great results.
	 */
	public void normalize() {
		// No infamous fast inverse square root algorithm here
		// Just pure unoptimized code
		
		double length = Math.sqrt(this.x * this.x + this.y * this.y);
		
		this.x /= length;
		this.y /= length;
	}
	
	@Override
	public Vector2Int clone() throws AssertionError {
		try {
			return (Vector2Int) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("how??");
		}
	}
}
