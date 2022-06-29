import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock {
    private long currentThreadID;
    private AtomicBoolean myLock;
    private int counter;

    public MyReentrantLock(){
        this.currentThreadID = Thread.currentThread().getId();
        this.myLock = new AtomicBoolean();
        this.counter = 0;
    }

    @Override
    public void acquire(){
        if ( (this.counter == 0) || (Thread.currentThread().getId() != this.currentThreadID) ) {
            while ( (this.myLock.compareAndSet(false, true))
                        && (Thread.currentThread().getId() != this.currentThreadID) ) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException exception) {
                    // Catch exception and move on.
                }
            }
            this.currentThreadID = Thread.currentThread().getId();
        }
        this.counter++;
    }

    @Override
    public boolean tryAcquire(){
        boolean isLockReleased = this.myLock.compareAndSet(false, true);
        return ( isLockReleased && (Thread.currentThread().getId() == this.currentThreadID) );
    }

    @Override
    public void release(){
        if ((Thread.currentThread().getId() != this.currentThreadID) || (!this.myLock.get()) ) {
            throw new IllegalReleaseAttempt();
        }
        if (this.counter > 1) {
            this.counter--;
        }
        else {
            this.counter--;
            this.myLock.set(false);
        }
    }

    @Override
    public void close() {
        this.release();
    }
}
