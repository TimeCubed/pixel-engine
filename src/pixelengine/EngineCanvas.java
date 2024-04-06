package pixelengine;

import java.util.ArrayList;
import java.util.List;

public abstract class EngineCanvas {
	// Current idea is to use a list that contains all the canvases
	private static final List<EngineCanvas> canvases = new ArrayList<>();
	
	public EngineCanvas() {
		canvases.add(this);
	}
	
	public abstract void setup(PixelDrawer pixelDrawer);
	public abstract void update(PixelDrawer pixelDrawer);
	
	protected List<EngineCanvas> getCanvases() {
		return canvases;
	}
}