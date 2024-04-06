package pixelengine;

import pixelengine.util.Logger;

public class RenderThread extends Thread implements Runnable {
	private final Logger logger = new Logger("Render Thread");
	private final PixelDrawer pixelDrawer;
	private final EngineCanvas engineCanvas;
	private int frameCount = 0;
	private final int fps;
	
	public RenderThread(PixelDrawer pixelDrawer, EngineCanvas engineCanvas, int fps) {
		this.pixelDrawer = pixelDrawer;
		this.engineCanvas = engineCanvas;
		
		this.fps = fps;
	}
	
	@Override
	public void run() {
		logger.info("Render thread running at " + fps + " fps");
		
		try {
			while (true) {
				long startTime = System.currentTimeMillis();
				pixelDrawer.clearPixels();

				engineCanvas.update();

				pixelDrawer.repaint();
				frameCount++;

				long elapsedTime = System.currentTimeMillis() - startTime;
				long timeToSleep = 1000 / fps - elapsedTime;

				if (timeToSleep > 0) {
					Thread.sleep(timeToSleep);
				}
			}
		} catch (Exception e) {
			logger.error("! RENDER THREAD FAIL !");
			logger.error("The render thread crashed due to the following exception:");
			logger.error("");

			logger.error(e.getClass().getName() + ": " + ((e.getCause() != null) ? e.getCause().getMessage() : "Unknown cause: "));
			for (StackTraceElement stackTraceElement : e.getStackTrace()) {
				logger.error(stackTraceElement.toString());
			}

			logger.info("Exiting application..");
			System.exit(0);
		}
	}
	
	@SuppressWarnings("unused")
	public long getFrameCount() {
		return frameCount;
	}
}
