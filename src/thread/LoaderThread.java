package thread;
import ship.AbstractShip;
import java.util.concurrent.BlockingQueue;

/**
 * Created by User on 05.03.2017.
 */
public class LoaderThread implements Runnable {

    private BlockingQueue<AbstractShip> ships;
    private static final int LOAD_SPEED = 10;

    public LoaderThread(BlockingQueue<AbstractShip> ships) {
        this.ships = ships;
    }

    @Override
    public void run() {
        AbstractShip ship;
        try {
            while ((ship = ships.take()).getType() != "null") {
                System.out.println(ship.toString() + " Пришел");
                Thread.sleep(1000);
                System.out.println("Номер: " + ship.getId() + " Начала погрузку");
                while (!ship.loadDetermine()) {
                    ship.setGoodOnShip(ship.getGoodOnShip() + LOAD_SPEED);
                    Thread.sleep(1000);
                }
                System.out.println("Номер: " + ship.getId() + " Загрузился");
                Thread.sleep(100);
                System.out.println(ship.toString() + " Ушел");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
