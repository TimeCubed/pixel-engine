package pixelengine;

import pixelengine.util.Constants;
import pixelengine.util.Logger;

public class RenderThread implements Runnable {
	private final PixelDrawer pixelDrawer;
	private final Logger logger = new Logger("Render Thread");
	private int frameCount = 0;
	
	public RenderThread(final PixelDrawer pixelDrawer) {
		this.pixelDrawer = pixelDrawer;
	}
	
	@Override
	public void run() {
		

		try {
			while (true) {
				long startTime = System.currentTimeMillis();
				pixelDrawer.clearPixels();

				

				pixelDrawer.repaint();
				frameCount++;

				long elapsedTime = System.currentTimeMillis() - startTime;
				long timeToSleep = 1000 / Constants.FPS - elapsedTime;

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
