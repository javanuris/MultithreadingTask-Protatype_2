package main;



import ship.AbstractShip;
import ship.Creator;
import thread.DesignerShipThread;
import thread.LoaderThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by User on 05.03.2017.
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService shipExec = Executors.newFixedThreadPool(Creator.CONNECTION_COUNT);
        ExecutorService desigExec = Executors.newFixedThreadPool(Creator.CONNECTION_COUNT);
        BlockingQueue<AbstractShip> ships = new ArrayBlockingQueue<AbstractShip>(6);
        DesignerShipThread designerShipThread = new DesignerShipThread(ships);
        LoaderThread loaderThread = new LoaderThread(ships);
      //  LoaderThread loaderThread1 = new LoaderThread(ships);
        desigExec.execute( designerShipThread);
        shipExec.execute( loaderThread);
        shipExec.shutdown();
        desigExec.shutdown();

      /*  new Thread(designerShipThread).start();
        new Thread(loaderThread).start();
        new Thread(loaderThread1).start();*/
    }

}
