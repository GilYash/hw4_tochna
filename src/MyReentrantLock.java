import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A class representing our version of a lock, which controls the use of threads in a certain computer resource.
 * It includes methods for locking and releasing the resource.
 */
public class MyReentrantLock implements Lock {
    private long currentThreadID;
    private AtomicBoolean myLock;
    private int counter;  // Number of times the lock was locked -
                          // Needed in order to release the lock as many times as necessary in the release method.

    public MyReentrantLock() {
        this.currentThreadID = Thread.currentThread().getId();
        this.myLock = new AtomicBoolean();
        this.counter = 0;
    }

    /**
     * In this method, the thread that is currently working checks whether it may lock the lock,
     * and if so, it does - thus prevents other threads using the resource.
     */
    @Override
    public void acquire(){
        if ( (this.counter == 0) || (Thread.currentThread().getId() != this.currentThreadID) ) {
            while ( (this.myLock.compareAndSet(false, true)) &&
                    (Thread.currentThread().getId() != this.currentThreadID)       ) {
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

    /**
     * Checks if the currently-working thread may lock the lock. While at it:
     * Checks whether the lock is released and whether the currently-working thread is he one asking to lock it.
     * @return - A boolean value indicating whether a thread may \ may not lock the lock.
     */
    @Override
    public boolean tryAcquire(){
        boolean isLockReleased = this.myLock.compareAndSet(false, true);
        // if was released, isLockReleased=true.    // if was locked, isLockReleased=false.
        return ( isLockReleased && (Thread.currentThread().getId() == this.currentThreadID) );
    }

    /**
     * Releases the locked lock, according to the amount of times it was locked by that thread.
     * This while making sure the thread that asks to release it is the same one that locked it,
     * and that the lock is indeed locked.
     */
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

    /**
     * Executes the lock's release command.
     */
    @Override
    public void close() {
        this.release();
    }
}
