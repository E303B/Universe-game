public class Runner implements Runnable {
    public int tps;
    public Engine mainEngine;
    private void tick() {
        mainEngine.tick();
    }

    @Override
    public void run() {
        while (true) {
            tick();
            try {
                Thread.sleep(1000 / tps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Runner(int tps) {
        this.tps = tps;
        mainEngine=new Engine();
    }

}
