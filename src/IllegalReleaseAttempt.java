public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    public IllegalReleaseAttempt() {}

    public IllegalReleaseAttempt(String errorOutput) {
        System.out.println(errorOutput);
    }
}
