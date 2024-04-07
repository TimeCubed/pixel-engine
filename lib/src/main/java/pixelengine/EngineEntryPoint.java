package pixelengine;

import pixelengine.util.Logger;
import javax.swing.*;

@SuppressWarnings("unused")
public class EngineEntryPoint {
	protected static JFrame jFrame;
	protected static PixelDrawer pixelDrawer;
	private static final Logger logger = new Logger("Main Thread");
	private static final InputListener inputListener = new InputListener();
	
	public static void startEngine(EngineCanvas engineCanvas) {
		long startTime = System.currentTimeMillis();
		
		logger.info("Starting engine...");
		
		String windowTitle = engineCanvas.getWindowTitle();
		int windowHeight = engineCanvas.getHeight();
		int windowWidth = engineCanvas.getWidth();
		int pixelSize = engineCanvas.getPixelSize();
		
		logger.info("Creating window...");
		jFrame = new JFrame(windowTitle);
		pixelDrawer = engineCanvas.getPixelDrawer();
		
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.add(pixelDrawer);
		
		jFrame.addMouseListener(inputListener);
		jFrame.addMouseMotionListener(inputListener);
		jFrame.addKeyListener(inputListener);
		
		jFrame.setSize(windowWidth * pixelSize, windowHeight * pixelSize);
		
		jFrame.setLocationRelativeTo(null);
		
		logger.info("Created window in " + (System.currentTimeMillis() - startTime) + "ms");
		
		jFrame.setVisible(true);

		logger.info("Finished engine setup in " + (System.currentTimeMillis() - startTime) + "ms");

		// This doesn't start a new thread, but it does replace the main thread since there
		// isn't anything else that needs to run on a separate thread. Naming convention
		// sucks but it is what it is
		RenderThread renderThread = new RenderThread(pixelDrawer, engineCanvas, 60);
		renderThread.run();
	}
}