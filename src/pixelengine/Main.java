package pixelengine;

import javax.swing.*;
import pixelengine.util.Constants;
import java.awt.*;

public class Main extends JPanel {
	protected static final PixelDrawer pixelDrawer = new PixelDrawer();
	protected static final JFrame jFrame = new JFrame("Wireframe Renderer");

	private static final InputListener inputListener = new InputListener();
	
	public static void main(String[] args) {
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.add(pixelDrawer);
		
		jFrame.addMouseListener(inputListener);
		jFrame.addMouseMotionListener(inputListener);
		jFrame.addKeyListener(inputListener);

		jFrame.setSize(Constants.SCREEN_WIDTH * Constants.PIXEL_SIZE, Constants.SCREEN_HEIGHT * Constants.PIXEL_SIZE);
		
		jFrame.setLocationRelativeTo(null);
		
		// Start the render thread
//		Thread renderThread = new Thread(new RenderThread(pixelDrawer));
//		renderThread.setName("Render Thread");
//		renderThread.start();
	}
	
//	public static void setBackgroundColor(Color c) {
//		jFrame.getContentPane().setBackground(c);
//	}
}