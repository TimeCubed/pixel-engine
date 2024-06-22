package pixelengine;

import pixelengine.vertexbuffer.DrawMode;
import pixelengine.vertexbuffer.VertexBuffer;

public class EngineTest extends EngineCanvas {
    public EngineTest() {
        super("Testing Window", 100, 100, 5);
    }

    // Weird way to test things, but regular unit tests don't work very nicely
    // with the way I have everything set up.
    // I also don't want this to get compiled with the rest of the code, so I
    // put the stuff to run the engine in the test package.
    public static void main(String[] args) {
        EngineEntryPoint.startEngine(new EngineTest());
    }
    
    @Override
    public void update() {
        int centerX = 50, centerY = 50;
        int radius = 10;
        
        VertexBuffer vertexBuffer = new VertexBuffer(pixelDrawer, DrawMode.TRIANGLE_FANS);
        vertexBuffer.vertex(centerX, centerY);
        
        for (int angle = 0; angle <= 360; angle++) {
            int x = (int) (Math.cos(Math.toRadians(angle)) * radius + centerX);
            int y = (int) (Math.sin(Math.toRadians(angle)) * radius + centerY);
            
            vertexBuffer.vertex(x, y);
        }
        
        vertexBuffer.finish();
    }
}