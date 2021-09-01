package emrys.understandingconcurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {


    public static void main(String[] args) throws InterruptedException {



        Runnable runnable = new Runnable() {

            public void run(){
                System.out.println("I am running in " + Thread.currentThread().getName());
            }

        };

        Thread thread = new Thread(runnable);
        thread.setName("thread1");
        thread.start();
        thread.join();
        System.out.println("this is " + Thread.currentThread().getName() );
    }
}
