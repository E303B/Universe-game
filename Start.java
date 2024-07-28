public class Start{
    public static Window mainWindow;
    public static Runner mainRunner;
    public static void main(String[] args) {
        mainRunner=new Runner(20);
        mainWindow=new Window("Universe", 20, null);
        new Thread(mainRunner).start();
        new Thread(mainWindow).start();
    }
}