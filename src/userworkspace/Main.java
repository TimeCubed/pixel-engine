package userworkspace;

import pixelengine.EngineCanvas;
import pixelengine.EngineEntryPoint;
import pixelengine.util.Vector2Int;

public class Main extends EngineCanvas {
	public Main() {
		super("windowTitle", 100, 100, 5);
		
		EngineEntryPoint.startEngine(this);
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	@Override
	public void update() {
		Vector2Int v0 = new Vector2Int(20, 20);
		Vector2Int v1 = new Vector2Int(25, 30);
		Vector2Int v2 = new Vector2Int(35, 30);
		Vector2Int v3 = new Vector2Int(40, 20);
		
		pixelDrawer.fillQuad(v0, v1, v2, v3);
	}
}