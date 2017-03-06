package thread;

import com.sun.org.apache.xpath.internal.SourceTree;
import ship.AbstractShip;
import ship.Creator;
import ship.ShipCreator;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by User on 05.03.2017.
 */
public class DesignerShipThread implements Runnable {
    private BlockingQueue<AbstractShip> ships;

    public DesignerShipThread(BlockingQueue<AbstractShip> ships) {
        this.ships = ships;
    }

    @Override
    public void run() {
        System.out.println("Проектирование караблей...");
        try {
            for (int i = 1; i <= 20; i++) {
                AbstractShip ship = new ShipCreator(Creator.TYPE_SHIP[new Random().nextInt(3)], Creator.TYPE_CAPACITY[new Random().nextInt(3)]);
                ship.setId(i);
                Thread.sleep(10);
                ships.put(ship);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AbstractShip ship = new ShipCreator("null", "null");
        try {
            ships.put(ship);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
