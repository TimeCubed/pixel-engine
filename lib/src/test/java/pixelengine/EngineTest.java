package pixelengine;

import pixelengine.util.Vector2Int;

public class EngineTest extends EngineCanvas {
    public EngineTest() {
        super("windowTitle", 100, 100, 5);
    }

    // Weird way to test things, but regular unit tests don't work very nicely
    // with the way I have my stuff set up.
    // I also don't want this to get compiled with the rest of the code, so I
    // put the stuff to run the engine in the test package.
    public static void main(String[] args) {
        EngineEntryPoint.startEngine(new EngineTest());
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