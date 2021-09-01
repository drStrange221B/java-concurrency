package emrys.understandingconcurrency;

public class LongWrapper {

    private Object key = new Object();
    private Long l;

    public LongWrapper(Long l) {

        this.l = l;
    }

    public Long getValue() {
        return l;
    }

    public void incrementValue() {
        synchronized(key) {
            this.l += 1;
        }
    }
}
