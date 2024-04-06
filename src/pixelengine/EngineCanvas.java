package pixelengine;

import pixelengine.util.Logger;

public abstract class EngineCanvas {
	private final int width, height, pixelSize;
	public final PixelDrawer pixelDrawer;
	private final String windowTitle;
	public final Logger logger;
	
	public EngineCanvas(String windowTitle, int width, int height, int pixelSize) {
		this.windowTitle = windowTitle;
		
		this.width = width;
		this.height = height;
		this.pixelSize = pixelSize;
		
		this.pixelDrawer = new PixelDrawer(width, height, pixelSize);
		this.logger = new Logger("Canvas");
	}
	
	public abstract void update();
	
	public PixelDrawer getPixelDrawer() {
		return pixelDrawer;
	}
	public String getWindowTitle() {
		return windowTitle;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getPixelSize() {
		return pixelSize;
	}
}