package producerconsumer;

import java.util.Arrays;

public class Main {

    private static Object lock = new Object();

    private static int[] buffer;
    private static int counter;

    static class Producer{

        public void produce(){

            synchronized (lock) {
                if (isFull(buffer)) {

                    try{
                        lock.wait();
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }
                buffer[counter++] = 1;

                lock.notifyAll();
            }
        }

    }


    static class Consumer {

        public void consume() {
            synchronized (lock) {
                if (isEmpty(buffer)) {
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
                buffer[--counter] = 0;
                lock.notifyAll();
            }
        }
    }

    private static boolean isEmpty(int[] buffer) {
        return counter==0;
    }
    private static boolean isFull(int[] buffer) {
        return buffer.length==counter;
    }

    public static void main(String[] args) throws InterruptedException {
         buffer = new int[10];
         counter = 0;

         Producer producer = new Producer();
         Consumer consumer = new Consumer();

         Thread producerThread = new Thread(
                 ()->{
                         for(int i=0; i<50; i++) {
                            producer.produce();
                         }

                     System.out.println("Done producing !");

                 });

         Thread consumerThread = new Thread(
                 ()->{
                     for(int i=0; i<45; i++){
                         consumer.consume();
                     }
                     System.out.println("Done Consuming !");
                 }

         );

         producerThread.start();
         consumerThread.start();
         producerThread.join();
         consumerThread.join();

        System.out.println("The buffer size is " + counter);

//        Arrays.stream(buffer).forEach(System.out::println);


    }


}
