package emrys.understandingconcurrency;

public class DeadLockExample {

    public static void main(String[] args) throws InterruptedException {
        SampleClass sampleClass = new SampleClass();

        Thread t1 = new Thread(()->sampleClass.a());
        Thread t2 = new Thread(()-> sampleClass.b());
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
