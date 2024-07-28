import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

    public int fps;
    public String keysPressed;
    BufferedImage renderBuf;

    public Window(String title, int fps, Image image) {
        this.fps = fps;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(title);
        this.setIconImage(image != null ? image : getIconImage());
        this.setExtendedState(MAXIMIZED_BOTH);
        keysPressed = "";
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (keysPressed.contains(e.getKeyChar() + ""))
                    return;
                keysPressed += e.getKeyChar();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!keysPressed.contains(e.getKeyChar() + ""))
                    return;
                keysPressed = keysPressed.substring(0, keysPressed.indexOf(e.getKeyChar()))
                        + keysPressed.substring(keysPressed.indexOf(e.getKeyChar()) + 1);
            }

        });
    }

    private void drawTiles() {
        Runner runner = Start.mainRunner;
        if (runner == null)
            return;
        Engine engine = runner.mainEngine;
        if (engine == null)
            return;
        TileMap tileMap = engine.mainTileMap;
        if (tileMap == null)
            return;
        Graphics gr = renderBuf.getGraphics();
        for (int x = 0; x < tileMap.tiles.length; x++) {
            for (int y = 0; y < tileMap.tiles[x].length; y++) {
                gr.setColor(Color.black);
                gr.fillRect((int) ((x - engine.camX) * engine.tileSize), (int) ((y - engine.camY) * engine.tileSize),
                        engine.tileSize, engine.tileSize);
                gr.setColor(Color.green);
                gr.fillRect((int) ((x - engine.camX + 0.05) * engine.tileSize),
                        (int) ((y - engine.camY + 0.05) * engine.tileSize),
                        (int) (engine.tileSize * 0.9), (int) (engine.tileSize * 0.9));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        renderBuf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        drawTiles();
        ((Graphics2D) g).drawImage(renderBuf, null, 0, 0);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
