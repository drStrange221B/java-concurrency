package emrys.understandingconcurrency;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {

        LongWrapper longWrapper = new LongWrapper(0l);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                longWrapper.incrementValue();
            }
        };

        Thread t = new Thread(runnable);


        Thread[] threads = new Thread[1000];

        for(int i=0; i<1000; i++){
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for (int i=0; i<1000; i++){
            threads[i].join();
        }


        System.out.println("total value: " + longWrapper.getValue());
    }
}
