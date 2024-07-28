public class Engine {
    public TileMap mainTileMap;
    public float camX, camY;
    private float camSpeed;
    public int tileSize;

    public Engine() {
        mainTileMap = new TileMap(100, 100);
        camX = camY = 0;
        camSpeed = 5;
        tileSize = 200;
    }

    public void tick() {
        controlCam();
    }

    private void controlCam() {
        Window window = Start.mainWindow;
        Runner runner = Start.mainRunner;
        if (window.keysPressed.contains("W") || window.keysPressed.contains("w"))
            camY -= camSpeed / runner.tps;
        if (window.keysPressed.contains("S") || window.keysPressed.contains("s"))
            camY += camSpeed / runner.tps;
        if (window.keysPressed.contains("D") || window.keysPressed.contains("d"))
            camX += camSpeed / runner.tps;
        if (window.keysPressed.contains("A") || window.keysPressed.contains("a"))
            camX -= camSpeed / runner.tps;
        if ((window.keysPressed.contains("Q") || window.keysPressed.contains("q")))
            tileSize += 50 / runner.tps;
        if ((window.keysPressed.contains("E") || window.keysPressed.contains("e")))
            tileSize -= 50 / runner.tps;
        limitCam(0.5f);
    }

    private void limitCam(float maxOffset) {
        Window window = Start.mainWindow;

        if (tileSize > Math.min(window.getWidth(), window.getHeight()))
            tileSize = Math.min(window.getWidth(), window.getHeight());
        if (tileSize < Math.min((window.getWidth() / mainTileMap.tiles.length),
                (window.getHeight() / mainTileMap.tiles[0].length)))
            tileSize = Math.min((window.getWidth() / mainTileMap.tiles.length),
                    (window.getHeight() / mainTileMap.tiles[0].length));

        if (camX < (-window.getWidth() * maxOffset / tileSize))
            camX = (-window.getWidth() * maxOffset / tileSize);
        if (camX > mainTileMap.tiles.length - (window.getWidth() * maxOffset / tileSize))
            camX = mainTileMap.tiles.length - (window.getWidth() * maxOffset / tileSize);

        if (camY < (-window.getHeight() * maxOffset / tileSize))
            camY = (-window.getHeight() * maxOffset / tileSize);
        if (camY > mainTileMap.tiles[0].length - (window.getHeight() * maxOffset / tileSize))
            camY = mainTileMap.tiles[0].length - (window.getHeight() * maxOffset / tileSize);
    }
}
