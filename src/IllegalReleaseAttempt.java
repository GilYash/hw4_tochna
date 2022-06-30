/**
 * This class extends the IllegalMonitorStateException, and represents an exception for trying to release a lock
 * illegally.
 */
public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    public IllegalReleaseAttempt() {}

    public IllegalReleaseAttempt(String errorOutput) {
        System.out.println(errorOutput);
    }
}
