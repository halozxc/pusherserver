package xyz.humilr.pusherserver.utils;

public class TimeUtils {
    public static long minToMicrosecond(int time) {
        return (long) time * 60 * 1000;
    }
}
