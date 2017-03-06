package main;


import ship.AbstractShip;
import ship.Creator;
import thread.DesignerShipThread;
import thread.ExecuteShipThread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by User on 05.03.2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService shipExec = Executors.newFixedThreadPool(Creator.CONNECTION_COUNT);
        ExecutorService desigExec = Executors.newFixedThreadPool(Creator.CONNECTION_COUNT);
        ArrayBlockingQueue<AbstractShip> abstractShips = new ArrayBlockingQueue<AbstractShip>(6);
        desigExec.execute(new DesignerShipThread(abstractShips));
        Thread.sleep(100);
        shipExec.execute(new ExecuteShipThread(abstractShips));

        desigExec.shutdown();
        shipExec.shutdown();


    }
}

