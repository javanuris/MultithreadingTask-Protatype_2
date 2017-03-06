package thread;

import ship.AbstractShip;
import ship.Creator;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by User on 05.03.2017.
 */
public class ExecuteShipThread implements Runnable {
    ExecutorService shipOilExec = Executors.newFixedThreadPool(Creator.CONNECTION_COUNT);
    ExecutorService shipBoxExec = Executors.newFixedThreadPool(Creator.CONNECTION_COUNT);
    ExecutorService shipEatExec = Executors.newFixedThreadPool(Creator.CONNECTION_COUNT);

    private BlockingQueue<AbstractShip> ships;

    public ExecuteShipThread(BlockingQueue<AbstractShip> ships) {
        this.ships = ships;
    }

    @Override
    public void run() {
        System.out.println("Начало загрузки караблей...");
        for (AbstractShip ship : ships) {

                switch (ship.getType()) {
                    case Creator.EAT_SHIP:
                        shipEatExec.execute(new LoaderThread(ships));
                        break;
                    case Creator.BOX_SHIP:
                        shipBoxExec.execute(new LoaderThread(ships));
                        break;
                    case Creator.OIL_SHIP:
                        shipOilExec.execute(new LoaderThread(ships));
                        break;
                    case "null":
                        shipOilExec.shutdown();
                        shipBoxExec.shutdown();
                        shipEatExec.shutdown();
                        System.out.println("Загрузка всех караблей завершена!!!");
                    default:
                        break;
                }
            }
        }
    }






